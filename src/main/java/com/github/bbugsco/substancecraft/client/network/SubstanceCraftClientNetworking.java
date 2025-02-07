package com.github.bbugsco.substancecraft.client.network;

import com.github.bbugsco.substancecraft.client.recipe.ClientRecipeInformation;
import com.github.bbugsco.substancecraft.network.payloads.ModdedRecipesPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClientNetworking {

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(ModdedRecipesPayload.TYPE, (payload, context) -> {
            context.client().execute(() -> {
                ClientRecipeInformation.initClientRecipeList(payload.recipes());
            });
        });
    }

}
