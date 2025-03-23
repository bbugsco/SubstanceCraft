package com.github.bbugsco.substancecraft.recipe.builder;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class CatalyticReformerRecipeBuilder extends OneInputRecipeBuilder {

    protected CatalyticReformerRecipeBuilder(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<CatalyticReformerRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static CatalyticReformerRecipeBuilder reform(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<CatalyticReformerRecipe> factory) {
        return new CatalyticReformerRecipeBuilder(ingredient, result, byproducts, time, factory);
    }

    public static CatalyticReformerRecipeBuilder reform(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<CatalyticReformerRecipe> factory) {
        return new CatalyticReformerRecipeBuilder(ingredient, result, null, time, factory);
    }

}
