package com.github.bbugsco.substancecraft.client.recipe;

import com.github.bbugsco.substancecraft.recipe.SubstanceCraftRecipes;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ClientRecipeInformation {

    private static final HashMap<RecipeType<?>, List<RecipeHolder<?>>> recipesByType = new HashMap<>();

    public static void initClientRecipeList(List<RecipeHolder<?>> recipeList) {
        for (RecipeType<?> type : SubstanceCraftRecipes.types) {
            Collection<? extends RecipeHolder<?>> recipesOfType = recipeList.stream().filter(recipeHolder -> recipeHolder.value().getType() == type).toList();
            for (RecipeHolder<?> recipeHolder : recipesOfType) {
                if (!recipesByType.containsKey(type)) {
                    recipesByType.put(type, new ArrayList<>());
                }
                recipesByType.get(type).add(recipeHolder);
            }
        }
    }

    public static List<RecipeHolder<?>> getAllRecipesFor(RecipeType<?> type) {
        return recipesByType.get(type);
    }

}
