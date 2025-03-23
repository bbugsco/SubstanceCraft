package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.OneInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.HashPressMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class HashPressScreen extends OneInputScreen<HashPressRecipe, HashPressBlockEntity, HashPressMenu> {

    public HashPressScreen(HashPressMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

}