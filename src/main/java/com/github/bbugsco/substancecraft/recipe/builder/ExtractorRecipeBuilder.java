package com.github.bbugsco.substancecraft.recipe.builder;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.ExtractorRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ExtractorRecipeBuilder extends OneInputRecipeBuilder {

    protected ExtractorRecipeBuilder(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<? extends OneInputRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static ExtractorRecipeBuilder extract(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<ExtractorRecipe> factory) {
        return new ExtractorRecipeBuilder(ingredient, result, byproducts, time, factory);
    }

    public static ExtractorRecipeBuilder extract(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<ExtractorRecipe> factory) {
        return new ExtractorRecipeBuilder(ingredient, result, null, time, factory);
    }

}
