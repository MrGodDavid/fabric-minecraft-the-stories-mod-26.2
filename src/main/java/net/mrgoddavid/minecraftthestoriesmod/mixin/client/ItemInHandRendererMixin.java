package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsItemAnimations;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.item.content.CudgelItem;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsEntityAnimationAnimator;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 9/27/2026
 */
@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

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
        if (player.isScoping()) return;

        Item item = itemStack.getItem();
        boolean isMainHand = hand == InteractionHand.MAIN_HAND;
        HumanoidArm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
        float invert = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;

        if (item instanceof CudgelItem) {
            animateCudgels(player, attack, itemStack, inverseArmHeight, poseStack, submitNodeCollector, lightCoords, ci, invert, arm);
        }
        // I don't understand why I cannot replace the entire use animation since I have ci.cancel() at the end.
        // any transformation on the poseStack is just add animation layer on top of the existing use animation.
        // After I spent painfully about 24 hours, I solved this issue. It turns out that the first poseStack.translate()
        // cannot contain "inverseArmHeight". hahaha...
        else if (itemStack.is(MtsItems.TEST_LONG_STICK)) {
            animateAnimationTestItem(player, frameInterp, attack, itemStack, poseStack, submitNodeCollector, lightCoords, ci, invert, arm);
        }
    }

    @Unique
    private void animateAnimationTestItem(
            AbstractClientPlayer player,
            float frameInterp,
            float attack, // always goes from 0 -> 1
            ItemStack itemStack,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            CallbackInfo ci,
            float invert,
            HumanoidArm arm
    ) {
        poseStack.pushPose();

        MtsEntityAnimationAnimator.startAnimation(player, frameInterp, attack, poseStack, invert);
//        MtsEntityAnimationAnimator.rotateHorizontallyZLeft(poseStack, attack, 0, 45, 0.5f, 1.0f);
//        MtsEntityAnimationAnimator.rotateYRight(poseStack, attack, 0, 45, 0.5f, 1.0f);
        MtsItemAnimations.TEST_ANIMATION.animate(poseStack, attack);

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

    @Unique
    private void animateCudgels(
            AbstractClientPlayer player,
            float attack,
            ItemStack itemStack,
            float inverseArmHeight,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            CallbackInfo ci,
            float invert,
            HumanoidArm arm
    ) {
        poseStack.pushPose();
        poseStack.translate(invert * 0.56F, -0.52F + inverseArmHeight * -0.6F, -0.72F); // do this if you want to keep vanilla's translation.
        if (attack > 0.0F) {
            MtsItemAnimations.CudgelAnimations.swing(poseStack, attack, arm);
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
