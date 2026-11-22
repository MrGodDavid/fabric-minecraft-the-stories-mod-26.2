package net.mrgoddavid.minecraftthestoriesmod.effect.content;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * @author Mr. GodDavid
 * @since 11/22/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public class PloddingOfAcaciania extends MobEffect {

    private static final Identifier SPEED_MODIFIER_ID = Constants.modId("plodding_of_acaciania");
    private static final Map<UUID, MovementTracker> TRACKERS = new WeakHashMap<>();
    private static final double MIN_DELTA_MOVEMENT = com.mrgoddavid.utils.Constants.EPSILON;
    private static final double MAX_DELTA_MOVEMENT = 10.0D;

    public PloddingOfAcaciania(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, LivingEntity mob, int amplification) {
        MovementTracker tracker = TRACKERS.computeIfAbsent(mob.getUUID(), id -> new MovementTracker(mob.position()));
        Vec3 currentPos = mob.position();
        double dx = currentPos.x() - tracker.lastPos.x();
        double dz = currentPos.z() - tracker.lastPos.z();
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        tracker.lastPos = currentPos;
        if (horizontalDistance > MIN_DELTA_MOVEMENT && horizontalDistance < MAX_DELTA_MOVEMENT) {
            tracker.totalDistance += horizontalDistance;
            this.updateSpeedMofidier(mob, tracker.totalDistance, amplification);
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    private void updateSpeedMofidier(LivingEntity mob, double distance, int amplification) {
        AttributeInstance speedAttribute = mob.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute == null) return;

        double penaltyPerBlock = 0.01 * (amplification + 1);
        double reduction = -Math.min(0.90, distance * penaltyPerBlock);
        speedAttribute.removeModifier(SPEED_MODIFIER_ID);
        speedAttribute.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_ID, reduction, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    @Override
    public void removeAttributeModifiers(@NonNull AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);
        AttributeInstance speedAttribute = attributes.getInstance(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null) {
            speedAttribute.removeModifier(SPEED_MODIFIER_ID);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }

    /**
     * @author Mr. GodDavid
     * @since 11/22/2026
     */
    private static class MovementTracker {

        Vec3 lastPos;
        double totalDistance = 0.0D;

        MovementTracker(Vec3 statePos) {
            this.lastPos = statePos;
        }
    }
}
