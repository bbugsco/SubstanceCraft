package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.OxidizerBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.OneInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.OxidizerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class OxidizerScreen extends OneInputScreen<OxidizerRecipe, OxidizerBlockEntity, OxidizerMenu> {

    public OxidizerScreen(OxidizerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
