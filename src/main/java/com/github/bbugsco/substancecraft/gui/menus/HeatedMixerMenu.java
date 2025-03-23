package com.github.bbugsco.substancecraft.gui.menus;

import com.github.bbugsco.substancecraft.block.entity.entities.HeatedMixerBlockEntity;
import com.github.bbugsco.substancecraft.gui.MultipleInputMenu;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HeatedMixerMenu extends MultipleInputMenu<HeatedMixerRecipe, HeatedMixerBlockEntity> {

    public HeatedMixerMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public HeatedMixerMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.HEATED_MIXER, syncId, inventory, entity, arrayPropertyDelegate);
    }


}
