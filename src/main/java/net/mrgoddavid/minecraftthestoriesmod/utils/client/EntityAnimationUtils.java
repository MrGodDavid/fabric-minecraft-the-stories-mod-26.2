package net.mrgoddavid.minecraftthestoriesmod.utils.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

import java.util.Objects;

/**
 * @author Mr. GodDavid
 * @since 10/11/2026
 */
public final class EntityAnimationUtils {

    public static void applyMobHeadRotation(EntityModel<? extends LivingEntityRenderState> model, float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 40f);

        Objects.requireNonNull(model.getChildPart("head")).yRot = headYaw * ((float) Math.PI / 180F);
        Objects.requireNonNull(model.getChildPart("head")).xRot = -headPitch * ((float) Math.PI / 180F);
    }

    private EntityAnimationUtils() {
    }
}
