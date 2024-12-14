package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.block.entity.OxidizerBlockEntity;
import com.github.bbugsco.drugs.recipe.recipes.OxidizerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class OxidizerMenu extends OneInputMenu<OxidizerRecipe, OxidizerBlockEntity> {

    public OxidizerMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public OxidizerMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.OXIDIZER_MENU, syncId, inventory, entity, arrayPropertyDelegate, new Slot[]{OneInputMenu.inputSlot((Container) entity), OneInputMenu.outputSlot((Container) entity)});
    }

}