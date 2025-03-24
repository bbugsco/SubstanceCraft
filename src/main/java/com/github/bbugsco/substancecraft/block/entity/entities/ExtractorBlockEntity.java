package com.github.bbugsco.substancecraft.block.entity.entities;

import com.github.bbugsco.substancecraft.block.entity.OneInputBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.menus.ExtractorMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.ExtractorRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ExtractorBlockEntity extends OneInputBlockEntity<ExtractorRecipe> {

    public ExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Extractor", ExtractorRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.EXTRACTOR, true);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ExtractorMenu(i, inventory, this, data);
    }
}
