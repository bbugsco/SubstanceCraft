package com.github.bbugsco.substancecraft.recipe.generic;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultipleInputRecipe implements Recipe<MultipleItemInput> {

    private final RecipeType<? extends MultipleInputRecipe> type;
    private final RecipeSerializer<? extends MultipleInputRecipe> serializer;

    protected final List<Ingredient> ingredients;
    protected final ItemStack result;
    protected final List<ItemStack> byproducts;
    protected final int time;

    public MultipleInputRecipe
            (RecipeType<? extends MultipleInputRecipe> type,
             RecipeSerializer<? extends MultipleInputRecipe> serializer,
             List<Ingredient> ingredients, ItemStack result, List<ItemStack> byproducts, int time)
    {
        this.type = type;
        this.serializer = serializer;
        this.ingredients = ingredients != null ? ingredients : List.of();
        this.result = result;
        this.byproducts = byproducts;
        this.time = time;
    }

    @NotNull
    public List<Ingredient> getInputs() {
        return ingredients;
    }

    @NotNull
    public ItemStack getResult() {
        return result;
    }

    @NotNull
    public List<ItemStack> getByproducts() {
        return byproducts;
    }

    public int getTime() {
        return time;
    }
    public int time() {
        return time;
    }

    @Override
    public boolean matches(MultipleItemInput input, Level level) {
        if (level.isClientSide()) return false;
        if (input.size() != ingredients.size()) return false;
        for (Ingredient ingredient : ingredients) {
            boolean ingredientInInput = false;
            for (ItemStack inputItem : input.items()) {
                if (ingredient.test(inputItem)) {
                    ingredientInInput = true;
                    break;
                }
            }
            if (!ingredientInInput) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(MultipleItemInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registries) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return serializer;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return type;
    }

    public interface Factory<T extends MultipleInputRecipe> {
        T create(List<Ingredient> ingredient, ItemStack itemStack, List<ItemStack> byproducts, int time);
    }

}
