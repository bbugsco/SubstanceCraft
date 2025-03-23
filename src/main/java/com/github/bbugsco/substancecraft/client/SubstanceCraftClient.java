package com.github.bbugsco.substancecraft.client;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.bbugsco.substancecraft.client.datagen.ModelGenerator;
import com.github.bbugsco.substancecraft.client.gui.SubstanceCraftScreens;
import com.github.bbugsco.substancecraft.client.network.SubstanceCraftClientNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClient implements ClientModInitializer, DataGeneratorEntrypoint {

    @Override
    public void onInitializeClient() {
        registerRenderLayers();
        registerBlockEntityRenderers();

        SubstanceCraftScreens.registerScreens();
        SubstanceCraftClientNetworking.init();
    }

    private void registerRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SubstanceCraftBlocks.CORN_CROP, RenderType.cutout());
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(SubstanceCraftBlockEntities.HASH_PRESS, context -> new HashPressBlockEntityRenderer());
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelGenerator::new);
    }

}
