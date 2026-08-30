package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public class EnteredVillageWithFullEmeraldArmorTrigger extends SimpleCriterionTrigger<EnteredVillageWithFullEmeraldArmorTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> instance.matches(player));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ServerPlayer player) {
            boolean locationMatches = player.level().isVillage(player.blockPosition());
            boolean helmetMarches = player.getItemBySlot(EquipmentSlot.HEAD).is(MtsItems.EMERALD_HELMET);
            boolean chestplateMatches = player.getItemBySlot(EquipmentSlot.CHEST).is(MtsItems.EMERALD_CHESTPLATE);
            boolean leggingsMarches = player.getItemBySlot(EquipmentSlot.LEGS).is(MtsItems.EMERALD_LEGGINGS);
            boolean bootsMarches = player.getItemBySlot(EquipmentSlot.FEET).is(MtsItems.EMERALD_BOOTS);
            return locationMatches && helmetMarches && chestplateMatches && leggingsMarches && bootsMarches;
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return Optional.empty();
        }
    }
}
