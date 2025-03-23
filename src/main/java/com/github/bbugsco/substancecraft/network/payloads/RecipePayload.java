package com.github.bbugsco.substancecraft.network.payloads;

import com.github.bbugsco.substancecraft.network.SubstanceCraftNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

public record RecipePayload(RecipeHolder<?> recipe) implements CustomPacketPayload {

    public static final Type<RecipePayload> TYPE = new Type<>(SubstanceCraftNetworking.RECIPE_COUNT_PAYLOAD_TYPE);
    public static final StreamCodec<RegistryFriendlyByteBuf, RecipePayload> CODEC = CustomPacketPayload.codec(RecipePayload::write, RecipePayload::new);

    public RecipePayload(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        this(read(registryFriendlyByteBuf));
    }

    private static RecipeHolder<?> read(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        return RecipeHolder.STREAM_CODEC.decode(registryFriendlyByteBuf);
    }

    private void write(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        RecipeHolder.STREAM_CODEC.encode(registryFriendlyByteBuf, recipe);
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
