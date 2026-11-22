package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleSpawner;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.ParticleSpawnMode;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsParticleTypeRegistries;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class BirchiaEntityRenderer extends AbstractWoodlinEntityRenderer<BirchiaEntity, BirchiaEntityRenderState, BirchiaEntityModel> {

    public BirchiaEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BirchiaEntityModel(context.bakeLayer(MtsEntityModelLayers.BIRCHIA)), 0.2f);
    }

    @Override
    public void extractRenderState(BirchiaEntity entity, BirchiaEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.abilityUseAnimationState.copyFrom(entity.jumpBoostAnimationState);
    }

    @Override
    protected SimpleParticleType getWoodlinNormalParticleType() {
        return MtsParticleTypes.GREEN_CLOUD_PARTICLE;
    }

    @Override
    protected SimpleParticleType getWoodlinWarningParticleType() {
        return MtsParticleTypes.ORANGE_CLOUD_PARTICLE;
    }

    @Override
    protected SimpleParticleType getWoodlinAttackingParticleType() {
        return MtsParticleTypes.RED_CLOUD_PARTICLE;
    }

    @Override
    protected void spawnEffectBorderParticles(BirchiaEntityRenderState state) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.equilibriumTriangle(
                mc,
                RANDOM_PULSE,
                super.getWoodlinBorderParticleTypes(state),
                state.entityUUID,
                "birchia_border_particles",
                new Timer(40),
                state,
                0.0,
                BirchiaEntity.JUMP_BOOSE_RADIUS,
                2,
                ParticleSpawnMode.EQUILIBRIUM_TRIANGLE_CONTINUOUS,
                false
        );
    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(BirchiaEntityRenderState state) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.fillEquilibriumTriangle(
                mc,
                RANDOM_PULSE,
                MtsParticleTypes.BIRCHIA_JUMPING_BOOST_PARTICLE,
                state.entityUUID,
                "birchia_jumping_boost_effect_particle",
                new Timer(40),
                state,
                0.0,
                BirchiaEntity.JUMP_BOOSE_RADIUS,
                2,
                ParticleSpawnMode.FILL_EQUILIBRIUM_TRIANGLE_CONTINUOUS,
                false
        );
    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(BirchiaEntityRenderState state) {
        return MtsEntityTextures.BIRCHIA;
    }

    @Override
    public BirchiaEntityRenderState createRenderState() {
        return new BirchiaEntityRenderState();
    }
}
