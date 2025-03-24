package com.github.bbugsco.substancecraft.block.entity;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class OneInputBlockEntity<T extends OneInputRecipe> extends InputOutputBlockEntity implements RecipeList<T> {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int BYPRODUCT_START_SLOT = 2;

    public final RecipeManager.CachedCheck<SingleRecipeInput, T> matchGetter;
    private final RecipeType<T> type;
    private final List<RecipeHolder<T>> recipes;
    private final boolean hasRepeatInputRecipes;

    public OneInputBlockEntity(BlockPos pos, BlockState state, String displayName, RecipeType<T> type, BlockEntityType<?> blockEntityType, boolean hasRepeatInputRecipes) {
        super(blockEntityType, pos, state, displayName, 5);
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
    public int getNumRecipes() {
        return recipes.size();
    }

    @Override
    public boolean multipleInput() {
        return false;
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

    public ItemStack getRenderStack() {
        if(this.getItem(OUTPUT_SLOT).isEmpty()) {
            return this.getItem(INPUT_SLOT);
        } else {
            return this.getItem(OUTPUT_SLOT);
        }
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean itemsEqual = !stack.isEmpty() && ItemStack.isSameItem(itemStack, stack);
        this.inventory.set(slot, stack);
        if (slot == INPUT_SLOT && !itemsEqual) {
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
        if(level.isClientSide) return;
        if (isSlotEmptyOrReceivable(OUTPUT_SLOT)) {
            if (hasRecipe()) {
                T recipe = getRecipes().get(getSelectedRecipeIndex()).value();
                if (recipe.matches(new SingleRecipeInput(inventory.getFirst()), level)) {
                    for (int i = 0; i < recipes.size(); i++) {
                        if (recipes.get(i).value() == recipe) {
                            selectedRecipeIndex = i;
                            break;
                        }
                    }
                    if ((inventory.get(OUTPUT_SLOT).getCount() == 0) || inventory.get(OUTPUT_SLOT).getItem() == recipe.getResult().getItem()) {
                        progress++;
                        setChanged(level, pos, state);
                        if (progress >= maxProgress) {
                            craftItem(recipe);
                            progress = 0;
                        }
                    } else {
                        progress = 0;
                    }
                } else {
                    progress = 0;
                }
            } else {
                progress = 0;
            }
        } else {
            progress = 0;
            setChanged(level, pos, state);
        }
    }

    private void craftItem(T recipe) {
        this.removeItem(INPUT_SLOT, 1);
        ItemStack result = recipe.getResult();
        if (canInsertAmountIntoSlot(result, OUTPUT_SLOT)) {
            this.setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.getResult().getCount()));
        }
        byproduct(recipe, BYPRODUCT_START_SLOT);
    }


    private boolean hasRecipe() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoSlot(recipe.get().value().getResult(), OUTPUT_SLOT) && canInsertItemIntoSlot(recipe.get().value().getResult().getItem(), OUTPUT_SLOT);
    }

    private int getCookTime() {
        int cookTime = 200;
        Optional<RecipeHolder<T>> optionalRecipeHolder = getCurrentRecipe();
        if (optionalRecipeHolder.isPresent()) {
            cookTime =  optionalRecipeHolder.get().value().getTime();
        }
        return cookTime;
    }

}
