package com.github.bbugsco.substancecraft.block.entity;


import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.entity.entities.CatalyticReformerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.ExtractorBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.FermentationTankBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.HeatedMixerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.MixerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.OxidizerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.RefineryBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SubstanceCraftBlockEntities {


    public static final BlockEntityType<HashPressBlockEntity> HASH_PRESS =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press"),
                    FabricBlockEntityTypeBuilder.create(HashPressBlockEntity::new, SubstanceCraftBlocks.HASH_PRESS).build());

    public static final BlockEntityType<RefineryBlockEntity> REFINERY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery"),
                    FabricBlockEntityTypeBuilder.create(RefineryBlockEntity::new, SubstanceCraftBlocks.REFINERY).build());

    public static final BlockEntityType<ElectrolysisMachineBlockEntity> ELECTROLYSIS_MACHINE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_machine"),
                    FabricBlockEntityTypeBuilder.create(ElectrolysisMachineBlockEntity::new, SubstanceCraftBlocks.ELECTROLYSIS_MACHINE).build());

    public static final BlockEntityType<OxidizerBlockEntity> OXIDIZER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidizer"),
                    FabricBlockEntityTypeBuilder.create(OxidizerBlockEntity::new, SubstanceCraftBlocks.OXIDATION_MACHINE).build());

    public static final BlockEntityType<CatalyticReformerBlockEntity> CATALYTIC_REFORMER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "catalytic_reformer"),
                    FabricBlockEntityTypeBuilder.create(CatalyticReformerBlockEntity::new, SubstanceCraftBlocks.CATALYTIC_REFORMER).build());

    public static final BlockEntityType<ExtractorBlockEntity> EXTRACTOR =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extractor"),
                    FabricBlockEntityTypeBuilder.create(ExtractorBlockEntity::new, SubstanceCraftBlocks.EXTRACTOR).build());

    public static final BlockEntityType<MixerBlockEntity> MIXER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mixer"),
                    FabricBlockEntityTypeBuilder.create(MixerBlockEntity::new, SubstanceCraftBlocks.MIXER).build());

    public static final BlockEntityType<HeatedMixerBlockEntity> HEATED_MIXER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "heated_mixer"),
                    FabricBlockEntityTypeBuilder.create(HeatedMixerBlockEntity::new, SubstanceCraftBlocks.HEATED_MIXER).build());

    public static final BlockEntityType<FermentationTankBlockEntity> FERMENTATION_TANK =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "fermentation_tank"),
                    FabricBlockEntityTypeBuilder.create(FermentationTankBlockEntity::new, SubstanceCraftBlocks.FERMENTATION_TANK).build());


    public static void registerBlockEntities() {

    }

}
