package com.github.bbugsco.substancecraft.recipe.generic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record MultipleItemInput(List<ItemStack> items) implements RecipeInput {

    @Override
    public @NotNull ItemStack getItem(int index) {
        if (!(index < items.size())) {
            throw new IllegalArgumentException("No item for index " + index);
        } else {
            return items.get(index);
        }
    }

    @Override
    public int size() {
        return items.size();
    }
}
