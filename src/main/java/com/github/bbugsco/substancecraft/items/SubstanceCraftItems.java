package com.github.bbugsco.substancecraft.items;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class SubstanceCraftItems {

    public static CreativeModeTab SUBSTANCES_ITEM_GROUP;

    public static final Item MARIJUANA = registerItem("marijuana", Item::new, new Item.Properties());
    public static final Item MARIJUANA_TRIM = registerItem("marijuana_trim", Item::new, new Item.Properties());
    public static final Item HASH = registerItem("hash", Item::new, new Item.Properties());
    public static final Item EMPTY_DAB_RIG = registerItem("empty_dab_rig", DabRig.EmptyDabRigItem::new, new Item.Properties());
    public static final Item DAB_RIG = registerItem("dab_rig", DabRig.DabRigItem::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(-1).build()));
    public static final Item DIPHENHYDRAMINE = registerItem("diphenhydramine", SimpleDrugs.Diphenhydramine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item KETAMINE = registerItem("ketamine", SimpleDrugs.Ketamine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item OIL = registerItem("oil", Item::new, new Item.Properties());
    public static final Item PETROLEUM_NAPHTHA = registerItem("petroleum_naphtha", Item::new, new Item.Properties());
    public static final Item KEROSENE = registerItem("kerosene", Item::new, new Item.Properties());
    public static final Item GASOLINE = registerItem("gasoline", Item::new, new Item.Properties());
    public static final Item METHANOL = registerItem("methanol", Item::new, new Item.Properties());
    public static final Item BENZENE = registerItem("benzene", Item::new, new Item.Properties());
    public static final Item CHLOROFORM = registerItem("chloroform", Item::new, new Item.Properties());
    public static final Item FORMALDEHYDE = registerItem("formaldehyde", Item::new, new Item.Properties());
    public static final Item TOLUENE = registerItem("toluene", Item::new, new Item.Properties());
    public static final Item SALT = registerItem("salt", Item::new, new Item.Properties());
    public static final Item BRINE = registerItem("brine", Item::new, new Item.Properties());
    public static final Item SODIUM_HYDROXIDE = registerItem("sodium_hydroxide", Item::new, new Item.Properties());
    public static final Item HYDROGEN = registerItem("hydrogen", Item::new, new Item.Properties());
    public static final Item CHLORINE = registerItem("chlorine", Item::new, new Item.Properties());
    public static final Item METHANE = registerItem("methane", Item::new, new Item.Properties());
    public static final Item NITROGEN = registerItem("nitrogen", Item::new, new Item.Properties());
    public static final Item OXYGEN = registerItem("oxygen", Item::new, new Item.Properties());
    public static final Item NATURAL_GAS = registerItem("natural_gas", Item::new, new Item.Properties());
    public static final Item ETHANE = registerItem("ethane", Item::new, new Item.Properties());
    public static final Item PROPANE = registerItem("propane", Item::new, new Item.Properties());
    public static final Item BUTANE = registerItem("butane", Item::new, new Item.Properties());
    public static final Item METHYLAMINE = registerItem("methylamine", Item::new, new Item.Properties());
    public static final Item ETHYLENE = registerItem("ethylene", Item::new, new Item.Properties());
    public static final Item PROPYLENE = registerItem("propylene", Item::new, new Item.Properties());
    public static final Item DIESEL = registerItem("diesel", Item::new, new Item.Properties());
    public static final Item AMMONIA = registerItem("ammonia", Item::new, new Item.Properties());
    public static final Item CORN = registerItem("corn", Item::new, new Item.Properties());
    public static final Item ETHANOL = registerItem("ethanol", Item::new, new Item.Properties());
    public static final Item YEAST = registerItem("yeast", Item::new, new Item.Properties());
    public static final Item HYDROCHLORIC_ACID = registerItem("hydrochloric_acid", Item::new, new Item.Properties());
    public static final Item ERGOT = registerItem("ergot", Item::new, new Item.Properties());
    public static final Item BENZALDEHYDE = registerItem("benzaldehyde", Item::new, new Item.Properties());
    public static final Item NITRIC_ACID = registerItem("nitric_acid", Item::new, new Item.Properties());
    public static final Item NITROMETHANE = registerItem("nitromethane", Item::new, new Item.Properties());
    public static final Item ACETIC_ACID = registerItem("acetic_acid", Item::new, new Item.Properties());
    public static final Item AMMONIUM_ACETATE = registerItem("ammonium_acetate", Item::new, new Item.Properties());
    public static final Item CARBON_MONOXIDE = registerItem("carbon_monoxide", Item::new, new Item.Properties());
    public static final Item COKE = registerItem("coke", Item::new, new Item.Properties());
    public static final Item BETA_NITROSTYRENE = registerItem("beta_nitrostyrene", Item::new, new Item.Properties());
    public static final Item MALEIC_ANHYDRIDE = registerItem("maleic_anhydride", Item::new, new Item.Properties());
    public static final Item TETRAHYDROFURAN = registerItem("tetrahydrofuran", Item::new, new Item.Properties());
    public static final Item TWO_C_H = registerItem("2c_h", Item::new, new Item.Properties());
    public static final Item BROMINE = registerItem("bromine", Item::new, new Item.Properties());
    public static final Item BROMIDE = registerItem("bromide", Item::new, new Item.Properties());
    public static final Item TWO_C_B = registerItem("2c_b", Item::new, new Item.Properties());

    public static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
        Item item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void registerItems() {
        registerItemGroups();
    }

    private static void registerItemGroups() {
        SUBSTANCES_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substances"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.substances"))
                        .icon(() -> new ItemStack(Items.OMINOUS_BOTTLE)).displayItems((displayContext, entries) -> {
                            entries.accept(SubstanceCraftItems.MARIJUANA);
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
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
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SALT));
                            entries.accept(SubstanceCraftItems.DIPHENHYDRAMINE);
                            entries.accept(SubstanceCraftItems.KETAMINE);
                            entries.accept(SubstanceCraftItems.TWO_C_H);
                            entries.accept(SubstanceCraftItems.TWO_C_B);
                            entries.accept(SubstanceCraftItems.SALT);
                            entries.accept(SubstanceCraftItems.SODIUM_HYDROXIDE);
                            entries.accept(SubstanceCraftItems.COKE);
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
                            entries.accept(SubstanceCraftItems.BENZALDEHYDE);
                            entries.accept(SubstanceCraftItems.NITRIC_ACID);
                            entries.accept(SubstanceCraftItems.NITROMETHANE);
                            entries.accept(SubstanceCraftItems.ACETIC_ACID);
                            entries.accept(SubstanceCraftItems.AMMONIUM_ACETATE);
                            entries.accept(SubstanceCraftItems.CARBON_MONOXIDE);
                            entries.accept(SubstanceCraftItems.BETA_NITROSTYRENE);
                            entries.accept(SubstanceCraftItems.MALEIC_ANHYDRIDE);
                            entries.accept(SubstanceCraftItems.TETRAHYDROFURAN);
                            entries.accept(SubstanceCraftItems.BROMINE);
                            entries.accept(SubstanceCraftItems.BROMIDE);
                        }).build());

    }

}
