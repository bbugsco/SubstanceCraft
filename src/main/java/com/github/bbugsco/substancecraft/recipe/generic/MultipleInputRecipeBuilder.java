package com.github.bbugsco.substancecraft.recipe.generic;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MultipleInputRecipeBuilder implements RecipeBuilder {

    private final List<Ingredient> ingredients;
    private final Item result;
    private final List<ItemStack> byproducts;
    private final int time;

    private int outputCount = 1;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final MultipleInputRecipe.Factory<? extends MultipleInputRecipe> factory;

    protected MultipleInputRecipeBuilder(
            final List<Ingredient> ingredients,
            final ItemLike result,
            List<ItemStack> byproducts,
            int time,
            MultipleInputRecipe.Factory<? extends MultipleInputRecipe> factory)
    {
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

    public MultipleInputRecipeBuilder setOutputCount(int outputCount) {
        if (outputCount < 1 || outputCount > 64) {
            SubstanceCraft.LOGGER.warn("Invalid outputCount in recipe: {} not in range [1,64]", outputCount);
            return this;
        }
        this.outputCount = outputCount;
        return this;
    }

    @Override
    public @NotNull MultipleInputRecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result;
    }

    @Override
    public void save(RecipeOutput exporter, ResourceKey<Recipe<?>> resourceKey) {
        this.validate(resourceKey);
        Advancement.Builder advancementBuilder = exporter.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey)).rewards(AdvancementRewards.Builder.recipe(resourceKey)).requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);
        MultipleInputRecipe recipe = this.factory.create(this.ingredients, new ItemStack(this.result, outputCount), this.byproducts, this.time);
        exporter.accept(resourceKey, recipe, advancementBuilder.build(resourceKey.location().withPrefix("recipes/")));
    }

    private void validate(ResourceKey<Recipe<?>> resourceKey) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + resourceKey.location());
        }
    }

}
