package com.github.bbugsco.substancecraft.client.gui;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.gui.InputOutputMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;

public abstract class InputOutputScreen<T extends InputOutputMenu<?>> extends AbstractContainerScreen<T> {

    protected static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller");
    protected static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller_disabled");
    protected static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_selected");
    protected static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_highlighted");
    protected static final ResourceLocation RECIPE_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe");

    protected static final int SCROLLER_WIDTH = 12;
    protected static final int SCROLLER_HEIGHT = 15;
    protected static final int RECIPES_COLUMNS = 4;
    protected static final int RECIPES_ROWS = 3;
    protected static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    protected static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    protected static final int SCROLLER_FULL_HEIGHT = 54;
    protected static final int RECIPES_X = 11;
    protected static final int RECIPES_Y = 15;
    protected static final int SCROLLER_X = 78;
    protected static final int SCROLLER_Y = 16;
    protected static final int PROGRESS_ARROW_X = 103;
    protected static final int PROGRESS_ARROW_Y = 30;

    protected ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output.png");

    protected float scrollOffset;
    protected boolean scrolling;
    protected int firstVisibleIndex;

    public InputOutputScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelY = 5;
        titleLabelX = 10;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        setBackgroundTexture(menu.getBlockEntity().getMaxByproducts(), menu.getBlockEntity().multipleInput());
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        guiGraphics.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

        ResourceLocation scrollerTexture = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        guiGraphics.blitSprite(RenderType::guiTextured, scrollerTexture, leftPos + SCROLLER_X, topPos + SCROLLER_Y + (int) (41.0F * this.scrollOffset), SCROLLER_WIDTH, SCROLLER_HEIGHT);

