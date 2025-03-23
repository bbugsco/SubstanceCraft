package com.github.bbugsco.substancecraft.gui.menus;

import com.github.bbugsco.substancecraft.block.entity.entities.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.substancecraft.gui.OneInputMenu;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ElectrolysisMachineMenu extends OneInputMenu<ElectrolysisRecipe, ElectrolysisMachineBlockEntity> {

    public ElectrolysisMachineMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public ElectrolysisMachineMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.ELECTROLYSIS_MACHINE, syncId, inventory, entity, arrayPropertyDelegate);
    }

}
