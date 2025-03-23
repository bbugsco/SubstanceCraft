package com.github.bbugsco.substancecraft.network;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.network.payloads.RecipeCountPayload;
import com.github.bbugsco.substancecraft.network.payloads.RecipePayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.resources.ResourceLocation;

public class SubstanceCraftNetworking {

    public static final ResourceLocation RECIPE_PAYLOAD_TYPE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "recipe_payload_type");
    public static final ResourceLocation RECIPE_COUNT_PAYLOAD_TYPE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "recipe_count_payload_type");

    public static void init() {
        PayloadTypeRegistry.playS2C().register(RecipePayload.TYPE, RecipePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(RecipeCountPayload.TYPE, RecipeCountPayload.CODEC);
    }

}
