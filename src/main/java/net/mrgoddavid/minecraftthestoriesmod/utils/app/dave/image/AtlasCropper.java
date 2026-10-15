package net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.image;

import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Mr. GodDavid
 * @since 10/14/2026
 */
public final class AtlasCropper {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    static void crop(ImageCropperContext context) {
        try {
            File targetedFile = new File(context.getDirectory().toString().concat(context.getFileName()).concat("_atlas.png"));
            BufferedImage atlasImage = ImageIO.read(targetedFile);
            int atlasWidth = atlasImage.getWidth();
            int atlasHeight = atlasImage.getHeight();
            if (atlasWidth > WIDTH) {
                throw new RuntimeException("Atlas image: " + targetedFile.getAbsolutePath() + " is too big! The width, "
                        + atlasWidth + "exceeds the limit width: 8px!");
            }
            if (atlasHeight < HEIGHT) {
                throw new RuntimeException("Atlas image: " + targetedFile.getAbsolutePath() + " is too small! The height, " +
                        +atlasHeight + "does not exceed the minimum height: 8px!");
            }

            int numOfImage = atlasHeight / HEIGHT;
            final int x = 0;
            int y = 0;
            for (int i = 0; i < numOfImage; i++) {
                BufferedImage croppedImage = atlasImage.getSubimage(x, y, WIDTH, HEIGHT);
                BufferedImage paintedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = paintedImage.createGraphics();
                g2d.drawImage(croppedImage, 0, 0, null);
                g2d.dispose();
                File paintedFile = new File(context.getOutputDirectory().toString()
                        .concat(context.getFileName()).concat("_")
                        .concat(String.valueOf(i).concat(".png")));
                ImageIO.write(paintedImage, "png", paintedFile);
                y += HEIGHT;
            }

        } catch (Exception e) {
            MtsLogger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private AtlasCropper() {
    }
}
