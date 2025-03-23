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

public class WorkstationStonecutterRecipeListMenu<T extends InputOutputBlockEntity> extends AbstractContainerMenu {

    protected static final int INPUT_SLOT_INDEX = 0;
    protected static final int INPUT_SLOT_X = 98;
    protected static final int INPUT_SLOT_Y = 11;

    protected static final int OUTPUT_SLOT_X = 98;
    protected static final int OUTPUT_SLOT_Y = 59;

    protected static final int BYPRODUCT_SLOT_X = 116;
    protected static final int BYPRODUCT_SLOT_Y = 59;

    protected final int BYPRODUCT_SLOT_INDEX;
    protected final int OUTPUT_SLOT_INDEX;

    protected final T handle;
    protected final Container inventory;
    protected final Inventory playerInventory;
    protected final SimpleContainerData simpleContainerData;

    @SuppressWarnings("unchecked")
    protected WorkstationStonecutterRecipeListMenu(MenuType<? extends WorkstationStonecutterRecipeListMenu> menu, int syncId, Inventory playerInventory, InputOutputBlockEntity entity, SimpleContainerData blockEntityData) {
        super(menu, syncId);
        checkContainerSize(entity, entity.getContainerSize());
        this.handle = (T) entity;
        this.inventory = entity;
        inventory.startOpen(playerInventory.player);
        this.playerInventory = playerInventory;
        this.simpleContainerData = blockEntityData;

        boolean isMultiple = handle.multipleInput();
        OUTPUT_SLOT_INDEX = isMultiple ? 4 : 1;
        BYPRODUCT_SLOT_INDEX = OUTPUT_SLOT_INDEX + 1;

        if (!isMultiple) {
            this.addSlot(inputSlot(handle, 0));
        } else {
            for (int i = 0; i < 4; i++) {
                this.addSlot(inputSlot(handle, i));
            }
        }
        this.addSlot(outputSlot(handle));
        for (int i = 0; i < Math.min(3, handle.getMaxByproducts()); i++) {
            this.addSlot(byproductSlot(handle, i));
        }
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addDataSlots(blockEntityData);
    }

    public InputOutputBlockEntity getHandle() {
        return handle;
    }

    public boolean isCrafting() {
        return simpleContainerData.get(0) > 0;
    }

    public int getNumRecipes() {
        return handle.getNumRecipes();
    }

    public int getScaledProgress() {
        int progress = this.simpleContainerData.get(0);
        int maxProgress = this.simpleContainerData.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Container playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
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
            if (index < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.inventory.getContainerSize(), false)) {
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
        return this.inventory.stillValid(player);
    }

    public static Slot inputSlot(Container container, int index) {
        return new Slot(container, INPUT_SLOT_INDEX + index, INPUT_SLOT_X + (18 * index), INPUT_SLOT_Y);
    }

    public Slot outputSlot(Container container) {
        return new Slot(container, OUTPUT_SLOT_INDEX, OUTPUT_SLOT_X, OUTPUT_SLOT_Y);
    }

    public Slot byproductSlot(Container container, int index) {
        return new Slot(container, BYPRODUCT_SLOT_INDEX + index, BYPRODUCT_SLOT_X + (18 * index), BYPRODUCT_SLOT_Y);
    }

}
