package com.github.bbugsco.substancecraft.client.gui;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.gui.WorkstationStonecutterRecipeListMenu;
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

public abstract class WorkstationStonecutterRecipeListScreen<T extends WorkstationStonecutterRecipeListMenu<?>> extends AbstractContainerScreen<T> {

    protected ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/one_input_output.png");
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

    protected float scrollOffset;
    protected boolean scrolling;
    protected int startIndex;

    public WorkstationStonecutterRecipeListScreen(T menu, Inventory playerInventory, Component title) {
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
        setBackgroundTexture(menu.getHandle().getMaxByproducts());
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
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        super.renderTooltip(guiGraphics, x, y);
        int i = this.leftPos + RECIPES_X;
        int j = this.topPos + RECIPES_Y;
        int k = this.startIndex + SCROLLER_WIDTH;
        for (int index = this.startIndex; index < k && index < this.menu.getNumRecipes(); index++) {
            int m = index - this.startIndex;
            int n = i + m % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int o = j + m / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            if (x >= n && x < n + RECIPES_IMAGE_SIZE_WIDTH && y >= o && y < o + RECIPES_IMAGE_SIZE_HEIGHT) {
                guiGraphics.renderTooltip(this.font, tooltip(index), Optional.empty(), x, y);
            }
        }
    }

    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int recipesX, int recipesY, int lastVisibleElementIndex) {
        for (int index = this.startIndex; index < lastVisibleElementIndex && index < this.menu.getNumRecipes(); index++) {
            int indexShift = index - this.startIndex;
            int renderX = recipesX + indexShift % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = indexShift / RECIPES_COLUMNS;
            int renderY = recipesY + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            ResourceLocation buttonStateTexture;
            if (menu.getHandle().hasRepeatInputRecipes() && index == this.menu.getHandle().getSelectedRecipeIndex()) {
                buttonStateTexture = RECIPE_SELECTED_SPRITE;
            } else if (mouseX >= renderX && mouseY >= renderY && mouseX < renderX + RECIPES_IMAGE_SIZE_WIDTH && mouseY < renderY + RECIPES_IMAGE_SIZE_HEIGHT) {
                buttonStateTexture = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                buttonStateTexture = RECIPE_SPRITE;
            }
            guiGraphics.blitSprite(RenderType::guiTextured, buttonStateTexture, renderX, renderY - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
        }
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
        int recipeX = this.leftPos + RECIPES_X;
        int recipeY = this.topPos + RECIPES_Y;
        int maxRecipeIndex = this.startIndex + (RECIPES_ROWS * RECIPES_COLUMNS);

        for (int index = this.startIndex; index < maxRecipeIndex; index++) {
            int indexAbsolute = index - this.startIndex;
            double d = mouseX - (double) (recipeX + indexAbsolute % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH);
            double e = mouseY - (double) (recipeY + indexAbsolute / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);
            Minecraft client = this.minecraft;
            if (client == null) {
                return false;
            }
            if (d >= 0.0 && e >= 0.0 && d < RECIPES_IMAGE_SIZE_WIDTH && e < RECIPES_IMAGE_SIZE_HEIGHT && this.menu.clickMenuButton(this.minecraft.player, index)) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                MultiPlayerGameMode multiPlayerGameMode = this.minecraft.gameMode;
                if (multiPlayerGameMode != null) {
                    multiPlayerGameMode.handleInventoryButtonClick(this.menu.containerId, index);
                    return true;
                } else {
                    return false;
                }
            }
        }
        recipeX = this.leftPos + SCROLLER_X;
        recipeY = this.topPos + SCROLLER_Y;
        if (mouseX >= (double) recipeX && mouseX < (double) (recipeX + (RECIPES_ROWS * RECIPES_COLUMNS)) && mouseY >= (double) recipeY && mouseY < (double) (recipeY + SCROLLER_FULL_HEIGHT)) {
            this.scrolling = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    protected abstract void renderRecipes(GuiGraphics guiGraphics, int x, int y, int startIndex);

    protected abstract List<Component> tooltip(int index);

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
