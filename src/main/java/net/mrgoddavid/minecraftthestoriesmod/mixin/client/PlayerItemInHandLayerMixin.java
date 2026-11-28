package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsDaffiAnimator;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 11/27/2026
 */
@Mixin(PlayerItemInHandLayer.class)
public class PlayerItemInHandLayerMixin {

    @Inject(method = "submitArmWithItem(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V",
            at = @At("HEAD")
    )
    private void mts$beforeItemSubmit(
            AvatarRenderState state,
            ItemStackRenderState item,
            ItemStack itemStack,
            HumanoidArm arm,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            CallbackInfo ci
    ) {
        Optional.ofNullable(Minecraft.getInstance().player).ifPresent(localPlayer -> {
            float partialTicks = MtsDaffiAnimator.getPartialTicks(state);
            float attackMainValue = localPlayer.getAttackAnim(partialTicks);

            if (itemStack.is(MtsItems.STRONG_IRON_BIG_MACE)) {
                MtsDaffiAnimator.thirdPersonAnimator()
                        .withItem(
                                MtsDaffiAnimator.heldItemAnimator()
                                        .addKeyFrame(MtsDaffiAnimator.FPInHandAnimKeyFrame()
                                                .moveForward(0.0F, 1.0F, 1, 12, null))
                        )
                        .animateItem(poseStack, attackMainValue, arm);
            }
        });
    }
}
