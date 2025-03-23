package com.github.bbugsco.substancecraft.recipe.recipes;

import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class HeatedMixerRecipe extends MultipleInputRecipe {

    public static final String ID = "heated_mixer";

    public HeatedMixerRecipe(List<Ingredient> ingredients, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ingredients, result, byproducts, time);
    }


    public static class Type implements RecipeType<HeatedMixerRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<HeatedMixerRecipe> INSTANCE = new MultipleInputSerializer<>(HeatedMixerRecipe::new);
    }

}
