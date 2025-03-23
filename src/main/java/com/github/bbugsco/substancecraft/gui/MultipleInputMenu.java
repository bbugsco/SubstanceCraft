package com.github.bbugsco.substancecraft.gui;

import com.github.bbugsco.substancecraft.block.entity.InputOutputBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.MultiInputBlockEntity;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.crafting.RecipeHolder;
import java.util.List;

public class MultipleInputMenu<R extends MultipleInputRecipe, T extends MultiInputBlockEntity<R>> extends WorkstationStonecutterRecipeListMenu<T> {

    protected MultipleInputMenu(MenuType<? extends MultipleInputMenu> menu, int syncId, Inventory playerInventory, InputOutputBlockEntity entity, SimpleContainerData blockEntityData) {
        super(menu, syncId, playerInventory, entity, blockEntityData);
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

    public boolean hasRepeatInputRecipes() {
        return handle.hasRepeatInputRecipes();
    }

}
