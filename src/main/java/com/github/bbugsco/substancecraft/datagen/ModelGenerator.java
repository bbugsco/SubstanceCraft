package com.github.bbugsco.substancecraft.datagen;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.blocks.CornCrop;
import com.github.bbugsco.substancecraft.block.blocks.MarijuanaPlant;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.data.models.BlockModelGenerators.createBooleanModelDispatch;
import static net.minecraft.data.models.BlockModelGenerators.createHorizontalFacingDispatch;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, BlockModelGenerators.TintState.NOT_TINTED, MarijuanaPlant.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.REFINERY, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.OIL_SHALE);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.OXIDATION_MACHINE, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.CATALYTIC_REFORMER, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.AIR_EXTRACTOR, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.SALT);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.MIXER, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.HEATED_MIXER, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        this.createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.FERMENTATION_TANK, TexturedModel.ORIENTABLE, blockStateModelGenerator);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.CORN_CROP, BlockModelGenerators.TintState.TINTED, CornCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.MARIJUANA_TRIM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.MARIJUANA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.HASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.DAB_RIG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.EMPTY_DAB_RIG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.DIPHENHYDRAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.KETAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.OIL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.PETROLEUM_NAPHTHA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.KEROSENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.GASOLINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.METHANOL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.FORMALDEHYDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.CHLOROFORM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BENZENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.TOLUENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.SALT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BRINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.SODIUM_HYDROXIDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.METHANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.CHLORINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.HYDROGEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.NITROGEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.OXYGEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.NATURAL_GAS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.PROPANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.ETHANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BUTANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.METHYLAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.ETHYLENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.PROPYLENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.DIESEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.AMMONIA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.CORN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.ETHANOL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.YEAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.HYDROCHLORIC_ACID, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.ERGOT, ModelTemplates.FLAT_ITEM);
    }

    private void createTopBottomSideFrontAndFrontOnTexture(Block block, TexturedModel.Provider provider, BlockModelGenerators blockModelGenerators) {
        ResourceLocation texture = provider.create(block, blockModelGenerators.modelOutput);
        ResourceLocation textureOn = TextureMapping.getBlockTexture(block, "_front_on");
        ResourceLocation anotherOnTexture = provider.get(block)
                .updateTextures(textureMapping -> textureMapping.put(TextureSlot.FRONT, textureOn))
                .createWithSuffix(block, "_on", blockModelGenerators.modelOutput);
        blockModelGenerators.blockStateOutput.accept(
                        MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, texture)) .
                                with(createBooleanModelDispatch(BlockStateProperties.LIT, anotherOnTexture, texture)).
                                with(createHorizontalFacingDispatch()));

    }

}
