package net.mrgoddavid.minecraftthestoriesmod.utils.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/11/2026
 */
public final class EntityAnimationUtils {

    public static void startAnimation(AbstractClientPlayer player, float frameInterp, float attack, PoseStack poseStack, float invert) {
        float rise = 0.0f;
        boolean isSelectedItem = player.getMainHandItem().is(MtsItems.TEST_LONG_STICK);
        if (isSelectedItem && attack <= 0.0f) {
            float attackStrength = Mth.clamp(player.getAttackStrengthScale(frameInterp), 0.0F, 1.0F);
            if (attackStrength <= 0.0f) {
                rise = 0.01f * (1.0F - attackStrength);
            }
        }

        poseStack.translate(invert * 0.56F, -0.52F + rise, -0.72F);
    }

    private static boolean isWeaponRecharging(AbstractClientPlayer player, float frameInterp) {
        return player.getAttackStrengthScale(frameInterp) < 1.0F;
    }

    public static float progress(float tick, float start, float end) {
        return Mth.clamp(Mth.inverseLerp(tick, start, end), 0.0F, 1.0F);
    }

    public static void applyMobHeadRotation(EntityModel<? extends LivingEntityRenderState> model, float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 40f);

        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
                .orElse(model.root()).yRot = headYaw * ((float) Math.PI / 180F);
        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
                .orElse(model.root()).xRot = multiplierFromEntityType(model) * headPitch * ((float) Math.PI / 180F);
    }

    /**
     * Don't ask me (Mr. GodDavid) why we have this here. It works here and please DO NOT TRY TO SIMPLIFY IT!
     *
     * @param model model.
     * @return multiplier based on the type of model.
     */
    private static float multiplierFromEntityType(EntityModel<? extends LivingEntityRenderState> model) {
        if (model instanceof BrownBearEntityModel) {
            return -1.0f;
        }
        return 1.0f;
    }

    private EntityAnimationUtils() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
