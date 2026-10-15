package net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.image;

import net.mrgoddavid.minecraftthestoriesmod.utils.directory.Directory;

/**
 * @author Mr. GodDavid
 * @since 10/14/2026
 */
public final class ImageCropperContext {

    private final Directory directory;
    private final Directory outputDirectory;
    private final String fileName;

    public ImageCropperContext(Builder builder) {
        this.directory = builder.directory;
        this.outputDirectory = builder.outputDirectory;
        this.fileName = builder.fileName;
    }

    public Directory getDirectory() {
        return directory;
    }
    public Directory getOutputDirectory() {
        return outputDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @author Mr. GodDavid
     * @since 10/14/2026
     */
    public static final class Builder {

        private Directory directory;
        private Directory outputDirectory;
        private String fileName;

        public Builder() {
            this.directory = null;
            this.outputDirectory = null;
            this.fileName = "NULL";
        }

        public ImageCropperContext build() {
            return new ImageCropperContext(this);
        }

        public Builder inputDirectory(Directory directory) {
            this.directory = directory;
            return this;
        }

        public Builder outputDirectory(Directory outputDirectory) {
            this.outputDirectory = outputDirectory;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }
    }
}
