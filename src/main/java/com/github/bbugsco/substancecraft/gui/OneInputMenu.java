package com.github.bbugsco.substancecraft.gui;

import com.github.bbugsco.substancecraft.block.entity.OneInputBlockEntity;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class OneInputMenu<R extends OneInputRecipe, T extends OneInputBlockEntity<R>> extends AbstractContainerMenu {

    public static final int INPUT_SLOT_INDEX = 0;
    public static final int INPUT_SLOT_X = 98;
    public static final int INPUT_SLOT_Y = 11;

    public static final int OUTPUT_SLOT_INDEX = 1;
    public static final int OUTPUT_SLOT_X = 98;
    public static final int OUTPUT_SLOT_Y = 59;

    public static final int BYPRODUCT_SLOT_INDEX = 2;
    public static final int BYPRODUCT_SLOT_X = 116;
    public static final int BYPRODUCT_SLOT_Y = 59;

    public final T handle;
    protected final Container inventory;
    protected final Inventory playerInventory;
    protected final SimpleContainerData simpleContainerData;

    protected OneInputMenu(MenuType<? extends OneInputMenu> menu, int syncId, Inventory playerInventory, BlockEntity entity, SimpleContainerData blockEntityData) {
        super(menu, syncId);
        checkContainerSize(((Container) entity), ((Container) entity).getContainerSize());
        this.handle = (T) entity;
        this.inventory = ((Container) entity);
        inventory.startOpen(playerInventory.player);
        this.playerInventory = playerInventory;
        this.simpleContainerData = blockEntityData;

        this.addSlot(inputSlot(handle));
        this.addSlot(outputSlot(handle));
        for (int i = 0; i < Math.min(3, handle.getMaxByproducts()); i++) {
            this.addSlot(byproductSlot(handle, i));
        }
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addDataSlots(blockEntityData);
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            handle.setSelectedRecipeIndex(id);
            handle.setChanged();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < handle.getRecipes().size();
    }

    public int getSelectedRecipeIndex() {
        try {
            return simpleContainerData.get(2);
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public List<RecipeHolder<R>> getRecipes() {
        return handle.getRecipes();
    }

    public int getNumRecipes() {
        return getRecipes().size();
    }

    public boolean isCrafting() {
        return simpleContainerData.get(0) > 0;
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

    public boolean hasRepeatInputRecipes() {
        return handle.hasRepeatInputRecipes();
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

    public static Slot inputSlot(Container container) {
        return new Slot(container, INPUT_SLOT_INDEX, INPUT_SLOT_X, INPUT_SLOT_Y);
    }

    public static Slot outputSlot(Container container) {
        return new Slot(container, OUTPUT_SLOT_INDEX, OUTPUT_SLOT_X, OUTPUT_SLOT_Y);
    }

    public static Slot byproductSlot(Container container, int index) {
        return new Slot(container, BYPRODUCT_SLOT_INDEX + index, BYPRODUCT_SLOT_X + (18 * index), BYPRODUCT_SLOT_Y);
    }

}
