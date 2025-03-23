package com.github.bbugsco.substancecraft.block.entity.entities;

import com.github.bbugsco.substancecraft.block.entity.MultiInputBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HeatedMixerBlockEntity extends MultiInputBlockEntity<HeatedMixerRecipe> {

    public HeatedMixerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Heated Mixer", HeatedMixerRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.HEATED_MIXER, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new HeatedMixerMenu(i, inventory, this, dataAccess);
    }


}
