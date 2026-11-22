package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi.JunmiEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi.JunmiEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi.JunmiEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class LightOakieEntityRenderer extends AbstractWoodlinEntityRenderer<LightOakieEntity, LightOakieEntityRenderState, LightOakieEntityModel> {

    public LightOakieEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new LightOakieEntityModel(context.bakeLayer(MtsEntityModelLayers.LIGHT_OAKIE)), 0.2f);
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
    protected void spawnEffectBorderParticles(LightOakieEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(LightOakieEntityRenderState state) {

    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(LightOakieEntityRenderState state) {
        return MtsEntityTextures.LIGHT_OAKIE;
    }

    @Override
    public LightOakieEntityRenderState createRenderState() {
        return new LightOakieEntityRenderState();
    }
}
