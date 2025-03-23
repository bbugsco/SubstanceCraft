package com.github.bbugsco.substancecraft.client.gui;

import com.github.bbugsco.substancecraft.client.gui.screens.CatalyticReformerScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.ElectrolysisMachineScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.ExtractorScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.FermentationTankScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.HashPressScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.HeatedMixerScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.MixerScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.OxidizerScreen;
import com.github.bbugsco.substancecraft.client.gui.screens.RefineryScreen;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class SubstanceCraftScreens {

    public static void registerScreens() {
        MenuScreens.register(SubstanceCraftMenus.HASH_PRESS, HashPressScreen::new);
        MenuScreens.register(SubstanceCraftMenus.REFINERY, RefineryScreen::new);
        MenuScreens.register(SubstanceCraftMenus.OXIDIZER, OxidizerScreen::new);
        MenuScreens.register(SubstanceCraftMenus.ELECTROLYSIS_MACHINE, ElectrolysisMachineScreen::new);
        MenuScreens.register(SubstanceCraftMenus.CATALYTIC_REFORMER, CatalyticReformerScreen::new);
        MenuScreens.register(SubstanceCraftMenus.EXTRACTOR, ExtractorScreen::new);
        MenuScreens.register(SubstanceCraftMenus.MIXER, MixerScreen::new);
        MenuScreens.register(SubstanceCraftMenus.HEATED_MIXER, HeatedMixerScreen::new);
        MenuScreens.register(SubstanceCraftMenus.FERMENTATION_TANK, FermentationTankScreen::new);
    }

}
