package net.mrgoddavid.minecraftthestoriesmod.world.drink;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * @author Mr. GodDavid
 * @since 10/31/2026 -> Today's Halloween!
 */
public record DietaryWaterProperties(int hydrationRestoration, float hydrationExhaustion) {

    public static final Codec<DietaryWaterProperties> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.INT.fieldOf("hydrationRestoration").forGetter(DietaryWaterProperties::hydrationRestoration),
                    Codec.FLOAT.fieldOf("hydrationExhaustion").forGetter(DietaryWaterProperties::hydrationExhaustion))
                    .apply(instance, DietaryWaterProperties::new));

    public static final StreamCodec<FriendlyByteBuf, DietaryWaterProperties> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, DietaryWaterProperties::hydrationRestoration,
            ByteBufCodecs.FLOAT, DietaryWaterProperties::hydrationExhaustion,
            DietaryWaterProperties::new
    );

    public static final DietaryWaterProperties DEFAULT = new DietaryWaterProperties(0, 0.0F);

    /**
     * @author Mr. GodDavid
     * @since 10/31/2026 -> Today's Halloween!
     */
    public static class Builder {
        private int thirstRestoration;
        private float thirstExhaustion;

        public DietaryWaterProperties.Builder thirstRestoration(int thirstRestoration) {
            this.thirstRestoration = thirstRestoration;
            return this;
        }

        public DietaryWaterProperties.Builder thirstExhaustion(float thirstExhaustion) {
            this.thirstExhaustion = thirstExhaustion / 1000F;
            return this;
        }

        public DietaryWaterProperties build() {
            return new DietaryWaterProperties(thirstRestoration, thirstExhaustion);
        }
    }
}
