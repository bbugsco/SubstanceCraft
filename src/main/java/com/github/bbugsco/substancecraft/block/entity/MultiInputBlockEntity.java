package com.github.bbugsco.substancecraft.block.entity;

import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleItemInput;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MultiInputBlockEntity<T extends MultipleInputRecipe> extends InputOutputBlockEntity implements RecipeList<T> {

    public static final int FIRST_INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 4;
    public static final int FIRST_BYPRODUCT_SLOT = 5;

    public final RecipeManager.CachedCheck<MultipleItemInput, T> matchGetter;
    private final RecipeType<T> type;
    private final List<RecipeHolder<T>> recipes;
    private final boolean hasRepeatInputRecipes;

    public MultiInputBlockEntity(BlockPos pos, BlockState state, String displayName, RecipeType<T> type, BlockEntityType<?> blockEntityType, boolean hasRepeatInputRecipes) {
        super(blockEntityType, pos, state, displayName, 8);
        this.matchGetter = RecipeManager.createCheck(type);
        this.hasRepeatInputRecipes = hasRepeatInputRecipes;
        this.type = type;
        this.recipes = new ArrayList<>();
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        setupRecipeList(level);
    }

    @Override
    public int getMaxByproducts() {
        int max = 0;
        for (RecipeHolder<T> holder : recipes) {
            if (holder.value().getByproducts().size() > max)
                max = holder.value().getByproducts().size();
        }
        return max;
    }

    @Override
    public boolean multipleInput() {
        return true;
    }

    @Override
    public int getNumRecipes() {
        return recipes.size();
    }

    @Override
    public List<RecipeHolder<T>> getRecipes() {
        return this.recipes;
    }

    @SuppressWarnings("unchecked")
    public void setupRecipeList(Level level) {
        this.recipes.clear();
        List<RecipeHolder<?>> allRecipes = getRecipeList(type, level);
        for (RecipeHolder<?> recipeHolder : allRecipes) {
            recipes.add((RecipeHolder<T>) recipeHolder);
        }
    }

    @Override
    public boolean hasRepeatInputRecipes() {
        return hasRepeatInputRecipes;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean itemsEqual = !stack.isEmpty() && ItemStack.isSameItem(itemStack, stack);
        this.inventory.set(slot, stack);
        if (slot >= FIRST_INPUT_SLOT && slot < OUTPUT_SLOT && !itemsEqual) {
            this.maxProgress = getCookTime();
            this.progress = 0;
            this.setChanged();
        }
    }

    public Optional<RecipeHolder<T>> getCurrentRecipe() {
        List<RecipeHolder<T>> recipes = getRecipes();
        if (recipes.isEmpty()) {
            return Optional.empty();
        } else {
            int index = getSelectedRecipeIndex();
            if (index > -1 && index < recipes.size()) {
                return Optional.of(recipes.get(index));
            } else return Optional.empty();
        }
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;
        super.updateState(state, level, pos);
        if (isSlotEmptyOrReceivable(OUTPUT_SLOT)) {
            if (hasRecipe()) {
                T recipe = getRecipes().get(getSelectedRecipeIndex()).value();
                if (recipe.matches(new MultipleItemInput(noAirInputs()), level)) {
                    if ((inventory.get(OUTPUT_SLOT).getCount() == 0) || inventory.get(OUTPUT_SLOT).getItem() == recipe.getResult().getItem()) {
                        progress++;
                        setChanged(level, pos, state);
                        if (progress >= maxProgress) {
                            craftItem(recipe);
                            resetProgress();
                        }
                    } else {
                        resetProgress();
                    }
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem(T recipe) {
        for (int i = 0; i < 4; i++) {
            this.removeItem(FIRST_INPUT_SLOT + i, 1);
        }
        ItemStack result = recipe.getResult();
        if (canInsertAmountIntoSlot(result, OUTPUT_SLOT)) {
            this.setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.getResult().getCount()));
        }
        byproduct(recipe, FIRST_BYPRODUCT_SLOT);
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoSlot(recipe.get().value().getResult(), OUTPUT_SLOT) && canInsertItemIntoSlot(recipe.get().value().getResult().getItem(), OUTPUT_SLOT);
    }

    private int getCookTime() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.map(tRecipeHolder -> tRecipeHolder.value().time()).orElse(200);
    }

    private List<ItemStack> noAirInputs() {
        return inventory.subList(FIRST_INPUT_SLOT, OUTPUT_SLOT).stream().filter(itemStack -> !itemStack.isEmpty() && !itemStack.getItem().equals(Items.AIR)).collect(Collectors.toList());
    }

}
