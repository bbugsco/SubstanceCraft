package com.github.bbugsco.substancecraft.gui;

import com.github.bbugsco.substancecraft.block.entity.InputOutputBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.OneInputBlockEntity;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class OneInputMenu<R extends OneInputRecipe, T extends OneInputBlockEntity<R>> extends InputOutputMenu<T> {

    protected OneInputMenu(MenuType<? extends OneInputMenu> menu, int syncId, Inventory playerInventory, BlockEntity entity, SimpleContainerData blockEntityData) {
        super(menu, syncId, playerInventory, (InputOutputBlockEntity) entity, blockEntityData);
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            blockEntity.setSelectedRecipeIndex(id);
            blockEntity.setChanged();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < blockEntity.getRecipes().size();
    }

    public List<RecipeHolder<R>> getRecipes() {
        return blockEntity.getRecipes();
    }

}
