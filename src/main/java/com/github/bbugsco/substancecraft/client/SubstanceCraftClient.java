package com.github.bbugsco.substancecraft.client;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.bbugsco.substancecraft.client.entity.SubstanceCraftEntityRenderers;
import com.github.bbugsco.substancecraft.client.gui.SubstanceCraftScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SubstanceCraftBlocks.CORN_CROP, RenderType.cutout());
        BlockEntityRenderers.register(SubstanceCraftBlockEntities.HASH_PRESS, context -> new HashPressBlockEntityRenderer());

        SubstanceCraftScreens.registerScreens();
        SubstanceCraftEntityRenderers.registerEntityRenderers();
    }

}
