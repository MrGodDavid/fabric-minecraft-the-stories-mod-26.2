package net.mrgoddavid.minecraftthestoriesmod.utils.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/11/2026
 */
public final class EntityAnimationUtils {

    public static void applyMobHeadRotation(EntityModel<? extends LivingEntityRenderState> model, float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 40f);

        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
                .orElse(model.root()).yRot = headYaw * ((float) Math.PI / 180F);
        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
                .orElse(model.root()).xRot = getEntityTypeMultiplier(model) * headPitch * ((float) Math.PI / 180F);
    }

    /**
     * Don't ask me (Mr. GodDavid) why we have this here. It works here and please DO NOT TRY TO SIMPLIFY IT!
     *
     * @param model model.
     * @return multiplier based on the type of model.
     */
    private static float getEntityTypeMultiplier(EntityModel<? extends LivingEntityRenderState> model) {
        if (model instanceof BrownBearEntityModel) {
            return -1.0f;
        }
        return 1.0f;
    }

    private EntityAnimationUtils() throws IllegalAccessException {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
