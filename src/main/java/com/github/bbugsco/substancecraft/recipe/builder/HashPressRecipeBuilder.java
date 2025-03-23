package com.github.bbugsco.substancecraft.recipe.builder;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class HashPressRecipeBuilder extends OneInputRecipeBuilder {

    private HashPressRecipeBuilder(final ItemLike result, final Ingredient ingredient, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<HashPressRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static HashPressRecipeBuilder press(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<HashPressRecipe> factory) {
        return new HashPressRecipeBuilder(result, ingredient, byproducts, time, factory);
    }

    public static HashPressRecipeBuilder press(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<HashPressRecipe> factory) {
        return new HashPressRecipeBuilder(result, ingredient, null, time, factory);
    }

}