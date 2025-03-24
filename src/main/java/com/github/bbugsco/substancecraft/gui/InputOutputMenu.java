package com.github.bbugsco.substancecraft.gui;

import com.github.bbugsco.substancecraft.block.entity.InputOutputBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InputOutputMenu<T extends InputOutputBlockEntity> extends AbstractContainerMenu {

    protected static final int INPUT_SLOT_X = 98;
    protected static final int INPUT_SLOT_Y = 11;

    protected static final int OUTPUT_SLOT_X = 98;
    protected static final int OUTPUT_SLOT_Y = 59;

    protected static final int BYPRODUCT_SLOT_X = 116;
    protected static final int BYPRODUCT_SLOT_Y = 59;

    protected final int INPUT_SLOT_INDEX = 0;
    protected final int BYPRODUCT_SLOT_INDEX;
    protected final int OUTPUT_SLOT_INDEX;

    protected final T blockEntity;
    protected final Container blockEntityInventory;
    protected final SimpleContainerData blockEntityData;
    protected final Inventory playerInventory;

    @SuppressWarnings("unchecked")
    protected InputOutputMenu(MenuType<? extends InputOutputMenu> menu, int syncId, Inventory playerInventory, InputOutputBlockEntity entity, SimpleContainerData blockEntityData) {
        super(menu, syncId);
        checkContainerSize(entity, entity.getContainerSize());
        this.blockEntity = (T) entity;
        this.blockEntityInventory = entity;
        blockEntityInventory.startOpen(playerInventory.player);
        this.playerInventory = playerInventory;
        this.blockEntityData = blockEntityData;

        boolean isMultiple = blockEntity.multipleInput();
        OUTPUT_SLOT_INDEX = isMultiple ? 4 : 1;
        BYPRODUCT_SLOT_INDEX = OUTPUT_SLOT_INDEX + 1;

        for (int i = 0; i < (isMultiple ? 4 : 1); i++) {
            this.addSlot(createInputSlot(blockEntity, i));
        }
        this.addSlot(createOutputSlot(blockEntity));
        for (int i = 0; i < Math.min(3, blockEntity.getMaxByproducts()); i++) {
            this.addSlot(createByproductSlot(blockEntity, i));
        }
        addDataSlots(blockEntityData);

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public InputOutputBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public boolean isCrafting() {
        return blockEntityData.get(0) > 0;
    }

    public int getNumRecipes() {
        return blockEntity.getNumRecipes();
    }

    public int getScaledProgress() {
        int progress = this.blockEntityData.get(0);
        int maxProgress = this.blockEntityData.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Container playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i + row * 9 + 9, 8 + i * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Container playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (index < this.blockEntityInventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.blockEntityInventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.blockEntityInventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntityInventory.stillValid(player);
    }

    public Slot createInputSlot(Container container, int index) {
        return new Slot(container, INPUT_SLOT_INDEX + index, INPUT_SLOT_X + (18 * index), INPUT_SLOT_Y);
    }

    public Slot createOutputSlot(Container container) {
        return new Slot(container, OUTPUT_SLOT_INDEX, OUTPUT_SLOT_X, OUTPUT_SLOT_Y);
    }

    public Slot createByproductSlot(Container container, int index) {
        return new Slot(container, BYPRODUCT_SLOT_INDEX + index, BYPRODUCT_SLOT_X + (18 * index), BYPRODUCT_SLOT_Y);
    }

}
