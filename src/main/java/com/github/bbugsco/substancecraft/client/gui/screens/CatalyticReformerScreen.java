package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.CatalyticReformerBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.OneInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.CatalyticReformerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class CatalyticReformerScreen extends OneInputScreen<CatalyticReformerRecipe, CatalyticReformerBlockEntity, CatalyticReformerMenu> {

    public CatalyticReformerScreen(CatalyticReformerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
