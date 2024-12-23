package com.github.bbugsco.substancecraft.block;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.blocks.AirExtractor;
import com.github.bbugsco.substancecraft.block.blocks.CatalyticReformer;
import com.github.bbugsco.substancecraft.block.blocks.CornCrop;
import com.github.bbugsco.substancecraft.block.blocks.ElectrolysisMachine;
import com.github.bbugsco.substancecraft.block.blocks.FermentationTank;
import com.github.bbugsco.substancecraft.block.blocks.HashPress;
import com.github.bbugsco.substancecraft.block.blocks.HeatedMixer;
import com.github.bbugsco.substancecraft.block.blocks.MarijuanaPlant;
import com.github.bbugsco.substancecraft.block.blocks.Mixer;
import com.github.bbugsco.substancecraft.block.blocks.Oxidizer;
import com.github.bbugsco.substancecraft.block.blocks.Refinery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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

public class SubstanceCraftBlocks {

    private static final HashMap<Block, Item> BLOCK_ITEMS = new HashMap<>();

    public static final Block MARIJUANA_PLANT = registerBlock("marijuana_plant", new MarijuanaPlant(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final Block HASH_PRESS = registerBlock("hash_press", new HashPress(BlockBehaviour.Properties.of().strength(3.5F)));
    public static final Block REFINERY = registerBlock("refinery", new Refinery(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block OIL_SHALE = registerBlock("oil_shale", new Block(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRAVEL)));
    public static final Block ELECTROLYSIS_MACHINE = registerBlock("electrolysis", new ElectrolysisMachine(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block OXIDATION_MACHINE = registerBlock("oxidation_machine", new Oxidizer(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block CATALYTIC_REFORMER = registerBlock("catalytic_reformer", new CatalyticReformer(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block AIR_EXTRACTOR = registerBlock("air_extractor", new AirExtractor(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block SALT = registerBlock("salt_block", new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.CALCITE)));
    public static final Block MIXER = registerBlock("mixer", new Mixer(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block HEATED_MIXER = registerBlock("heated_mixer", new HeatedMixer(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block FERMENTATION_TANK = registerBlock("fermentation_tank", new FermentationTank(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block CORN_CROP = registerBlock("corn_crop", new CornCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static Item getBlockItem(Block block) {
        return BLOCK_ITEMS.get(block);
    }

    private static Block registerBlock(String name, Block block) {
        BLOCK_ITEMS.put(block, registerBlockItem(name, block));
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name), new BlockItem(block, new Item.Properties()));
    }

    public static void registerBlocks() {
        SubstanceCraft.LOGGER.info("Registering Blocks for " + SubstanceCraft.MOD_ID);
    }

}
