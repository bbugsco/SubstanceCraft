package com.github.bbugsco.substancecraft.client.gui;

import com.github.bbugsco.substancecraft.block.entity.MultiInputBlockEntity;
import com.github.bbugsco.substancecraft.gui.MultipleInputMenu;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class MultipleInputScreen<R extends MultipleInputRecipe, E extends MultiInputBlockEntity<R>, T extends MultipleInputMenu<R, E>> extends InputOutputScreen<T> {

    public MultipleInputScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected List<Component> tooltip(int index) {
        MultipleInputRecipe recipe = this.menu.getRecipes().get(index).value();
        List<Ingredient> inputs = recipe.getInputs();
        ItemStack resultItem = recipe.getResult();
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(getItemNameString(resultItem));
        tooltip.add(Component.literal("Requires: "));
        Set<Holder<Item>> itemInputsSet = inputs.stream().flatMap(Ingredient::items).collect(Collectors.toUnmodifiableSet());
        for (Holder<Item> item : itemInputsSet) {
            tooltip.add(getItemNameString(new ItemStack(item.value())));
        }
        if (!recipe.getByproducts().isEmpty()) {
            tooltip.add(Component.literal("Byproducts: "));
            List<ItemStack> byproducts = recipe.getByproducts();
            for (ItemStack byproduct : byproducts) {
                tooltip.add(getByproductString(byproduct, byproduct.getCount() << 1));
            }
        }
        return tooltip;
    }

    @Override
    protected void renderRecipes(GuiGraphics guiGraphics, int x, int y, int startIndex) {
        List<RecipeHolder<R>> list = this.menu.getRecipes();
        for (int index = this.firstVisibleIndex; index < startIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int renderX = x + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = relativeIndex / RECIPES_COLUMNS;
            int renderY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            guiGraphics.renderItem(list.get(index).value().getResult(), renderX, renderY);
        }
    }

}
