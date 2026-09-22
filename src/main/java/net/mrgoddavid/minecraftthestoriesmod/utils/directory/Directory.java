package net.mrgoddavid.minecraftthestoriesmod.utils.directory;

/**
 * Today I am sick. My brain is dizzy and cooked inside-out. Typing the directory of my textures is a nightmare. So
 * today, I'm going to use the factory design pattern to make the perhaps the most useless class of this project.
 *
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public final class Directory {

    public final String directory;

    private Directory(Directory.Builder builder) {
        this.directory = builder.directory;
    }

    public String directory() {
        return this.directory;
    }

    public static Directory.Builder builder() {
        return new Directory.Builder();
    }

    /**
     * @author Mr. GodDavid
     * @since 9/21/2026
     */
    public static class Builder {

        private String directory;

        public Builder() {
            directory = "";
        }

        public Builder atlases() {
            this.directory = this.directory.concat(FILES.ATLASES.getName());
            return this;
        }

        public Builder lang() {
            this.directory = this.directory.concat(FILES.LANG.getName());
            return this;
        }

        public Builder models() {
            this.directory = this.directory.concat(FILES.MODELS.getName());
            return this;
        }

        public Builder palette() {
            this.directory = this.directory.concat(FILES.PALETTE.getName());
            return this;
        }

        public Builder particles() {
            this.directory = this.directory.concat(FILES.PARTICLES.getName());
            return this;
        }

        public Builder textures() {
            this.directory = this.directory.concat(FILES.TEXTURES.getName());
            return this;
        }

        public Builder block() {
            this.directory = this.directory.concat(FILES.BLOCK.getName());
            return this;
        }

        public Builder item() {
            this.directory = this.directory.concat(FILES.ITEM.getName());
            return this;
        }

        public Builder entity() {
            this.directory = this.directory.concat(FILES.ENTITY.getName());
            return this;
        }

        public Builder gui() {
            this.directory = this.directory.concat(FILES.GUI.getName());
            return this;
        }

        public Builder particle() {
            this.directory = this.directory.concat(FILES.PARTICLE.getName());
            return this;
        }

        public Builder template() {
            this.directory = this.directory.concat(FILES.TEMPLATE.getName());
            return this;
        }

        public Builder icons() {
            this.directory = this.directory.concat(FILES.ICONS.getName());
            return this;
        }

        public Builder menu() {
            this.directory = this.directory.concat(FILES.MENU.getName());
            return this;
        }

        public Builder sprites() {
            this.directory = this.directory.concat(FILES.SPRITES.getName());
            return this;
        }

        public Builder end_exalter() {
            this.directory = directory.concat(FILES.ENDER_EXALTER.getName());
            return this;
        }

        public Builder enricher() {
            this.directory = this.directory.concat(FILES.ENRICHER.getName());
            return this;
        }

        public Builder ore_compressor() {
            this.directory = this.directory.concat(FILES.ORE_COMPRESSOR.getName());
            return this;
        }

        public Builder squeezer() {
            this.directory = this.directory.concat(FILES.SQUEEZER.getName());
            return this;
        }

        public Builder super_crafter() {
            this.directory = this.directory.concat(FILES.SUPER_CRAFTER.getName());
            return this;
        }

        public Builder hud() {
            this.directory = this.directory.concat(FILES.HUD.getName());
            return this;
        }

        public Builder tooltip() {
            this.directory = this.directory.concat(FILES.TOOLTIP.getName());
            return this;
        }

        public Builder widget() {
            this.directory = this.directory.concat(FILES.WIDGET.getName());
            return this;
        }

        public Directory build() {
            return new Directory(this);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 9/21/2026
     */
    private enum FILES {
        ATLASES,
        LANG,
        MODELS,
        PALETTE,
        PARTICLES,
        TEXTURES,
        BLOCK,
        ITEM,
        ENTITY,
        GUI,
        PARTICLE,
        TEMPLATE,
        ICONS,
        MENU,
        SPRITES,
        ENDER_EXALTER,
        ENRICHER,
        ORE_COMPRESSOR,
        SQUEEZER,
        SUPER_CRAFTER,
        HUD,
        TOOLTIP,
        WIDGET;

        String getName() {
            return this.name().toLowerCase().concat("/");
        }
    }
}
