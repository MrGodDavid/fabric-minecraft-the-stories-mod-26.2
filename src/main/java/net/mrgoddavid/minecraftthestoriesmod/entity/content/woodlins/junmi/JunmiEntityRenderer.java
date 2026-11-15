package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie.DenseOakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie.DenseOakieEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie.DenseOakieEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class JunmiEntityRenderer extends AbstractWoodlinEntityRenderer<JunmiEntity, JunmiEntityRenderState, JunmiEntityModel> {

    public JunmiEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new JunmiEntityModel(context.bakeLayer(MtsEntityModelLayers.JUNMI)), 0.2f);
    }

    @Override
    protected void spawnEffectBorderParticles(JunmiEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(JunmiEntityRenderState state) {

    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(JunmiEntityRenderState state) {
        return MtsEntityTextures.JUNMI;
    }

    @Override
    public JunmiEntityRenderState createRenderState() {
        return new JunmiEntityRenderState();
    }
}
