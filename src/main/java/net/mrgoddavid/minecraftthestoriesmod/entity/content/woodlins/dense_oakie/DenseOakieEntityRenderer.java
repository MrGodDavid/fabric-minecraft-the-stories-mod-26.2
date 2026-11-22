package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class DenseOakieEntityRenderer extends AbstractWoodlinEntityRenderer<DenseOakieEntity, DenseOakieEntityRenderState, DenseOakieEntityModel> {

    public DenseOakieEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new DenseOakieEntityModel(context.bakeLayer(MtsEntityModelLayers.DENSE_OAKIE)), 0.2f);
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
    protected void spawnEffectBorderParticles(DenseOakieEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(DenseOakieEntityRenderState state) {

    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(DenseOakieEntityRenderState state) {
        return MtsEntityTextures.DENSE_OAKIE;
    }

    @Override
    public DenseOakieEntityRenderState createRenderState() {
        return new DenseOakieEntityRenderState();
    }
}
