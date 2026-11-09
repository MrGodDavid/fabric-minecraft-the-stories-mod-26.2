package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleSpawner;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.ParticleSpawnMode;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTextures;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
@SuppressWarnings({"NullableProblems", "SpellCheckingInspection"})
public class OakieEntityRenderer extends AbstractWoodlinEntityRenderer<OakieEntity, OakieEntityRenderState, OakieEntityModel> {

    private static final int OAKIE_ENCHANTING_STAFF_GLOWING_COLOR = MathUtils.argbToColor(255, 3, 2, 8);

    public OakieEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new OakieEntityModel(context.bakeLayer(MtsEntityModelLayers.OAKIE)), 0.2f);
        this.addLayer(new OakieEyesLayer(this));
        this.addLayer(new OakieStaffGlowingLayer(this));
    }

    @Override
    public Identifier getTextureLocation(OakieEntityRenderState state) {
        return MtsEntityTextures.OAKIE;
    }

    @Override
    public OakieEntityRenderState createRenderState() {
        return new OakieEntityRenderState();
    }

    @Override
    public void extractRenderState(OakieEntity entity, OakieEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.abilityUseAnimationState.copyFrom(entity.healAnimationState);
        state.staffGlowingColor = this.selectStaffGlowingColor(entity);
    }

    /** Y = (R x 0.299) + (G x 0.587) + (B x 0.114) */
    private int selectStaffGlowingColor(OakieEntity oakie) {
        return oakie.isEnchanting() ? OAKIE_ENCHANTING_STAFF_GLOWING_COLOR : 0;
    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFD8FF14;
    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(OakieEntityRenderState renderState) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.fillCircle(mc,
                RANDOM_PULSE,
                MtsParticleTypes.OAKIE_REGENERATION_PARTICLE,
                renderState.entityUUID,
                "oakie_regeneration_particle",
                new Timer(40),
                renderState,
                0.0,
                OakieEntity.HEALING_RADIUS,
                2,
                ParticleSpawnMode.FILL_CIRCLE_EIGHT_DIR,
                true
        );
    }

    @Override
    protected void spawnEffectBorderParticles(OakieEntityRenderState renderState) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.circle(mc,
                RANDOM_PULSE,
                renderState.isAlerting ? MtsParticleTypes.RED_CLOUD_PARTICLE : MtsParticleTypes.GREEN_CLOUD_PARTICLE,
                renderState.entityUUID,
                "oakie_particle_circle",
                new Timer(60),
                renderState,
                0.5,
                OakieEntity.HEALING_RADIUS,
                ParticleSpawnMode.CIRCLE_SIXTEEN_DIR,
                true
        );
    }
}
