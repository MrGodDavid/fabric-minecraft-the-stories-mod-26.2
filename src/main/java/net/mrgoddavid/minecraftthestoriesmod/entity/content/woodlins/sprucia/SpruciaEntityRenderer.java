package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.sprucia;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.entity.MtsEntityTextures;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings({"SpellCheckingInspection"})
public class SpruciaEntityRenderer extends AbstractWoodlinEntityRenderer<SpruciaEntity, SpruciaEntityRenderState, SpruciaEntityModel> {

    public SpruciaEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SpruciaEntityModel(context.bakeLayer(MtsEntityModelLayers.SPRUCIA)), 0.2f);
    }

    @Override
    protected void spawnEffectBorderParticles(SpruciaEntityRenderState state) {

    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(SpruciaEntityRenderState state) {

    }

    @Override
    protected int getEyeGlowColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public @NonNull Identifier getTextureLocation(SpruciaEntityRenderState state) {
        return MtsEntityTextures.SPRUCIA;
    }

    @Override
    public SpruciaEntityRenderState createRenderState() {
        return new SpruciaEntityRenderState();
    }
}
