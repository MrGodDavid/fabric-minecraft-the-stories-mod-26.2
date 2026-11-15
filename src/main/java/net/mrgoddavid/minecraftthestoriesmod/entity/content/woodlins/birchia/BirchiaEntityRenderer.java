package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
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
    protected void spawnEffectBorderParticles(BirchiaEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(BirchiaEntityRenderState state) {

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
