package com.github.bbugsco.drugs.gui.one_input_menu;

import com.github.bbugsco.drugs.block.entity.one_input.AirExtractorBlockEntity;
import com.github.bbugsco.drugs.gui.DrugsMenus;
import com.github.bbugsco.drugs.recipe.recipes.AirExtractorRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AirExtractorMenu extends OneInputMenu<AirExtractorRecipe, AirExtractorBlockEntity> {

    public AirExtractorMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public AirExtractorMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.AIR_EXTRACTOR, syncId, inventory, entity, arrayPropertyDelegate);
    }
}