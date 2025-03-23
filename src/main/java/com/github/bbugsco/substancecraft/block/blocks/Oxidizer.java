package com.github.bbugsco.substancecraft.block.blocks;

import com.github.bbugsco.substancecraft.block.GenericMenuBlock;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.block.entity.entities.OxidizerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class Oxidizer extends GenericMenuBlock<OxidizerBlockEntity> {

    public Oxidizer(Properties properties) {
        super(properties, simpleCodec(Oxidizer::new), Shapes.block());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OxidizerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, SubstanceCraftBlockEntities.OXIDIZER, (world1, pos, blockState, blockEntity) -> blockEntity.tick(world1, pos, blockState));
    }

}
