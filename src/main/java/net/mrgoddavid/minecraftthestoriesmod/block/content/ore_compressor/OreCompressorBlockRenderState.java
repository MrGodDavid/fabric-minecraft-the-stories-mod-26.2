package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

/**
 * @author Mr. GodDavid
 * @since 8/28/2026
 */
public class OreCompressorBlockRenderState extends BlockEntityRenderState {

    public boolean isCompressing;
    public float animationTime;
    public float angleCorrection;

    public OreCompressorBlock.FreewheelModelTranslationCorrection translationCorrection;
}
