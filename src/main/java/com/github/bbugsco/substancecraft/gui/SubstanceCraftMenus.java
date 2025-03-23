package com.github.bbugsco.substancecraft.gui;


import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.gui.menus.CatalyticReformerMenu;
import com.github.bbugsco.substancecraft.gui.menus.ElectrolysisMachineMenu;
import com.github.bbugsco.substancecraft.gui.menus.ExtractorMenu;
import com.github.bbugsco.substancecraft.gui.menus.FermentationTankMenu;
import com.github.bbugsco.substancecraft.gui.menus.HashPressMenu;
import com.github.bbugsco.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.bbugsco.substancecraft.gui.menus.MixerMenu;
import com.github.bbugsco.substancecraft.gui.menus.OxidizerMenu;
import com.github.bbugsco.substancecraft.gui.menus.RefineryMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class SubstanceCraftMenus {

    public static final MenuType<HashPressMenu> HASH_PRESS = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press"),
            new ExtendedScreenHandlerType<>(HashPressMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<RefineryMenu> REFINERY = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery"),
            new ExtendedScreenHandlerType<>(RefineryMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<CatalyticReformerMenu> CATALYTIC_REFORMER = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "catalytic_reformer"),
            new ExtendedScreenHandlerType<>(CatalyticReformerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<OxidizerMenu> OXIDIZER = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidizer"),
            new ExtendedScreenHandlerType<>(OxidizerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<ElectrolysisMachineMenu> ELECTROLYSIS_MACHINE = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_machine"),
            new ExtendedScreenHandlerType<>(ElectrolysisMachineMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<ExtractorMenu> EXTRACTOR = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extractor"),
            new ExtendedScreenHandlerType<>(ExtractorMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<MixerMenu> MIXER = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mixer"),
            new ExtendedScreenHandlerType<>(MixerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<HeatedMixerMenu> HEATED_MIXER = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "heated_mixer"),
            new ExtendedScreenHandlerType<>(HeatedMixerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<FermentationTankMenu> FERMENTATION_TANK = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "fermentation_tank"),
            new ExtendedScreenHandlerType<>(FermentationTankMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static void registerMenus() {

    }

}
