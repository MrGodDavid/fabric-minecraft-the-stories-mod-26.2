package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/11/2026
 */
public abstract class AbstractHasMobEffectTrigger extends SimpleCriterionTrigger<AbstractHasMobEffectTrigger.TriggerInstance> {

    private final Holder<MobEffect> effect;

    protected AbstractHasMobEffectTrigger(Holder<MobEffect> effect) {
        this.effect = effect;
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> instance.matches(player, this.effect));
    }

    /**
     * @author Mr. GodDavid
     * @since 10/11/2026
     */
    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ServerPlayer player, Holder<MobEffect> effect) {
            return player.hasEffect(effect);
        }
    }
}
