package com.github.bbugsco.substancecraft.client.entity.hatman;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.client.entity.SubstanceCraftEntityRenderers;
import com.github.bbugsco.substancecraft.entities.hatman.HatMan;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class HatmanRenderer extends MobRenderer<HatMan, HatmanRenderState, HatManModel> {

    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/entity/hatman.png");

    public HatmanRenderer(EntityRendererProvider.Context context) {
        super(context, new HatManModel(context.bakeLayer(SubstanceCraftEntityRenderers.HATMAN_MODEL)), 0.5F);
    }

    @Override
    public @NotNull HatmanRenderState createRenderState() {
        return new HatmanRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(HatmanRenderState livingEntityRenderState) {
        return LOCATION;
    }
}
