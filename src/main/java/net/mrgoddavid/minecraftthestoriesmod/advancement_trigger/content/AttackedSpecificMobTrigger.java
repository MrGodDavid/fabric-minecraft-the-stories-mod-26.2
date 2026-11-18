package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 11/17/2026
 */
public class AttackedSpecificMobTrigger extends SimpleCriterionTrigger<AttackedSpecificMobTrigger.TriggerInstance> {

    public <T extends LivingEntity> void trigger(ServerPlayer player, Class<T> hurtMobClass) {
        this.trigger(player, instance -> instance.matches(player, hurtMobClass));
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    /**
     * @author Mr. GodDavid
     * @since 11/17/2026
     */
    public record TriggerInstance(
            Optional<ContextAwarePredicate> player
    ) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player))
                .apply(instance, AttackedSpecificMobTrigger.TriggerInstance::new)
        );

        public <T extends LivingEntity> boolean matches(ServerPlayer player, Class<T> hurtMobClass) {
            LivingEntity lastHurtMob = player.getLastHurtMob();
            if (lastHurtMob == null) return false;
            return hurtMobClass.isInstance(lastHurtMob);
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}
