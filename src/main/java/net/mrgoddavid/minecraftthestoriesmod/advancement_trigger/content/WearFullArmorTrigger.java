package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;

import java.util.Optional;


/**
 * @author Mr. GodDavid
 * @since 10/10/2026
 */
public abstract class WearFullArmorTrigger extends SimpleCriterionTrigger<WearFullArmorTrigger.TriggerInstance> {

    private final ArmorType armorType;

    protected WearFullArmorTrigger(ArmorType armorType) {
        this.armorType = armorType;
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> instance.matches(this.armorType, player));
    }

    /**
     * @author Mr. GodDavid
     * @since 10/10/2026
     */
    public record TriggerInstance(
            Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ArmorType armorType, ServerPlayer player) {
            return switch (armorType) {
                case ArmorType.STRONG_AMETHYST -> this.checkWhetherWearingStrongAmethystArmor(player);
                case ArmorType.STRONG_TOPAZ -> this.checkWhetherWearingStrongTopazArmor(player);
                case ArmorType.STRONG_RUBY -> this.checkWhetherWearingStrongRubyArmor(player);
            };
        }

        private boolean checkWhetherWearingStrongRubyArmor(ServerPlayer player) {
            return checkWhetherWearingFullArmor(player, MtsItems.STRONG_RUBY_HELMET, MtsItems.STRONG_RUBY_CHESTPLATE, MtsItems.STRONG_RUBY_LEGGINGS, MtsItems.STRONG_RUBY_BOOTS);
        }

        private boolean checkWhetherWearingStrongTopazArmor(ServerPlayer player) {
            return checkWhetherWearingFullArmor(player, MtsItems.STRONG_TOPAZ_HELMET, MtsItems.STRONG_TOPAZ_CHESTPLATE, MtsItems.STRONG_TOPAZ_LEGGINGS, MtsItems.STRONG_TOPAZ_BOOTS);
        }

        private boolean checkWhetherWearingStrongAmethystArmor(ServerPlayer player) {
            return checkWhetherWearingFullArmor(player, MtsItems.STRONG_AMETHYST_HELMET, MtsItems.STRONG_AMETHYST_CHESTPLATE, MtsItems.STRONG_AMETHYST_LEGGINGS, MtsItems.STRONG_AMETHYST_BOOTS);
        }

        private boolean checkWhetherWearingFullArmor(ServerPlayer player, Item helmet, Item chestplate, Item leggings, Item boots) {
            return player.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                    && (player.getItemBySlot(EquipmentSlot.CHEST).is(chestplate) || player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA))
                    && player.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                    && player.getItemBySlot(EquipmentSlot.FEET).is(boots);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/10/2026
     */
    public enum ArmorType {
        STRONG_AMETHYST,
        STRONG_TOPAZ,
        STRONG_RUBY;
    }
}
