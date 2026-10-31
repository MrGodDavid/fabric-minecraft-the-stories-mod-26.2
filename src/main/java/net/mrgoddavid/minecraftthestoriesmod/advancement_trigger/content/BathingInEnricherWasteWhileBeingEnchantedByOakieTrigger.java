package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/20/2026
 */
public class BathingInEnricherWasteWhileBeingEnchantedByOakieTrigger extends SimpleCriterionTrigger<BathingInEnricherWasteWhileBeingEnchantedByOakieTrigger.TriggerInstance> {

    private static final Timer BATHING_TIME = new Timer(2400);

    @Override
    public @NonNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, OakieEntity oakie) {
        this.trigger(player, instance -> instance.matches(player, oakie));
    }

    /**
     * @author Mr. GodDavid
     * @since 10/20/2026
     */
    public record TriggerInstance(
            Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ServerPlayer player, OakieEntity oakie) {
            boolean isPlayerInEnricherFluid = BATHING_TIME.pass(() -> player.isInFluid(MtsTags.Fluids.ENRICHER_WASTE));
            boolean isPlayerInOakieRanges = oakie.isOwnerInEnchantingRange(player);
            return isPlayerInEnricherFluid && isPlayerInOakieRanges;
        }

        @Override
        public @NonNull Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}
