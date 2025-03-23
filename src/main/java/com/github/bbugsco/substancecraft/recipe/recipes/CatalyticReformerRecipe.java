package com.github.bbugsco.substancecraft.recipe.recipes;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class CatalyticReformerRecipe extends OneInputRecipe {

    public static final String ID = "catalytic_reformer";

    public CatalyticReformerRecipe(Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, byproducts, time);
    }

    public static class Type implements RecipeType<CatalyticReformerRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<CatalyticReformerRecipe> INSTANCE = new OneInputRecipeSerializer<>(CatalyticReformerRecipe::new);
    }

}
