package com.github.bbugsco.substancecraft.block.blocks;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CornCrop extends CropBlock {

    public static final BooleanProperty UPPER = BooleanProperty.create("upper");
    private final MapCodec<CornCrop> CODEC = simpleCodec(CornCrop::new);

    private static final VoxelShape[] UPPER_SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)
    };

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };

    public CornCrop(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(UPPER, false));
    }

    @Override
    public @NotNull MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(CornCrop.UPPER) ? UPPER_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())]: SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    public BooleanProperty getUpperProperty() {
        return UPPER;
    }

    public int getGrowUpperAge() {
        return 4;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, UPPER);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        float growthRate = getGrowthSpeed(this, level, pos);
        int age = this.getAge(state);
        if (level.getRawBrightness(pos, 0) >= 9) {
            if (age < this.getMaxAge()) {
                if (random.nextInt((int)(25.0F / growthRate) + 1) == 0) {
                    level.setBlock(pos, this.getStateForAge(age + 1), 2);
                }
            }
        } if (state.getValue(UPPER)) {
            return;
        } if (age >= this.getGrowUpperAge()) {
            // if (random.nextInt((int)(25.0F / growthRate) + 1) == 0) {

                level.setBlock(pos.above(1), state.setValue(AGE, 0).setValue(UPPER, true), Block.UPDATE_CLIENTS);
                level.setBlockAndUpdate(pos.above(), this.defaultBlockState().setValue(this.getUpperProperty(), true));
            // }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        BlockState upperState = level.getBlockState(pos.above());
        if (upperState.is(this)) {
            return !(this.isMaxAge(upperState));
        }
        if (state.getValue(this.getUpperProperty())) {
            return !(this.isMaxAge(state));
        }
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int ageGrowth = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(level), 15);
        if (ageGrowth <= this.getMaxAge()) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth));
        } else {
            level.setBlockAndUpdate(pos, state.setValue(AGE, this.getMaxAge()));
            if (state.getValue(this.getUpperProperty())) {
                return;
            }
            BlockState top = level.getBlockState(pos.above());
            if (top.is(this)) {
                BonemealableBlock growable = (BonemealableBlock) level.getBlockState(pos.above()).getBlock();
                if (growable.isValidBonemealTarget(level, pos.above(), top)) {
                    growable.performBonemeal(level, level.random, pos.above(), top);
                }
            } else {
                int remainingGrowth = ageGrowth - this.getMaxAge() - 1;
                if (this.defaultBlockState().canSurvive(level, pos.above()) && level.isEmptyBlock(pos.above())) {
                    level.setBlock(pos.above(), this.defaultBlockState()
                            .setValue(this.getUpperProperty(), true)
                            .setValue(this.getAgeProperty(), remainingGrowth), 3);
                }
            }
        }
    }
}
