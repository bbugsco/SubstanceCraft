package com.github.bbugsco.substancecraft.client;

import com.github.bbugsco.substancecraft.client.datagen.ModelGenerator;
import com.github.bbugsco.substancecraft.client.gui.SubstanceCraftScreens;
import com.github.bbugsco.substancecraft.client.network.SubstanceCraftClientNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClient implements ClientModInitializer, DataGeneratorEntrypoint {

    @Override
    public void onInitializeClient() {
        SubstanceCraftScreens.registerScreens();
        SubstanceCraftClientNetworking.init();
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelGenerator::new);
    }

}
