package net.mrgoddavid.minecraftthestoriesmod.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal.AnimalAggressionMeleeGoal;
import net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal.AnimalAggressionTargetGoal;
import net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal.NeutralMobAggressionGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
@Mixin(Mob.class)
public abstract class MobMixin {

    @Shadow
    @Final
    protected GoalSelector goalSelector;

    @Shadow
    @Final
    protected GoalSelector targetSelector;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void mts$addMobPublicEnemyGoals(EntityType<? extends LivingEntity> type, Level level, CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;

        if (mob instanceof Animal animal) {
            this.targetSelector.addGoal(1, new AnimalAggressionTargetGoal(animal));
            this.goalSelector.addGoal(2, new AnimalAggressionMeleeGoal(animal, 1.2D));

        } else if (mob instanceof NeutralMob) {
            this.targetSelector.addGoal(1, new NeutralMobAggressionGoal(mob));
        }
    }
}
