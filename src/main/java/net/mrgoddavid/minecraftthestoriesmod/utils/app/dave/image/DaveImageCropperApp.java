package net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.image;

import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

/**
 * @author Mr. GodDavid
 * @since 10/14/2026
 */
public final class DaveImageCropperApp {

    private static final String[] FILE_NAMES = new String[] {
            "acaciania_swiftness",
            "birchia_jumping_boost",
            "cherrinia_enticement",
            "dense_oakie_strength",
            "junmi_hydration",
            "light_oakie_invisibility",
            "mangrolino_swimmer_boost",
            "sprucia_attack_speed"
    };

    public static void main(final String[] args) {

        for (String fileName : FILE_NAMES) {
            ImageCropperContext particleAtlasCropperContext = ImageCropperContext.builder()
                    .inputDirectory(Constants.Paths.PATH_TO_PARTICLE_ATLAS)
                    .outputDirectory(Constants.Paths.PATH_TO_PARTICLE_FRAMES)
                    .fileName(fileName)
                    .build();
            AtlasCropper.crop(particleAtlasCropperContext);
        }
    }
}
