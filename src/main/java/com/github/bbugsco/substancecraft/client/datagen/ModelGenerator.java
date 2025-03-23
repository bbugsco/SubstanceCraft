package com.github.bbugsco.substancecraft.client.datagen;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.blocks.CornCrop;
import com.github.bbugsco.substancecraft.block.blocks.MarijuanaPlant;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@Environment(EnvType.CLIENT)
public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, BlockModelGenerators.PlantType.TINTED, MarijuanaPlant.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.REFINERY, blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.OIL_SHALE);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.OXIDATION_MACHINE, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.CATALYTIC_REFORMER, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.EXTRACTOR, blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.SALT);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.MIXER, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.HEATED_MIXER, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.FERMENTATION_TANK, blockStateModelGenerator);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.CORN_CROP, BlockModelGenerators.PlantType.TINTED, CornCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
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
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.TWO_C_B, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.TWO_C_H, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BROMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BROMIDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.COKE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BENZALDEHYDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.NITRIC_ACID, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.NITROMETHANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.ACETIC_ACID, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.AMMONIUM_ACETATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.CARBON_MONOXIDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BETA_NITROSTYRENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.MALEIC_ANHYDRIDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.TETRAHYDROFURAN, ModelTemplates.FLAT_ITEM);
    }

    private void createTopBottomSideFrontAndFrontOnTexture(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation texture = TexturedModel.ORIENTABLE.create(block, blockModelGenerators.modelOutput);
        ResourceLocation frontOn = TexturedModel.ORIENTABLE.get(block)
                .updateTextures(textureMapping -> textureMapping.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_front_on")))
                .createWithSuffix(block, "_on", blockModelGenerators.modelOutput);

        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, texture))
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, frontOn, texture))
                        .with(BlockModelGenerators.createHorizontalFacingDispatch())
        );

    }

}
