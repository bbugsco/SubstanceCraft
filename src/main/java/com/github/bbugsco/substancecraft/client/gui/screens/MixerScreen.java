package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.MixerBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.MultipleInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.MixerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.MixerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class MixerScreen extends MultipleInputScreen<MixerRecipe, MixerBlockEntity, MixerMenu> {

    public MixerScreen(MixerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
