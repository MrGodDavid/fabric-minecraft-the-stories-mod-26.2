package net.mrgoddavid.minecraftthestoriesmod.client.particle.content;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
public class JunmiHydrationParticle extends AbstractWoodlinParticle {

    public JunmiHydrationParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites) {
        super(level, x, y, z, xa, ya, za, sprites);
    }

    /**
     * @author Mr. GodDavid
     * @since 10/14/2026
     */
    public static final class Provider extends AbstractProvider<JunmiHydrationParticle> {

        public Provider(SpriteSet sprites) {
            super(sprites);
        }

        @Override
        protected JunmiHydrationParticle create(ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux) {
            return new JunmiHydrationParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
        }
    }
}
