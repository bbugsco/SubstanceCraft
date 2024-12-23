package com.github.bbugsco.substancecraft.items;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SubstanceCraftItems {

    public static CreativeModeTab SUBSTANCES_ITEM_GROUP;

    public static final Item MARIJUANA = registerItem("marijuana", new Item(new Item.Properties()));
    public static final Item MARIJUANA_TRIM = registerItem("marijuana_trim", new Item(new Item.Properties()));
    public static final Item HASH = registerItem("hash", new Item(new Item.Properties()));
    public static final Item EMPTY_DAB_RIG = registerItem("empty_dab_rig", new DabRig.EmptyDabRigItem(new Item.Properties()));
    public static final Item DAB_RIG = registerItem("dab_rig", new DabRig.DabRigItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(-1).build())));
    public static final Item DIPHENHYDRAMINE = registerItem("diphenhydramine", new SimpleDrugs.Diphenhydramine(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build())));
    public static final Item KETAMINE = registerItem("ketamine", new SimpleDrugs.Ketamine(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build())));
    public static final Item OIL = registerItem("oil", new Item(new Item.Properties()));
    public static final Item PETROLEUM_NAPHTHA = registerItem("petroleum_naphtha", new Item(new Item.Properties()));
    public static final Item KEROSENE = registerItem("kerosene", new Item(new Item.Properties()));
    public static final Item GASOLINE = registerItem("gasoline", new Item(new Item.Properties()));
    public static final Item METHANOL = registerItem("methanol", new Item(new Item.Properties()));
    public static final Item BENZENE = registerItem("benzene", new Item(new Item.Properties()));
    public static final Item CHLOROFORM = registerItem("chloroform", new Item(new Item.Properties()));
    public static final Item FORMALDEHYDE = registerItem("formaldehyde", new Item(new Item.Properties()));
    public static final Item TOLUENE = registerItem("toluene", new Item(new Item.Properties()));
    public static final Item SALT = registerItem("salt", new Item(new Item.Properties()));
    public static final Item BRINE = registerItem("brine", new Item(new Item.Properties()));
    public static final Item SODIUM_HYDROXIDE = registerItem("sodium_hydroxide", new Item(new Item.Properties()));
    public static final Item HYDROGEN = registerItem("hydrogen", new Item(new Item.Properties()));
    public static final Item CHLORINE = registerItem("chlorine", new Item(new Item.Properties()));
    public static final Item METHANE = registerItem("methane", new Item(new Item.Properties()));
    public static final Item NITROGEN = registerItem("nitrogen", new Item(new Item.Properties()));
    public static final Item OXYGEN = registerItem("oxygen", new Item(new Item.Properties()));
    public static final Item NATURAL_GAS = registerItem("natural_gas", new Item(new Item.Properties()));
    public static final Item ETHANE = registerItem("ethane", new Item(new Item.Properties()));
    public static final Item PROPANE = registerItem("propane", new Item(new Item.Properties()));
    public static final Item BUTANE = registerItem("butane", new Item(new Item.Properties()));
    public static final Item METHYLAMINE = registerItem("methylamine", new Item(new Item.Properties()));
    public static final Item ETHYLENE = registerItem("ethylene", new Item(new Item.Properties()));
    public static final Item PROPYLENE = registerItem("propylene", new Item(new Item.Properties()));
    public static final Item DIESEL = registerItem("diesel", new Item(new Item.Properties()));
    public static final Item AMMONIA = registerItem("ammonia", new Item(new Item.Properties()));
    public static final Item CORN = registerItem("corn", new Item(new Item.Properties()));
    public static final Item ETHANOL = registerItem("ethanol", new Item(new Item.Properties()));
    public static final Item YEAST = registerItem("yeast", new Item(new Item.Properties()));
    public static final Item HYDROCHLORIC_ACID = registerItem("hydrochloric_acid", new Item(new Item.Properties()));
    public static final Item ERGOT = registerItem("ergot", new Item(new Item.Properties()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SubstanceCraft.LOGGER.info("Registering Mod Items for " + SubstanceCraft.MOD_ID);
    }

    public static void registerItemGroups() {
        SUBSTANCES_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substances"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.substances"))
                        .icon(() -> new ItemStack(Items.OMINOUS_BOTTLE)).displayItems((displayContext, entries) -> {
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
                            entries.accept(SubstanceCraftItems.MARIJUANA);
                            entries.accept(SubstanceCraftItems.MARIJUANA_TRIM);
                            entries.accept(SubstanceCraftItems.HASH);
                            entries.accept(SubstanceCraftItems.EMPTY_DAB_RIG);
                            entries.accept(SubstanceCraftItems.DAB_RIG);
                            entries.accept(SubstanceCraftItems.CORN);
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP));
                            entries.accept(SubstanceCraftItems.YEAST);
                            entries.accept(SubstanceCraftItems.ERGOT);
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CATALYTIC_REFORMER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.AIR_EXTRACTOR));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SALT));
                            entries.accept(SubstanceCraftItems.DIPHENHYDRAMINE);
                            entries.accept(SubstanceCraftItems.KETAMINE);
                            entries.accept(SubstanceCraftItems.SALT);
                            entries.accept(SubstanceCraftItems.SODIUM_HYDROXIDE);
                            entries.accept(SubstanceCraftItems.OIL);
                            entries.accept(SubstanceCraftItems.PETROLEUM_NAPHTHA);
                            entries.accept(SubstanceCraftItems.KEROSENE);
                            entries.accept(SubstanceCraftItems.GASOLINE);
                            entries.accept(SubstanceCraftItems.METHANOL);
                            entries.accept(SubstanceCraftItems.FORMALDEHYDE);
                            entries.accept(SubstanceCraftItems.CHLOROFORM);
                            entries.accept(SubstanceCraftItems.TOLUENE);
                            entries.accept(SubstanceCraftItems.BENZENE);
                            entries.accept(SubstanceCraftItems.BRINE);
                            entries.accept(SubstanceCraftItems.CHLORINE);
                            entries.accept(SubstanceCraftItems.HYDROGEN);
                            entries.accept(SubstanceCraftItems.METHANE);
                            entries.accept(SubstanceCraftItems.NITROGEN);
                            entries.accept(SubstanceCraftItems.OXYGEN);
                            entries.accept(SubstanceCraftItems.NATURAL_GAS);
                            entries.accept(SubstanceCraftItems.PROPANE);
                            entries.accept(SubstanceCraftItems.ETHANE);
                            entries.accept(SubstanceCraftItems.BUTANE);
                            entries.accept(SubstanceCraftItems.METHYLAMINE);
                            entries.accept(SubstanceCraftItems.ETHYLENE);
                            entries.accept(SubstanceCraftItems.PROPYLENE);
                            entries.accept(SubstanceCraftItems.DIESEL);
                            entries.accept(SubstanceCraftItems.ETHANOL);
                            entries.accept(SubstanceCraftItems.AMMONIA);
                            entries.accept(SubstanceCraftItems.HYDROCHLORIC_ACID);
                        }).build());

    }

}
