package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.Timer;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/10/2026
 */
public class BathingInEnricherWasteTrigger extends SimpleCriterionTrigger<BathingInEnricherWasteTrigger.TriggerInstance> {

    private static final Timer BATHING_TIME = new Timer(60);

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> instance.matches(player));
    }

    /**
     * @author Mr. GodDavid
     * @since 10/10/2026
     */
    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
        ).apply(instance, BathingInEnricherWasteTrigger.TriggerInstance::new));

        public boolean matches(ServerPlayer player) {
            return BATHING_TIME.pass(() -> player.isInFluid(MtsTags.Fluids.ENRICHER_WASTE));
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return Optional.empty();
        }
    }
}
