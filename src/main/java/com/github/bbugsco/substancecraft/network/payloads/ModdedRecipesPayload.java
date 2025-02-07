package com.github.bbugsco.substancecraft.network.payloads;

import com.github.bbugsco.substancecraft.network.SubstanceCraftNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record ModdedRecipesPayload(List<RecipeHolder<?>> recipes) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ModdedRecipesPayload> TYPE = new CustomPacketPayload.Type<>(SubstanceCraftNetworking.RECIPE_PAYLOAD_TYPE);
    public static final StreamCodec<RegistryFriendlyByteBuf, ModdedRecipesPayload> CODEC = CustomPacketPayload.codec(ModdedRecipesPayload::write, ModdedRecipesPayload::new);

    public ModdedRecipesPayload(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        this(readFromBuffer(registryFriendlyByteBuf));
    }

    private static List<RecipeHolder<?>> readFromBuffer(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        List<RecipeHolder<?>> recipes = new ArrayList<>();
        for (int i = 0; i < registryFriendlyByteBuf.readVarInt(); i++) {
            recipes.add(RecipeHolder.STREAM_CODEC.decode(registryFriendlyByteBuf));
        }
        return recipes;
    }

    private void write(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        registryFriendlyByteBuf.writeVarInt(recipes.size());
        for (RecipeHolder<?> recipeHolder : recipes) {
            RecipeHolder.STREAM_CODEC.encode(registryFriendlyByteBuf, recipeHolder);
        }
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
