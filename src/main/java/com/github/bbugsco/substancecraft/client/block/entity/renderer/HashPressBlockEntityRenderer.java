package com.github.bbugsco.substancecraft.client.block.entity.renderer;

import com.github.bbugsco.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class HashPressBlockEntityRenderer implements BlockEntityRenderer<HashPressBlockEntity> {

    @Override
    public void render(HashPressBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = entity.getRenderStack();
        matrices.pushPose();
        matrices.translate(0.5f, 0.95f, 0.5f);
        matrices.scale(0.35f, 0.35f, 0.35f);
        matrices.mulPose(Axis.XP.rotationDegrees(270));
        itemRenderer.renderStatic(
                itemStack,
                ItemDisplayContext.GUI,
                getLightLevel(Objects.requireNonNull(entity.getLevel()), entity.getBlockPos()),
                OverlayTexture.NO_OVERLAY,
                matrices,
                vertexConsumers,
                entity.getLevel(),
                1
        );
        matrices.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
