package com.github.bbugsco.substancecraft.block.entity.entities;

import com.github.bbugsco.substancecraft.block.entity.MultiInputBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.menus.MixerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.MixerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MixerBlockEntity extends MultiInputBlockEntity<MixerRecipe> {

    public MixerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Mixer", MixerRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.MIXER, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MixerMenu(i, inventory, this, dataAccess);
    }


}
