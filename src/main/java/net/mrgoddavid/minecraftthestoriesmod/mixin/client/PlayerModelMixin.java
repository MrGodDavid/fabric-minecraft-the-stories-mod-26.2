package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsPlayerAnimations;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.DodgeRollAnimationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
@Mixin(PlayerModel.class)
public class PlayerModelMixin {

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V", at = @At("TAIL"))
    private void mts$addPlayerAnimations(AvatarRenderState state, CallbackInfo ci) {
        PlayerModel playerModel = (PlayerModel) (Object) this;

        if (DodgeRollAnimationManager.isRolling(state.id)) {
            float partialTick = state.ageInTicks - Mth.floor(state.ageInTicks);
            float animationFrame = DodgeRollAnimationManager.getProgress(state.id, partialTick);
            MtsPlayerAnimations.TEST_ANIMATION.animate(playerModel, animationFrame);
        }
    }
}
