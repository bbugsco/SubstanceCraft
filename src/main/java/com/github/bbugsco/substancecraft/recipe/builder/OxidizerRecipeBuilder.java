package com.github.bbugsco.substancecraft.recipe.builder;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class OxidizerRecipeBuilder extends OneInputRecipeBuilder {

    private OxidizerRecipeBuilder(final ItemLike result, final Ingredient ingredient, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<OxidizerRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static OxidizerRecipeBuilder oxidize(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<OxidizerRecipe> factory) {
        return new OxidizerRecipeBuilder(result, ingredient, byproducts, time, factory);
    }

    public static OxidizerRecipeBuilder oxidize(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<OxidizerRecipe> factory) {
        return new OxidizerRecipeBuilder(result, ingredient, null, time, factory);
    }

}
