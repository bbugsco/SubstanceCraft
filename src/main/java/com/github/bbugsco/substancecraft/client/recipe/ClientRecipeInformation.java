package com.github.bbugsco.substancecraft.client.recipe;

import com.github.bbugsco.substancecraft.network.payloads.RecipeCountPayload;
import com.github.bbugsco.substancecraft.network.payloads.RecipePayload;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientRecipeInformation {

    public static boolean recipesLoaded = false;

    private static int receivedPackets = 0;
    private static int expectedPackets = -1;
    private static final List<RecipePayload> buffer = new ArrayList<>();
    private static final HashMap<RecipeType<?>, List<RecipeHolder<?>>> recipesByType = new HashMap<>();

    public static void acceptRecipePacket(RecipePayload payload) {
        buffer.add(payload);
        receivedPackets++;
        if (expectedPackets != -1 && receivedPackets == expectedPackets) {
            createList();
        }
    }

    public static void acceptRecipeCountPacket(RecipeCountPayload payload) {
        expectedPackets = payload.totalRecipes();
        if (receivedPackets == expectedPackets) {
            createList();
        }
    }

    private static void createList() {
        for (RecipePayload payload : buffer) {
            RecipeHolder<?> holder = payload.recipe();
            recipesByType.putIfAbsent(holder.value().getType(), new ArrayList<>());
            recipesByType.get(holder.value().getType()).add(holder);
        }
        recipesLoaded = true;
    }

    public static List<RecipeHolder<?>> getAllRecipesFor(RecipeType<?> type) {
        List<RecipeHolder<?>> ofType = recipesByType.get(type);
        return ofType == null ? new ArrayList<>() : ofType;
    }
}
