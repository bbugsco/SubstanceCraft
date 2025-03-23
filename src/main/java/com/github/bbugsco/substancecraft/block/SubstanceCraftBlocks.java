package com.github.bbugsco.substancecraft.block;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.function.Function;

public class SubstanceCraftBlocks {

    private static final HashMap<Block, Item> BLOCK_ITEMS = new HashMap<>();

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
