package com.github.bbugsco.substancecraft.datagen;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(SubstanceCraftBlocks.HASH_PRESS, createNameableBlockEntityTable(SubstanceCraftBlocks.HASH_PRESS));
        add(SubstanceCraftBlocks.REFINERY, createNameableBlockEntityTable(SubstanceCraftBlocks.REFINERY));
        dropSelf(SubstanceCraftBlocks.OIL_SHALE);
        add(SubstanceCraftBlocks.OXIDATION_MACHINE, createNameableBlockEntityTable(SubstanceCraftBlocks.OXIDATION_MACHINE));
        add(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE, createNameableBlockEntityTable(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE));
        add(SubstanceCraftBlocks.AIR_EXTRACTOR, createNameableBlockEntityTable(SubstanceCraftBlocks.AIR_EXTRACTOR));
        add(SubstanceCraftBlocks.CATALYTIC_REFORMER, createNameableBlockEntityTable(SubstanceCraftBlocks.CATALYTIC_REFORMER));

        add(SubstanceCraftBlocks.SALT, block -> this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                                .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

    }

}