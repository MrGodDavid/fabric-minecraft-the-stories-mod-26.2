package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie.LightOakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie.LightOakieEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie.LightOakieEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class MangrolinoEntityRenderer extends AbstractWoodlinEntityRenderer<MangrolinoEntity, MangrolinoEntityRenderState, MangrolinoEntityModel> {

    public MangrolinoEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new MangrolinoEntityModel(context.bakeLayer(MtsEntityModelLayers.MANGROLINO)), 0.2f);
    }

    @Override
    protected SimpleParticleType getWoodlinNormalParticleType() {
        return MtsParticleTypes.YELLOW_CLOUD_PARTICLE;
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
    protected void spawnEffectBorderParticles(MangrolinoEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(MangrolinoEntityRenderState state) {

    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(MangrolinoEntityRenderState state) {
        return MtsEntityTextures.MANGROLINO;
    }

    @Override
    public MangrolinoEntityRenderState createRenderState() {
        return new MangrolinoEntityRenderState();
    }
}
