package com.github.bbugsco.substancecraft.gui.menus;

import com.github.bbugsco.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.github.bbugsco.substancecraft.gui.OneInputMenu;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HashPressMenu extends OneInputMenu<HashPressRecipe, HashPressBlockEntity> {

    public HashPressMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public HashPressMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.HASH_PRESS, syncId, inventory, entity, arrayPropertyDelegate);
    }

}
