package net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleSpawner;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.ParticleSpawnMode;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.TargetDummyDamageManager;
import net.mrgoddavid.minecraftthestoriesmod.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTextures;
import net.mrgoddavid.minecraftthestoriesmod.utils.dave.RandomPulse;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/25/2026
 */
public class TargetDummyEntityRenderer extends LivingEntityRenderer<TargetDummyEntity, TargetDummyRenderState, TargetDummyModel> {

    private static final RandomPulse pulse = RandomPulse.builder(100).origin(0).bound(100).probability(50).build();

    public TargetDummyEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new TargetDummyModel(context.bakeLayer(MtsEntityModelLayers.TARGET_DUMMY)), 0.6F);
    }

    @Override
    public @NonNull Identifier getTextureLocation(TargetDummyRenderState state) {
        return MtsEntityTextures.TARGET_DUMMY;
    }

    @Override
    public TargetDummyRenderState createRenderState() {
        return new TargetDummyRenderState();
    }

    @Override
    public void submit(TargetDummyRenderState state, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        Minecraft mc = Minecraft.getInstance();

        // Spawn particles around entity
        pulse.tick();
        MtsParticleSpawner.spawnCircle(mc, pulse, MtsParticleTypes.ENRICHER_WASTE_PARTICLE, state, 2, 4, ParticleSpawnMode.CIRCLE_SIXTEEN_DIR, true);
        MtsParticleSpawner.spawnSquare(mc, pulse, MtsParticleTypes.COLORED_CLOUD_PARTICLE, state, 3, 5, 1, ParticleSpawnMode.SQUARE_FILL_IN, false);

        // If the entity was hurt by the player.
        if (TargetDummyDamageManager.getDamageNumbers().isEmpty()) return;

        // Render the text into the world space from client side. (Pretty sure this is in the client side.)
        Font font = mc.font;
        for (TargetDummyDamageManager.TargetDummyDamageNumber damageNumber : TargetDummyDamageManager.getDamageNumbers()) {
            String text = formatText(damageNumber.getDamage());
            float width = font.width(text);
            int alpha = (int) (damageNumber.getAlpha() * 255.0F);
            int color = (alpha << 24) | 0xFFFFFF;

            poseStack.pushPose();
            poseStack.translate(damageNumber.getXOffset(), damageNumber.getY() - state.y, damageNumber.getZOffset());
            poseStack.mulPose(new Quaternionf(camera.orientation));
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            poseStack.scale(-0.025F, -0.025F, 0.025F);
            submitNodeCollector.submitText(poseStack, -width / 2.0F, -4.0F, Component.literal(text).getVisualOrderText(), false, Font.DisplayMode.SEE_THROUGH, 0xF000F0, color, 0, 0);
            poseStack.popPose();
        }
    }

    private String formatText(float damage) {
        if (damage == Math.round(damage)) {
            return Integer.toString(Math.round(damage));
        }
        return String.format("%.1f", damage);
    }
}
