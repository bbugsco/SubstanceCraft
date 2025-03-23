package com.github.bbugsco.substancecraft.block;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.blocks.CatalyticReformer;
import com.github.bbugsco.substancecraft.block.blocks.CornCrop;
import com.github.bbugsco.substancecraft.block.blocks.ElectrolysisMachine;
import com.github.bbugsco.substancecraft.block.blocks.Extractor;
import com.github.bbugsco.substancecraft.block.blocks.FermentationTank;
import com.github.bbugsco.substancecraft.block.blocks.HashPress;
import com.github.bbugsco.substancecraft.block.blocks.HeatedMixer;
import com.github.bbugsco.substancecraft.block.blocks.MarijuanaPlant;
import com.github.bbugsco.substancecraft.block.blocks.Mixer;
import com.github.bbugsco.substancecraft.block.blocks.Oxidizer;
import com.github.bbugsco.substancecraft.block.blocks.Refinery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;
import java.util.function.Function;

public class SubstanceCraftBlocks {

    private static final HashMap<Block, Item> BLOCK_ITEMS = new HashMap<>();

    public static final Block MARIJUANA_PLANT = registerBlock("marijuana_plant", MarijuanaPlant::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    public static final Block HASH_PRESS = registerBlock("hash_press", HashPress::new, BlockBehaviour.Properties.of().strength(3.5F));
    public static final Block REFINERY = registerBlock("refinery", Refinery::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block OIL_SHALE = registerBlock("oil_shale", Block::new, BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRAVEL));
    public static final Block ELECTROLYSIS_MACHINE = registerBlock("electrolysis", ElectrolysisMachine::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block OXIDATION_MACHINE = registerBlock("oxidation_machine", Oxidizer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block CATALYTIC_REFORMER = registerBlock("catalytic_reformer", CatalyticReformer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block EXTRACTOR = registerBlock("extractor", Extractor::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block SALT = registerBlock("salt_block", Block::new, BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.CALCITE));
    public static final Block MIXER = registerBlock("mixer", Mixer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block HEATED_MIXER = registerBlock("heated_mixer", HeatedMixer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block FERMENTATION_TANK = registerBlock("fermentation_tank", FermentationTank::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block CORN_CROP = registerBlock("corn_crop", CornCrop::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));


    public static Item getBlockItem(Block block) {
        return BLOCK_ITEMS.get(block);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = key(name);
        Block block = factory.apply(properties.setId(key));
        BLOCK_ITEMS.put(block, registerBlockItem(name, block));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static Item registerBlockItem(String name, Block block) {
        ResourceKey<Item> key = itemKey(name);
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(key)));
    }

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
    }

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
    }

    public static void registerBlocks() {

    }

}
