package com.github.bbugsco.substancecraft.block.entity.entities;

import com.github.bbugsco.substancecraft.block.entity.OneInputBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.menus.CatalyticReformerMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CatalyticReformerBlockEntity extends OneInputBlockEntity<CatalyticReformerRecipe> {

    public CatalyticReformerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Catalytic Reform", CatalyticReformerRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.CATALYTIC_REFORMER, true);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CatalyticReformerMenu(i, inventory, this, data);
    }

}
