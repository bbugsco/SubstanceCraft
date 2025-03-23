package com.github.bbugsco.substancecraft.client.network;

import com.github.bbugsco.substancecraft.client.recipe.ClientRecipeInformation;
import com.github.bbugsco.substancecraft.network.payloads.RecipeCountPayload;
import com.github.bbugsco.substancecraft.network.payloads.RecipePayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClientNetworking {

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(RecipePayload.TYPE, (payload, context)
                -> context.client().execute(()
                -> ClientRecipeInformation.acceptRecipePacket(payload)));

        ClientPlayNetworking.registerGlobalReceiver(RecipeCountPayload.TYPE, (payload, context)
                -> context.client().execute(()
                -> ClientRecipeInformation.acceptRecipeCountPacket(payload)));
    }

}
