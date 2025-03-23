package com.github.bbugsco.substancecraft.datagen;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SubstanceCraftBlocks.HASH_PRESS).add(SubstanceCraftBlocks.REFINERY)
                .add(SubstanceCraftBlocks.EXTRACTOR).add(SubstanceCraftBlocks.CATALYTIC_REFORMER)
                .add(SubstanceCraftBlocks.OXIDATION_MACHINE).add(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE)
                .add(SubstanceCraftBlocks.MIXER).add(SubstanceCraftBlocks.HEATED_MIXER)
                .add(SubstanceCraftBlocks.FERMENTATION_TANK)
                .add(SubstanceCraftBlocks.SALT);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(SubstanceCraftBlocks.OIL_SHALE);
    }

}