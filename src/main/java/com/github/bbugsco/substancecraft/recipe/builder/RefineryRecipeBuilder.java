package com.github.bbugsco.substancecraft.recipe.builder;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class RefineryRecipeBuilder extends OneInputRecipeBuilder {

    private RefineryRecipeBuilder(final ItemLike result, final Ingredient ingredient, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<RefineryRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static RefineryRecipeBuilder refine(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<RefineryRecipe> factory) {
        return new RefineryRecipeBuilder(result, ingredient, byproducts, time, factory);
    }

    public static RefineryRecipeBuilder refine(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<RefineryRecipe> factory) {
        return new RefineryRecipeBuilder(result, ingredient, null, time, factory);
    }

}