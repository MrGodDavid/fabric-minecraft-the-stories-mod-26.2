package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class CherriniaEntityRenderer extends AbstractWoodlinEntityRenderer<CherriniaEntity, CherriniaEntityRenderState, CherriniaEntityModel> {

    public CherriniaEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new CherriniaEntityModel(context.bakeLayer(MtsEntityModelLayers.CHERRINIA)), 0.2f);
    }

    @Override
    protected void spawnEffectBorderParticles(CherriniaEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(CherriniaEntityRenderState state) {

    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(CherriniaEntityRenderState state) {
        return MtsEntityTextures.CHERRINIA;
    }

    @Override
    public CherriniaEntityRenderState createRenderState() {
        return new CherriniaEntityRenderState();
    }
}
