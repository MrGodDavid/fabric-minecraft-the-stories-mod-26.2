package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/20/2026
 */
public class HitEnemyWithCudgelTrigger extends SimpleCriterionTrigger<HitEnemyWithCudgelTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> instance.matches(player));
    }

    /**
     * @author Mr. GodDavid
     * @since 10/20/2026
     */
    public record TriggerInstance(Optional<ContextAwarePredicate> player,
                                  Optional<ItemPredicate> weapon,
                                  Optional<ContextAwarePredicate> enemy
    ) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                ItemPredicate.CODEC.optionalFieldOf("weapon").forGetter(TriggerInstance::weapon),
                ContextAwarePredicate.CODEC.optionalFieldOf("enemy").forGetter(TriggerInstance::enemy)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ServerPlayer player) {
            LivingEntity lastHurtMob = player.getLastHurtMob();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            boolean isWeaponCorrect = mainHandItem.is(MtsTags.Items.MTS_CUDGELS) || offHandItem.is(MtsTags.Items.MTS_CUDGELS);
            boolean isLastHurtMobAnEnemy = lastHurtMob instanceof Enemy;
            return isWeaponCorrect && isLastHurtMobAnEnemy;
        }

        @Override
        public @NonNull Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}
