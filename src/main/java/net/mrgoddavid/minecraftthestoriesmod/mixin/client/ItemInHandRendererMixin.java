package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.CudgelAnimations;
import net.mrgoddavid.minecraftthestoriesmod.item.content.CudgelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 9/27/2026
 */
@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
    private void mts$applyCudgelItemAnimation(
            AbstractClientPlayer player,
            float frameInterp,
            float xRot,
            InteractionHand hand,
            float attack,
            ItemStack itemStack,
            float inverseArmHeight,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords, CallbackInfo ci
    ) {
        if (!(itemStack.getItem() instanceof CudgelItem)) {
            return;
        }
        if (player.isScoping()) {
            return;
        }
        boolean isMainHand = hand == InteractionHand.MAIN_HAND;
        HumanoidArm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
        float invert = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;

        poseStack.pushPose();
        poseStack.translate(invert * 0.56F, -0.52F + inverseArmHeight * -0.6F, -0.72F);
        if (attack > 0.0F) {
            CudgelAnimations.swing(poseStack, attack, arm);
        }
        ItemInHandRenderer renderer = (ItemInHandRenderer) (Object) this;
        renderer.renderItem(
                player,
                itemStack,
                arm == HumanoidArm.RIGHT ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
                poseStack,
                submitNodeCollector,
                lightCoords
        );
        poseStack.popPose();
        ci.cancel();
    }
}
