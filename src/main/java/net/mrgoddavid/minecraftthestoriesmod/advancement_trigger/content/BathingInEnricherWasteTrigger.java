package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Mr. GodDavid
 * @since 10/10/2026
 */
public class BathingInEnricherWasteTrigger extends SimpleCriterionTrigger<BathingInEnricherWasteTrigger.TriggerInstance> {

    private static final int REQUIRED_BATHING_DURATION = 1200;
    private final Map<UUID, Timer> bathingTimers = new HashMap<>();

    @Override
    public @NonNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        UUID uuid = player.getUUID();
        Timer timer = bathingTimers.computeIfAbsent(uuid, ignored -> new Timer(REQUIRED_BATHING_DURATION));
        if (timer.pass(() -> TriggerInstance.matches(player))) {
            this.trigger(player, instance -> true);
            timer.reset();
        }
    }

    public void removePlayer(ServerPlayer player) {
        this.bathingTimers.remove(player.getUUID());
    }

    /**
     * @author Mr. GodDavid
     * @since 10/10/2026
     */
    public record TriggerInstance(
            Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
        ).apply(instance, BathingInEnricherWasteTrigger.TriggerInstance::new));

        public static boolean matches(ServerPlayer player) {
            return player.isInFluid(MtsTags.Fluids.ENRICHER_WASTE);
        }

        @Override
        public @NonNull Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}
