package com.github.bbugsco.substancecraft.block.blocks;

import com.github.bbugsco.substancecraft.block.GenericMenuBlock;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.block.entity.entities.ElectrolysisMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class ElectrolysisMachine extends GenericMenuBlock<ElectrolysisMachineBlockEntity> {

    public ElectrolysisMachine(Properties settings) {
        super(settings, simpleCodec(ElectrolysisMachine::new), Shapes.block());
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ElectrolysisMachineBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, SubstanceCraftBlockEntities.ELECTROLYSIS_MACHINE, (world1, pos, blockState, blockEntity) -> blockEntity.tick(world1, pos, blockState));
    }

}
