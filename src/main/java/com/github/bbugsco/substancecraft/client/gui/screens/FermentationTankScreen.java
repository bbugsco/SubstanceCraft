package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.FermentationTankBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.MultipleInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.FermentationTankMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class FermentationTankScreen extends MultipleInputScreen<FermentationTankRecipe, FermentationTankBlockEntity, FermentationTankMenu> {

    public FermentationTankScreen(FermentationTankMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
