package com.github.bbugsco.substancecraft.datagen;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import com.github.bbugsco.substancecraft.recipe.builder.CatalyticReformerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.ElectrolysisRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.ExtractorRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.FermentationTankRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.HashPressRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.HeatedMixerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.MixerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.OxidizerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.RefineryRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.ExtractorRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.FermentationTankRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.HeatedMixerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.MixerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {


    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS))
                        .pattern("121")
                        .pattern(" 3 ")
                        .pattern("111")
                        .define('1', Items.SMOOTH_STONE_SLAB)
                        .define('2', Items.REDSTONE_BLOCK)
                        .define('3', Items.PISTON)
                        .unlockedBy("has_item", has(Items.SMOOTH_STONE_SLAB))
                        .unlockedBy("has_item", has(Items.REDSTONE_BLOCK))
                        .unlockedBy("has_item", has(Items.PISTON))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press")));

                shaped(RecipeCategory.MISC, SubstanceCraftItems.EMPTY_DAB_RIG)
                        .pattern("1  ")
                        .pattern("121")
                        .pattern(" 1 ")
                        .define('1', Items.GLASS)
                        .define('2', Items.GLASS_BOTTLE)
                        .unlockedBy("has_item", has(Items.GLASS))
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "dab_rig")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY))
                        .pattern("333")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER))
                        .pattern("121")
                        .pattern("343")
                        .pattern("131")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.IRON_SHOVEL)
                        .define('3', Items.IRON_INGOT)
                        .define('4', Items.CAULDRON)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mixer")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER))
                        .pattern("121")
                        .pattern("343")
                        .pattern("151")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.IRON_SHOVEL)
                        .define('3', Items.IRON_INGOT)
                        .define('4', Items.CAULDRON)
                        .define('5', Items.LAVA_BUCKET)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "heated_mixer")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK))
                        .pattern("121")
                        .pattern("232")
                        .pattern("121")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.CAULDRON)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "fermentation_tank")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CATALYTIC_REFORMER))
                        .pattern("333")
                        .pattern("212")
                        .pattern("343")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .define('4', Items.LAVA_BUCKET)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "catalytic_reformer")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE))
                        .pattern("323")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidizer")));


                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR))
                        .pattern("343")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .define('4', Items.HOPPER)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extractor")));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE))
                        .pattern("121")
                        .pattern("343")
                        .pattern("151")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.LEVER)
                        .define('3', Items.REDSTONE)
                        .define('4', Items.CAULDRON)
                        .define('5', Items.IRON_INGOT)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_machine")));

                HashPressRecipeBuilder.press(
                                Ingredient.of(SubstanceCraftItems.MARIJUANA),
                                SubstanceCraftItems.HASH,
                                1000,
                                HashPressRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.MARIJUANA))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "press_hash")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE)),
                                SubstanceCraftItems.OIL,
                                List.of(new ItemStack(SubstanceCraftItems.NATURAL_GAS, 40 >> 1)),
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE)))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_oil")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.PETROLEUM_NAPHTHA,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_naphtha")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.KEROSENE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_kerosene")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.GASOLINE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_gasoline")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.DIPHENHYDRAMINE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_dph")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.KETAMINE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_ketamine")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.METHANOL,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_methanol")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.ETHANE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_ethane")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.BUTANE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_butane")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.PROPANE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_propane")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.PROPANE),
                                SubstanceCraftItems.PROPYLENE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_propylene")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.ETHANE),
                                SubstanceCraftItems.ETHYLENE,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_ethylene")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.DIESEL,
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_diesel")));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(Items.COAL),
                                SubstanceCraftItems.COKE,
                                List.of(new ItemStack(SubstanceCraftItems.CARBON_MONOXIDE, 50 >> 1)),
                                1000,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.COAL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_coke")));

                CatalyticReformerRecipeBuilder.reform(
                                Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA),
                                SubstanceCraftItems.BENZENE,
                                1000,
                                CatalyticReformerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "reform_benzene")));

                CatalyticReformerRecipeBuilder.reform(
                                Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA),
                                SubstanceCraftItems.TOLUENE,
                                1000,
                                CatalyticReformerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "reform_toluene")));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.METHANOL),
                                SubstanceCraftItems.FORMALDEHYDE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidize_formaldehyde")));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.AMMONIA),
                                SubstanceCraftItems.NITRIC_ACID,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidize_nitric_acid")));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.TOLUENE),
                                SubstanceCraftItems.BENZALDEHYDE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TOLUENE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidize_benzaldehyde")));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.BENZENE),
                                SubstanceCraftItems.MALEIC_ANHYDRIDE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZENE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidize_maleic_anhydride")));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.BROMIDE),
                                SubstanceCraftItems.BROMINE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BROMIDE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidize_bromine")));

                ElectrolysisRecipeBuilder.electrolysis(
                                Ingredient.of(SubstanceCraftItems.BRINE),
                                SubstanceCraftItems.SODIUM_HYDROXIDE,
                                List.of(new ItemStack(SubstanceCraftItems.CHLORINE, 30 >> 1), new ItemStack(SubstanceCraftItems.HYDROGEN, 30 >> 1)),
                                1000,
                                ElectrolysisRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_brine")));

                ElectrolysisRecipeBuilder.electrolysis(
                                Ingredient.of(Items.POTION),
                                SubstanceCraftItems.HYDROGEN,
                                List.of(new ItemStack(SubstanceCraftItems.OXYGEN, 50 >> 1)),
                                1000,
                                ElectrolysisRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_water")));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(Items.GLASS_BOTTLE),
                                SubstanceCraftItems.NITROGEN,
                                600,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extract_nitrogen")));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(Items.GLASS_BOTTLE),
                                SubstanceCraftItems.OXYGEN,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extract_oxygen")));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.BRINE),
                                SubstanceCraftItems.BROMIDE,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extract_bromide")));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SALT), Ingredient.of(Items.POTION)),
                                SubstanceCraftItems.BRINE,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.POTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SALT))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_brine")));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ACETIC_ACID), Ingredient.of(SubstanceCraftItems.AMMONIA)),
                                SubstanceCraftItems.AMMONIUM_ACETATE,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_ammonia_acetate")));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(SubstanceCraftItems.MALEIC_ANHYDRIDE)),
                                SubstanceCraftItems.TETRAHYDROFURAN,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.MALEIC_ANHYDRIDE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_tetrahydrofuran")));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BETA_NITROSTYRENE), Ingredient.of(SubstanceCraftItems.TETRAHYDROFURAN), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.TWO_C_H,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BETA_NITROSTYRENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TETRAHYDROFURAN))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_2c_h")));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.TWO_C_H), Ingredient.of(SubstanceCraftItems.BROMINE), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.TWO_C_B,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TWO_C_H))
                        .unlockedBy("has_item", has(SubstanceCraftItems.BROMINE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_2c_b")));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.METHYLAMINE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_methylamine")));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.METHANE)),
                                SubstanceCraftItems.CHLOROFORM,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_chloroform")));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(Items.POTION)),
                                SubstanceCraftItems.HYDROCHLORIC_ACID,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .unlockedBy("has_item", has(Items.POTION))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_hcl")));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.NITROGEN), Ingredient.of(SubstanceCraftItems.HYDROGEN)),
                                SubstanceCraftItems.AMMONIA,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_ammonia")));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPANE), Ingredient.of(SubstanceCraftItems.NITRIC_ACID)),
                                SubstanceCraftItems.NITROMETHANE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_nitromethane")));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.METHANOL), Ingredient.of(SubstanceCraftItems.CARBON_MONOXIDE)),
                                SubstanceCraftItems.ACETIC_ACID,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_acetic_acid")));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BENZALDEHYDE), Ingredient.of(SubstanceCraftItems.NITROMETHANE), Ingredient.of(SubstanceCraftItems.AMMONIUM_ACETATE)),
                                SubstanceCraftItems.BETA_NITROSTYRENE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_beta_nitrostyrene")));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(SubstanceCraftItems.CORN)),
                                SubstanceCraftItems.ETHANOL,
                                2000,
                                FermentationTankRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CORN))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ferment_ethanol")));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(Items.SUGAR)),
                                SubstanceCraftItems.YEAST,
                                2000,
                                FermentationTankRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .unlockedBy("has_item", has(Items.SUGAR))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ferment_yeast")));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.ERGOT), Ingredient.of(Items.SUGAR)),
                                SubstanceCraftItems.ERGOT,
                                2000,
                                FermentationTankRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOT))
                        .unlockedBy("has_item", has(Items.SUGAR))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ferment_ergot")));
            }
        };
    }


    @Override
    public @NotNull String getName() {
        return "";
    }
}