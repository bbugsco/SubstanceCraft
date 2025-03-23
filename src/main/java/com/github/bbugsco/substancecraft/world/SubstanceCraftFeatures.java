package com.github.bbugsco.substancecraft.world;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class SubstanceCraftFeatures {

    public static void registerFeatures() {
        SubstanceCraft.LOGGER.info("Registering Features for " + SubstanceCraft.MOD_ID);
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_marijuana"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oil_shale_vein"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OCEAN),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "salt_vein_ocean"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DRIPSTONE_CAVES),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "salt_vein_dripstone_cave"))
        );
    }

}
