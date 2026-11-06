package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEyesLayer;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsRenderTypes;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 10/18/2026
 */
public class OakieEyesLayer extends AbstractWoodlinEyesLayer<OakieEntityRenderState, OakieEntityModel> {

    public OakieEyesLayer(RenderLayerParent<OakieEntityRenderState, OakieEntityModel> renderer) {
        super(renderer);
    }

    @Override
    public @NonNull RenderType renderType() {
        return MtsRenderTypes.OAKIE_EYES;
    }
}
