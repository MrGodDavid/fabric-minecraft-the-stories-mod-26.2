package net.mrgoddavid.minecraftthestoriesmod.item.content;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 10/10/2026
 */
public class CursedItem extends Item {

    private final Holder<MobEffect> effect;

    public CursedItem(Properties properties, Holder<MobEffect> effect) {
        super(properties);
        this.effect = effect;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (owner instanceof Player player
                && (player.getMainHandItem().getItem() instanceof CursedItem || player.getOffhandItem().getItem() instanceof CursedItem)) {
            player.addEffect(new MobEffectInstance(effect, 40, 0, false, true, true));
        }
        super.inventoryTick(itemStack, level, owner, slot);
    }
}
