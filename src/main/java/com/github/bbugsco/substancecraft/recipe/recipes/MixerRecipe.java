package com.github.bbugsco.substancecraft.recipe.recipes;

import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class MixerRecipe extends MultipleInputRecipe {

    public static final String ID = "mixer";

    public MixerRecipe(List<Ingredient> ingredients, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ingredients, result, byproducts, time);
    }

    public static class Type implements RecipeType<MixerRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<MixerRecipe> INSTANCE = new MultipleInputSerializer<>(MixerRecipe::new);
    }

}
