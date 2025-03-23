package com.github.bbugsco.substancecraft.gui.menus;

import com.github.bbugsco.substancecraft.block.entity.entities.FermentationTankBlockEntity;
import com.github.bbugsco.substancecraft.gui.MultipleInputMenu;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FermentationTankMenu extends MultipleInputMenu<FermentationTankRecipe, FermentationTankBlockEntity> {

    public FermentationTankMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public FermentationTankMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.FERMENTATION_TANK, syncId, inventory, entity, arrayPropertyDelegate);
    }

}
