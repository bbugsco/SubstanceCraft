package com.github.bbugsco.substancecraft.recipe.generic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class OneInputRecipeSerializer<T extends OneInputRecipe> implements RecipeSerializer<T> {

    private final OneInputRecipe.Factory<T> factory;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> packetCodec;

    public OneInputRecipeSerializer(OneInputRecipe.Factory<T> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.mapCodec(
                (instance) -> instance.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(OneInputRecipe::getInput),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(OneInputRecipe::getResult),
                        ItemStack.CODEC.listOf().fieldOf("byproducts").forGetter(OneInputRecipe::getByproducts),
                        Codec.INT.fieldOf("time").orElse(200).forGetter(OneInputRecipe::getTime)
                ).apply(instance, factory::create));
        packetCodec = StreamCodec.of(this::write, this::read);
    }

    @Override
    public @NotNull MapCodec<T> codec() {
        return codec;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
        return packetCodec;
    }

    private T read(RegistryFriendlyByteBuf buf) {
        Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
        ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
        NonNullList<ItemStack> byproducts =  NonNullList.withSize(buf.readVarInt(), ItemStack.EMPTY);
        byproducts.replaceAll(ingredient -> ItemStack.STREAM_CODEC.decode(buf));
        return this.factory.create(input, output, byproducts, buf.readInt());
    }

    private void write(RegistryFriendlyByteBuf buf, T recipe) {
        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.getInput());
        ItemStack.STREAM_CODEC.encode(buf, recipe.getResult());
        buf.writeVarInt(recipe.getByproducts().size());
        for (ItemStack byproduct : recipe.getByproducts()) {
            ItemStack.STREAM_CODEC.encode(buf, byproduct);
        }
        buf.writeInt(recipe.getTime());
    }

}
