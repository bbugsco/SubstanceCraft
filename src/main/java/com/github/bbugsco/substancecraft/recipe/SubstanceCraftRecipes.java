package com.github.bbugsco.substancecraft.recipe;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.network.payloads.RecipeCountPayload;
import com.github.bbugsco.substancecraft.network.payloads.RecipePayload;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.ExtractorRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.FermentationTankRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.HeatedMixerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.MixerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, HashPressRecipe.ID), HashPressRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  HashPressRecipe.ID), HashPressRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, ElectrolysisRecipe.ID), ElectrolysisRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  ElectrolysisRecipe.ID), ElectrolysisRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, OxidizerRecipe.ID), OxidizerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  OxidizerRecipe.ID), OxidizerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, CatalyticReformerRecipe.ID), CatalyticReformerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  CatalyticReformerRecipe.ID), CatalyticReformerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, ExtractorRecipe.ID), ExtractorRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  ExtractorRecipe.ID), ExtractorRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, MixerRecipe.ID), MixerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  MixerRecipe.ID), MixerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, HeatedMixerRecipe.ID), HeatedMixerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  HeatedMixerRecipe.ID), HeatedMixerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, FermentationTankRecipe.ID), FermentationTankRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  FermentationTankRecipe.ID), FermentationTankRecipe.Serializer.INSTANCE);

        ServerLifecycleEvents.SERVER_STARTING.register(SubstanceCraftRecipes::initRecipeList);
        ServerPlayConnectionEvents.JOIN.register(SubstanceCraftRecipes::sendRecipesToClient);
        ServerPlayConnectionEvents.JOIN.register(SubstanceCraftRecipes::sendRecipeCountToClient);
    }

    private static void sendRecipeCountToClient(ServerGamePacketListenerImpl serverGamePacketListener, PacketSender packetSender, MinecraftServer minecraftServer) {
        ServerPlayNetworking.send(serverGamePacketListener.player, new RecipeCountPayload(count));
    }

    private static void sendRecipesToClient(ServerGamePacketListenerImpl serverGamePacketListener, PacketSender packetSender, MinecraftServer minecraftServer) {
        for (List<RecipeHolder<?>> recipes : recipesByType.values()) {
            for (RecipeHolder<?> recipe : recipes) {
                ServerPlayNetworking.send(serverGamePacketListener.player, new RecipePayload(recipe));
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
    public static List<RecipeHolder<?>> getAllRecipesFor(RecipeType<?> type) {
        List<RecipeHolder<?>> ofType = recipesByType.get(type);
        return ofType == null ? new ArrayList<>() : ofType;
    }

}
