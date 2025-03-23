package com.github.bbugsco.substancecraft.client.gui;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.entity.OneInputBlockEntity;
import com.github.bbugsco.substancecraft.gui.OneInputMenu;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class OneInputScreen<R extends OneInputRecipe, E extends OneInputBlockEntity<R>, T extends OneInputMenu<R, E>> extends AbstractContainerScreen<T> {

    private ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output.png");
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller_disabled");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_selected");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_highlighted");
    private static final ResourceLocation RECIPE_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe");

    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 11;
    private static final int RECIPES_Y = 15;
    private static final int SCROLLER_X = 78;
    private static final int SCROLLER_Y = 16;
    private static final int PROGRESS_ARROW_X = 103;
    private static final int PROGRESS_ARROW_Y = 30;

    private float scrollOffset;
    private boolean scrolling;
    private int startIndex;

    public OneInputScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelY = 5;
        titleLabelX = 10;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        super.renderTooltip(guiGraphics, x, y);
        int i = this.leftPos + RECIPES_X;
        int j = this.topPos + RECIPES_Y;
        int k = this.startIndex + SCROLLER_WIDTH;
        List<RecipeHolder<R>> list = this.menu.getRecipes();
        for (int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); l++) {
            int m = l - this.startIndex;
            int n = i + m % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int o = j + m / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            if (x >= n && x < n + RECIPES_IMAGE_SIZE_WIDTH && y >= o && y < o + RECIPES_IMAGE_SIZE_HEIGHT) {
                OneInputRecipe recipe = list.get(l).value();
                ItemStack inputItem = new ItemStack(recipe.getInput().items().findFirst().orElse(Holder.direct(Items.AIR)).value().asItem());
                ItemStack resultItem = recipe.getResult();
                List<Component> tooltip = new ArrayList<>();
                tooltip.add(getItemNameString(resultItem, false));
                tooltip.add(getItemNameString(inputItem, true));
                if (!recipe.getByproducts().isEmpty()) {
                    tooltip.add(Component.literal("Byproducts: "));
                    List<ItemStack> byproducts = recipe.getByproducts();
                    for (ItemStack byproduct : byproducts) {
                        tooltip.add(getByproductString(byproduct, byproduct.getCount() << 1));
                    }
                }
                guiGraphics.renderTooltip(this.font, tooltip, Optional.empty(), x, y);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        setBackgroundTexture(menu.handle.getMaxByproducts());
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(RenderType::guiTextured, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
        ResourceLocation resourceLocation = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        guiGraphics.blitSprite(RenderType::guiTextured, resourceLocation, leftPos + SCROLLER_X, topPos + SCROLLER_Y + (int) (41.0F * this.scrollOffset), SCROLLER_WIDTH, SCROLLER_HEIGHT);
        renderProgressArrow(guiGraphics, leftPos, topPos);
        this.renderButtons(guiGraphics, mouseX, mouseY, leftPos + RECIPES_X, topPos + RECIPES_Y, startIndex + (RECIPES_ROWS * RECIPES_COLUMNS));
        this.renderRecipes(guiGraphics, leftPos + RECIPES_X, topPos + RECIPES_Y, startIndex + (RECIPES_ROWS * RECIPES_COLUMNS));
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.isScrollBarActive()) {
            int offscreenRows = this.getOffscreenRows();
            float f = (float) scrollY / (float) offscreenRows;
            this.scrollOffset = Mth.clamp(this.scrollOffset - f, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffset * (float) offscreenRows) + 0.5) * RECIPES_COLUMNS;
        }
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int y = this.topPos + RECIPES_Y;
            int y2 = y + SCROLLER_FULL_HEIGHT;
            this.scrollOffset = ((float) mouseY - (float) y - 7.5F) / ((float) (y2 - y) - 15.0F);
            this.scrollOffset = Mth.clamp(this.scrollOffset, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffset * (float) this.getOffscreenRows()) + (double) 0.5F) * RECIPES_COLUMNS;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int x = this.leftPos + RECIPES_X;
        int y = this.topPos + RECIPES_Y;
        int i = this.startIndex + (RECIPES_ROWS * RECIPES_COLUMNS);

        for (int index = this.startIndex; index < i; index++) {
            int m = index - this.startIndex;
            double d = mouseX - (double) (x + m % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH);
            double e = mouseY - (double) (y + m / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);
            if (d >= 0.0 && e >= 0.0 && d < RECIPES_IMAGE_SIZE_WIDTH && e < RECIPES_IMAGE_SIZE_HEIGHT && this.menu.clickMenuButton(this.minecraft.player, index)) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, index);
                return true;
            }
        }
        x = this.leftPos + SCROLLER_X;
        y = this.topPos + SCROLLER_Y;
        if (mouseX >= (double) x && mouseX < (double) (x + (RECIPES_ROWS * RECIPES_COLUMNS)) && mouseY >= (double) y && mouseY < (double) (y + SCROLLER_FULL_HEIGHT)) {
            this.scrolling = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }


    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y, int lastVisibleElementIndex) {
        for (int index = this.startIndex; index < lastVisibleElementIndex && index < this.menu.getNumRecipes(); index++) {
            int indexShift = index - this.startIndex;
            int renderX = x + indexShift % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = indexShift / RECIPES_COLUMNS;
            int renderY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            ResourceLocation buttonStateTexture;
            if (menu.hasRepeatInputRecipes() && index == this.menu.getSelectedRecipeIndex()) {
                buttonStateTexture = RECIPE_SELECTED_SPRITE;
            } else if (mouseX >= renderX && mouseY >= renderY && mouseX < renderX + RECIPES_IMAGE_SIZE_WIDTH && mouseY < renderY + RECIPES_IMAGE_SIZE_HEIGHT) {
                buttonStateTexture = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                buttonStateTexture = RECIPE_SPRITE;
            }
            guiGraphics.blitSprite(RenderType::guiTextured, buttonStateTexture, renderX, renderY - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int x, int y, int startIndex) {
        List<RecipeHolder<R>> list = this.menu.getRecipes();
        for (int index = this.startIndex; index < startIndex && index < this.menu.getNumRecipes(); index++) {
            int indexShift = index - this.startIndex;
            int renderX = x + indexShift % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = indexShift / RECIPES_COLUMNS;
            int renderY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            guiGraphics.renderItem(list.get(index).value().getResult(), renderX, renderY);
        }
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(RenderType::guiTextured, TEXTURE, x + PROGRESS_ARROW_X, y + PROGRESS_ARROW_Y, 176, 0, 8, menu.getScaledProgress(), 256, 256);
        }
    }

    private boolean isScrollBarActive() {
        return this.menu.getNumRecipes() > (RECIPES_ROWS * RECIPES_COLUMNS);
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
    }

    private Component getItemNameString(ItemStack itemStack, boolean isInput) {
        if (itemStack == null) return Component.empty();
        if (isInput) {
            if (itemStack.getItem() == Items.POTION) return Component.literal("Requires Water Bottle");
            else return Component.literal("Requires " + itemStack.getDisplayName().getString().replace("[", "").replace("]", ""));
        } else {
            if (itemStack.getItem() == Items.POTION) return Component.literal("Water Bottle");
            else return Component.literal(itemStack.getDisplayName().getString().replace("[", "").replace("]", ""));
        }
    }

    private Component getByproductString(ItemStack itemStack, int chance) {
        if (itemStack == null) return Component.empty();
        if (itemStack.getItem() == Items.POTION) return Component.literal("Water Bottle " + chance + "%");
        else return Component.literal(itemStack.getDisplayName().getString().replace("[", "").replace("]", "") + " " + chance + "%");
    }

    private void setBackgroundTexture(int numberOfByproductSlots) {
        if (numberOfByproductSlots == 1)
            TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output_1_byproduct.png");
        else if (numberOfByproductSlots == 2)
            TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output_2_byproduct.png");
        else if (numberOfByproductSlots == 3)
            TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output_3_byproduct.png");
        else TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output.png");
    }
}
