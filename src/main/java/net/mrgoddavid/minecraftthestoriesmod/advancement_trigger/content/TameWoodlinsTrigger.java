package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 11/1/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public class TameWoodlinsTrigger extends SimpleCriterionTrigger<TameWoodlinsTrigger.TriggerInstance> {

    @Override
    public @NonNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, LivingEntity woodlins) {
        this.trigger(player, instance -> instance.matches(player, woodlins));
    }

    /**
     * @author Mr. GodDavid
     * @since 11/1/2026
     */
    public record TriggerInstance(Optional<ContextAwarePredicate> player,
                                  Optional<ContextAwarePredicate> woodlins) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                ContextAwarePredicate.CODEC.optionalFieldOf("woodlins").forGetter(TriggerInstance::woodlins)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ServerPlayer player, LivingEntity woodlins) {
            if (!(woodlins instanceof TamableAnimal woodlin)) return false;
            EntityReference<LivingEntity> ownerRef = woodlin.getOwnerReference();
            if (ownerRef == null) return false;
            return ownerRef.matches(player);
        }

        @Override
        public @NonNull Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}
