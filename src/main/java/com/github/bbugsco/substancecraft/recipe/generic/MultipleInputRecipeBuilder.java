package com.github.bbugsco.substancecraft.recipe.generic;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MultipleInputRecipeBuilder implements RecipeBuilder {

    private final List<Ingredient> ingredients;
    private final Item result;
    private final List<ItemStack> byproducts;
    private final int time;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final MultipleInputRecipe.Factory<? extends MultipleInputRecipe> factory;

    protected MultipleInputRecipeBuilder(final List<Ingredient> ingredients, final ItemLike result, List<ItemStack> byproducts, int time, MultipleInputRecipe.Factory<? extends MultipleInputRecipe> factory) {
        this.ingredients = ingredients;
        this.result = result.asItem();
        this.byproducts = byproducts == null ? List.of() : byproducts;
        this.time = time;
        this.factory = factory;
    }

    @Override
    public @NotNull MultipleInputRecipeBuilder unlockedBy(String string, Criterion<?> advancementCriterion) {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result;
    }

    @Override
    public void save(RecipeOutput exporter, ResourceLocation recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::addCriterion);
        int count = 1;
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null && ingredient.test(new ItemStack(result))) {
                count = 2;
                break;
            }
        }
        MultipleInputRecipe recipeFactory = this.factory.create(this.ingredients, new ItemStack(this.result, count), this.byproducts, this.time);
        exporter.accept(recipeId, recipeFactory, builder.build(recipeId.withPrefix("recipes/")));
    }

    private void validate(ResourceLocation recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

}
