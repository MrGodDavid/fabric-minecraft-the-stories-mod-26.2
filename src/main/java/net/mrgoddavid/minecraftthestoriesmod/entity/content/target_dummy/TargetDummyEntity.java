package net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.networking.MtsPacketHelper;

/**
 * @author Mr. GodDavid
 * @since 9/25/2026
 */
public class TargetDummyEntity extends LivingEntity {

    private static final int FREEZE_TICK = 5;
    private int hitFreezeTick;

    public TargetDummyEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public void aiStep() {
        if (this.hitFreezeTick > 0) {
            this.hitFreezeTick--;
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);
            return;
        }
        super.aiStep();
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        float previousHealth = this.getHealth();
        float safeDamage = Math.min(damage, previousHealth - 1.0F);
        if (safeDamage <= 0.0F) return false;

        boolean damaged = super.hurtServer(level, source, damage);
        if (!damaged) return false;

        float actualDamage = previousHealth - this.getHealth();
        this.setHealth(this.getMaxHealth());
        this.hitFreezeTick = FREEZE_TICK;
        if  (actualDamage > 0.0F) {
            MtsPacketHelper.sendTargetDummyDamageNumber(level, this, actualDamage);
        }
        return true;
    }
}
