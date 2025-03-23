package com.github.bbugsco.substancecraft.recipe;

import com.github.bbugsco.substancecraft.client.recipe.ClientRecipeInformation;
import com.github.bbugsco.substancecraft.network.payloads.RecipePayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SubstanceCraftRecipes {

    private static int count = 0;
    private static final HashMap<RecipeType<?>, List<RecipeHolder<?>>> recipesByType = new HashMap<>();
    public static final ArrayList<RecipeType<?>> types = new ArrayList<>();

    public static void registerRecipes() {
        ServerLifecycleEvents.SERVER_STARTING.register(SubstanceCraftRecipes::initRecipeList);
        ServerPlayConnectionEvents.JOIN.register(SubstanceCraftRecipes::sendRecipesToClient);
    }

    private static void sendRecipesToClient(ServerGamePacketListenerImpl serverGamePacketListener, PacketSender packetSender, MinecraftServer minecraftServer) {
        for (List<RecipeHolder<?>> recipes : recipesByType.values()) {
            for (RecipeHolder<?> recipe : recipes) {
                ServerPlayNetworking.send(serverGamePacketListener.player, new RecipePayload(count, recipe));
            }
        }
    }

    private static void initRecipeList(MinecraftServer server) {
        RecipeManager recipeManager = server.getRecipeManager();
        for (RecipeType<?> type : types) {
            Collection<? extends RecipeHolder<?>> recipesOfType = recipeManager.getRecipes().stream().filter(recipeHolder -> recipeHolder.value().getType() == type).toList();
            for (RecipeHolder<?> recipeHolder : recipesOfType) {
                if (!recipesByType.containsKey(type)) {
                    recipesByType.put(type, new ArrayList<>());
                }
                recipesByType.get(type).add(recipeHolder);
                count++;
            }
        }
    }

    @NotNull
    public static List<RecipeHolder<?>> getAllRecipesFor(RecipeType<?> type, boolean client) {
        if (client) {
            if (ClientRecipeInformation.recipesLoaded) {
                return ClientRecipeInformation.getAllRecipesFor(type);
            } else {
                return List.of();
            }
        } else {
            return recipesByType.get(type);
        }
    }

}
