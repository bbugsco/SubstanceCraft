package com.github.bbugsco.substancecraft.block.blocks;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MarijuanaPlant extends BushBlock implements BonemealableBlock {

    public static final MapCodec<MarijuanaPlant> CODEC = simpleCodec(MarijuanaPlant::new);
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final int oneBlockMaxAge = 4;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0)
    };

    public MarijuanaPlant(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public @NotNull MapCodec<MarijuanaPlant> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[state.getValue(AGE)];
    }

    @Override
    protected @NotNull ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            if (state.getValue(AGE) < MAX_AGE) {
                this.performBonemeal(level, level.random, pos, state);
            }
        }
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (level.getBlockState(pos.above()).is(this)) {
                age = level.getBlockState(pos.above()).getValue(AGE);
            }
        }
        boolean isMaxAge = age == MAX_AGE;
        return !isMaxAge && stack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        boolean harvest = false;
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            BlockState upper = level.getBlockState(pos.above());
            if (upper.is(this)) {
                int age = upper.getValue(AGE);
                if (age == MAX_AGE) {
                    harvest = true;
                }
            }
        } else {
            int age = state.getValue(AGE);
            if (age == MAX_AGE) {
                harvest = true;
            }
        }
        if (harvest) {
            popResource(level, pos, new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), 1));
            popResource(level, pos, new ItemStack(SubstanceCraftItems.MARIJUANA, 1));
            popResource(level, pos, new ItemStack(SubstanceCraftItems.MARIJUANA_TRIM, 1 + level.random.nextInt(2) == 1 ? 1 : 0));
            popResource(level, pos, new ItemStack(SubstanceCraftItems.MARIJUANA_TRIM, 1 + level.random.nextInt(2) == 1 ? 1 : 0));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                level.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                level.setBlock(pos, state.setValue(AGE, oneBlockMaxAge), Block.UPDATE_CLIENTS);
            } else {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                level.setBlock(pos.below(), state.setValue(AGE, oneBlockMaxAge).setValue(HALF, DoubleBlockHalf.LOWER), Block.UPDATE_CLIENTS);
            }
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.800000011920929, 0.75, 0.800000011920929));
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (age < oneBlockMaxAge) {
                level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
            } else if (age == oneBlockMaxAge && level.getBlockState(pos.above(1)).is(Blocks.AIR)) {
                level.setBlock(pos.above(1), state.setValue(AGE, age + 1).setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_CLIENTS);
            } else {
                BlockState upper = level.getBlockState(pos.above());
                if (upper.is(this) && upper.getValue(HALF) == DoubleBlockHalf.UPPER) {
                    int upperAge = upper.getValue(AGE);
                    if (upperAge < MAX_AGE) {
                        level.setBlock(pos.above(), upper.setValue(AGE, upperAge + 1), Block.UPDATE_CLIENTS);
                    } else {
                        level.setBlock(pos.above(), upper.setValue(AGE, upperAge), Block.UPDATE_CLIENTS);
                    }
                }
            }
        } else if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            if (age < MAX_AGE) {
                level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
            } else {
                level.setBlock(pos, state.setValue(AGE, age), Block.UPDATE_CLIENTS);
            }
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos, MarijuanaPlant.withWaterloggedState(level, pos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER)), Block.UPDATE_ALL);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = level.getBlockState(pos.below());
            return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                MarijuanaPlant.onBreakInCreative(level, pos, state, player);
            } else {
                if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                    popResource(level,  pos, new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), 1));
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    private static void onBreakInCreative(Level level, BlockPos pos, BlockState state, Player player) {
        BlockPos blockPos;
        BlockState blockState;
        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER && (blockState = level.getBlockState(blockPos = pos.below())).is(state.getBlock()) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            BlockState blockState2 = blockState.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
            level.setBlock(blockPos, blockState2, Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
            level.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK, blockPos, Block.getId(blockState));
        }
    }

    private static BlockState withWaterloggedState(LevelReader levelReader, BlockPos pos, BlockState state) {
        if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
            return state.setValue(BlockStateProperties.WATERLOGGED, levelReader.isWaterAt(pos));
        }
        return state;
    }

}
