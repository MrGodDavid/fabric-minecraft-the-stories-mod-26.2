package net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.image;

import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

/**
 * @author Mr. GodDavid
 * @since 10/14/2026
 */
public final class DaveImageCropperApp {

    public static void main(final String[] args) {

        ImageCropperContext particleAtlasCropperContext = ImageCropperContext.builder()
                .inputDirectory(Constants.Paths.PATH_TO_PARTICLE_ATLAS)
                .outputDirectory(Constants.Paths.PATH_TO_PARTICLE_FRAMES)
                .fileName("acaciania_swiftness")
                .build();
        AtlasCropper.crop(particleAtlasCropperContext);
    }
}
