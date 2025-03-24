package com.github.bbugsco.substancecraft.world;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Optional;

public class SubstanceCraftLootTables {


    public static void registerLootTables() {
        addMarijuanaPlantSeedsToJungleTempleChestLoot();
        addFungiToCrops();
        addCornSeedsToVillagerHouseChestLoot();
    }

    private static void addCornSeedsToVillagerHouseChestLoot() {
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            List<ResourceKey<LootTable>> chests = List.of(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, BuiltInLootTables.VILLAGE_SAVANNA_HOUSE, BuiltInLootTables.VILLAGE_DESERT_HOUSE, BuiltInLootTables.VILLAGE_SNOWY_HOUSE);
            if (lootTableSource.isBuiltin() && chests.contains(lootTable)) {
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP))
                                .setWeight(7))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)));
                tableBuilder.withPool(lootPool);
            }
        });

    }

    private static void addFungiToCrops() {
        Optional<ResourceKey<LootTable>> POTATO_LOOT_TABLE = Blocks.POTATOES.getLootTable();
        Optional<ResourceKey<LootTable>> CARROT_LOOT_TABLE = Blocks.CARROTS.getLootTable();
        Optional<ResourceKey<LootTable>> WHEAT_LOOT_TABLE = Blocks.WHEAT.getLootTable();
        Optional<ResourceKey<LootTable>> BEETROOT_LOOT_TABLE = Blocks.BEETROOTS.getLootTable();

        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            if (POTATO_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && POTATO_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.POTATOES).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            } else if (CARROT_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && CARROT_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CARROTS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            } else if (WHEAT_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && WHEAT_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.WHEAT).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            }  else if (BEETROOT_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && BEETROOT_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BEETROOTS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            }
        });
    }

    private static void addMarijuanaPlantSeedsToJungleTempleChestLoot() {
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            if (lootTableSource.isBuiltin() && BuiltInLootTables.JUNGLE_TEMPLE.equals(lootTable)) {
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT))
                        .setWeight(15))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)));
                tableBuilder.withPool(lootPool);
            }
        });
    }

}
