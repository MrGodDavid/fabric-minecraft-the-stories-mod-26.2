package net.mrgoddavid.minecraftthestoriesmod.effect.content;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.mrgoddavid.minecraftthestoriesmod.datagen.MtsDamageTypes;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 10/18/2026
 */
public class LeechOfOakieEffect extends MobEffect {

    public LeechOfOakieEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        mob.hurtServer(serverLevel, MtsDamageTypes.create(serverLevel, MtsDamageTypes.LEECH_OF_OAKIE), 0.25f * (amplification + 1));
        this.spreadEffectToOtherEnemies(serverLevel, mob, amplification);
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    private void spreadEffectToOtherEnemies(ServerLevel serverLevel, LivingEntity livingEntity, int amplification) {
        AABB spreadRegion = livingEntity.getBoundingBox().inflate(amplification + 1);
        List<LivingEntity> enemies = serverLevel.getEntitiesOfClass(LivingEntity.class, spreadRegion);
        for (LivingEntity enemy : enemies) {
            if (enemy instanceof Mob mob) {
                // 5% change spreading to all surrounding enemies.
                if (serverLevel.getRandom().nextInt(20) == 0) {
                    mob.hurtServer(serverLevel, MtsDamageTypes.create(serverLevel, MtsDamageTypes.LEECH_OF_OAKIE), 0.25f * (amplification + 1));
                }
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
