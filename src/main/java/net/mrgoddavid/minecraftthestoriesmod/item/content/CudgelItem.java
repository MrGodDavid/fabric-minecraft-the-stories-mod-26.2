package net.mrgoddavid.minecraftthestoriesmod.item.content;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.datagen.MtsDamageTypes;
import net.mrgoddavid.minecraftthestoriesmod.sound.MtsSounds;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/27/2026
 */
public class CudgelItem extends Item {

    private final float knockBack;

    public CudgelItem(ToolMaterial toolMaterial, float attackDamageBaseline, float attackSpeedBaseline, float knockBack, Properties properties) {
        super(toolMaterial.applySwordProperties(properties, attackDamageBaseline, attackSpeedBaseline));
        this.knockBack = knockBack;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        Level level = attacker.level();
        if (!level.isClientSide()) {
            level.playSound(
                    null,
                    mob.getX(), mob.getY(), mob.getZ(),
                    MtsSounds.BONK, SoundSource.PLAYERS,
                    1.0F,
                    0.8F);
        }

        DamageSource damageSource = MtsDamageTypes.create(attacker.level(), MtsDamageTypes.CUDGEL_BONK);
        mob.knockback(knockBack, attacker.getX() - mob.getX(), attacker.getZ() - mob.getZ(), damageSource, 0.0F);
    }
}
