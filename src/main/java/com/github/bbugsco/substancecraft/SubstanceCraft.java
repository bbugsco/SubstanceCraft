package com.github.bbugsco.substancecraft;

import com.github.bbugsco.substancecraft.network.SubstanceCraftNetworking;
import com.github.bbugsco.substancecraft.recipe.SubstanceCraftRecipes;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubstanceCraft implements ModInitializer {

    public static final String MOD_ID = "substancecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SubstanceCraftRecipes.registerRecipes();
        SubstanceCraftNetworking.init();
    }
}
