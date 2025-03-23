package com.github.bbugsco.substancecraft.network.payloads;

import com.github.bbugsco.substancecraft.network.SubstanceCraftNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record RecipeCountPayload(int totalRecipes) implements CustomPacketPayload {

    public static final Type<RecipeCountPayload> TYPE = new Type<>(SubstanceCraftNetworking.RECIPE_PAYLOAD_TYPE);
    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeCountPayload> CODEC = CustomPacketPayload.codec(RecipeCountPayload::write, RecipeCountPayload::new);


    public RecipeCountPayload(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        this(read(registryFriendlyByteBuf));
    }

    private static int read(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        return registryFriendlyByteBuf.readVarInt();
    }

    private void write(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        registryFriendlyByteBuf.writeVarInt(totalRecipes);
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
