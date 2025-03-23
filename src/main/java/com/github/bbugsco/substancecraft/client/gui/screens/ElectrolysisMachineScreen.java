package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.OneInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.ElectrolysisMachineMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ElectrolysisMachineScreen extends OneInputScreen<ElectrolysisRecipe, ElectrolysisMachineBlockEntity, ElectrolysisMachineMenu> {

    public ElectrolysisMachineScreen(ElectrolysisMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