        renderProgressArrow(guiGraphics, leftPos, topPos);
        this.renderButtons(guiGraphics, mouseX, mouseY, leftPos + RECIPES_X, topPos + RECIPES_Y, firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS));
        this.renderRecipes(guiGraphics, leftPos + RECIPES_X, topPos + RECIPES_Y, firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS));
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);
        int recipeX = this.leftPos + RECIPES_X;
        int recipeY = this.topPos + RECIPES_Y;
        int lastVisibleIndex = this.firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS);
        for (int index = this.firstVisibleIndex; index < lastVisibleIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int buttonX = recipeX + (relativeIndex % RECIPES_COLUMNS) * RECIPES_IMAGE_SIZE_WIDTH;
            int buttonY = recipeY + (relativeIndex / RECIPES_COLUMNS) * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            if (isMouseInBox(mouseX, mouseY, buttonX, buttonX + RECIPES_IMAGE_SIZE_WIDTH, buttonY, buttonY + RECIPES_IMAGE_SIZE_HEIGHT)) {
                guiGraphics.renderTooltip(this.font, tooltip(index), Optional.empty(), mouseX, mouseY);
            }
        }
    }

    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int recipesX, int recipesY, int lastVisibleElementIndex) {
        for (int index = this.firstVisibleIndex; index < lastVisibleElementIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int row = relativeIndex / RECIPES_COLUMNS;
            int renderX = recipesX + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int renderY = recipesY + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            ResourceLocation buttonStateTexture = getButtonStateTexture(index, mouseX, mouseY, renderX, renderY);
            guiGraphics.blitSprite(RenderType::guiTextured, buttonStateTexture, renderX, renderY - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.isScrollBarActive()) {
            int offscreenRows = this.getOffscreenRows();
            float scrollChangeAmount = (float) scrollY / (float) offscreenRows;
            this.scrollOffset = Mth.clamp(this.scrollOffset - scrollChangeAmount, 0.0F, 1.0F);
            this.firstVisibleIndex = (int) ((double) (this.scrollOffset * (float) offscreenRows) + 0.5) * RECIPES_COLUMNS;
        }
        return true;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int recipeY = this.topPos + RECIPES_Y;
            int bottomScrollerY = recipeY + SCROLLER_FULL_HEIGHT;
            this.scrollOffset = ((float) mouseY - (float) recipeY - 7.5F) / ((float) (bottomScrollerY - recipeY) - 15.0F);
            this.scrollOffset = Mth.clamp(this.scrollOffset, 0.0F, 1.0F);
            this.firstVisibleIndex = (int) ((double) (this.scrollOffset * (float) this.getOffscreenRows()) + (double) 0.5F) * RECIPES_COLUMNS;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int recipeX = this.leftPos + RECIPES_X;
        int recipeY = this.topPos + RECIPES_Y;
        int maxRecipeIndex = this.firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS);

        for (int index = this.firstVisibleIndex; index < maxRecipeIndex; index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            double buttonX = mouseX - (double) (recipeX + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH);
            double buttonY = mouseY - (double) (recipeY + relativeIndex / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);

            Minecraft client = this.minecraft;
            if (client == null) return false;

            if (buttonX >= 0.0 && buttonY >= 0.0 && buttonX < RECIPES_IMAGE_SIZE_WIDTH && buttonY < RECIPES_IMAGE_SIZE_HEIGHT && this.menu.clickMenuButton(this.minecraft.player, index)) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                MultiPlayerGameMode multiPlayerGameMode = this.minecraft.gameMode;
                if (multiPlayerGameMode != null) {
                    multiPlayerGameMode.handleInventoryButtonClick(this.menu.containerId, index);
                }
                return true;
            }
        }
        recipeX = this.leftPos + SCROLLER_X;
        recipeY = this.topPos + SCROLLER_Y;
        if (isMouseInBox((int) mouseX, (int) mouseY, recipeX, recipeX + (RECIPES_ROWS * RECIPES_COLUMNS), recipeY, recipeY + SCROLLER_FULL_HEIGHT)) {
            this.scrolling = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    protected abstract void renderRecipes(GuiGraphics guiGraphics, int x, int y, int startIndex);

    protected abstract List<Component> tooltip(int index);

    private boolean isMouseInBox(int mouseX, int mouseY, int minX, int maxX, int minY, int maxY) {
        return mouseX >= minX && mouseX < maxX && mouseY >= minY && mouseY < maxY;
    }

    private ResourceLocation getButtonStateTexture(int index, int mouseX, int mouseY, int renderX, int renderY) {
        ResourceLocation buttonStateTexture;
        if (menu.getBlockEntity().hasRepeatInputRecipes() && index == this.menu.getBlockEntity().getSelectedRecipeIndex()) {
            buttonStateTexture = RECIPE_SELECTED_SPRITE;
        } else if (mouseX >= renderX && mouseY >= renderY && mouseX < renderX + RECIPES_IMAGE_SIZE_WIDTH && mouseY < renderY + RECIPES_IMAGE_SIZE_HEIGHT) {
            buttonStateTexture = RECIPE_HIGHLIGHTED_SPRITE;
        } else {
            buttonStateTexture = RECIPE_SPRITE;
        }
        return buttonStateTexture;
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, x + PROGRESS_ARROW_X, y + PROGRESS_ARROW_Y, 176, 0, 8, menu.getScaledProgress(), 256, 256);
        }
    }

    private boolean isScrollBarActive() {
        return this.menu.getNumRecipes() > (RECIPES_ROWS * RECIPES_COLUMNS);
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
    }

    protected Component getItemNameString(ItemStack itemStack) {
        if (itemStack == null) return Component.empty();
        if (itemStack.getItem() == Items.POTION) return Component.literal("Water Bottle");
        else return Component.literal(itemStack.getDisplayName().getString().replace("[", "").replace("]", ""));
    }

    protected Component getByproductString(ItemStack itemStack, int chance) {
        if (itemStack == null) return Component.empty();
        if (itemStack.getItem() == Items.POTION) return Component.literal("Water Bottle " + chance + "%");
        else return Component.literal(itemStack.getDisplayName().getString().replace("[", "").replace("]", "") + " " + chance + "%");
    }

    private void setBackgroundTexture(int numberOfByproductSlots, boolean multipleInput) {
        if (!multipleInput) {
            if (numberOfByproductSlots == 1)
                BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output_1_byproduct.png");
            else if (numberOfByproductSlots == 2)
                BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output_2_byproduct.png");
            else if (numberOfByproductSlots == 3)
                BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output_3_byproduct.png");
            else BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output.png");
        } else {
            if (numberOfByproductSlots == 1)
                BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/multiple_input_output_1_byproduct.png");
            else if (numberOfByproductSlots == 2)
                BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/multiple_input_output_2_byproduct.png");
            else if (numberOfByproductSlots == 3)
                BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/multiple_input_output_3_byproduct.png");
            else BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/multiple_input_output.png");
        }
    }

}
