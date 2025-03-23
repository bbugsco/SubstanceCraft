package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.HeatedMixerBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.MultipleInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class HeatedMixerScreen extends MultipleInputScreen<HeatedMixerRecipe, HeatedMixerBlockEntity, HeatedMixerMenu> {

    public HeatedMixerScreen(HeatedMixerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
