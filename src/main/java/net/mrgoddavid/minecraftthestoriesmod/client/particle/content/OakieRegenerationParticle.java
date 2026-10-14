package net.mrgoddavid.minecraftthestoriesmod.client.particle.content;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 10/13/2026
 */
@SuppressWarnings("NullableProblems")
public class OakieRegenerationParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    protected OakieRegenerationParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites) {
        super(level, x, y, z, 0.0, 0.0, 0.0, sprites.first());
        this.sprites = sprites;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.xd += xa;
        this.yd += ya;
        this.zd += za;

        this.quadSize *= 1.875F;
        int baseLifetime = (int) (8.0 / (this.random.nextFloat() * 0.8 + 0.3));
        this.lifetime = (int) Math.max(baseLifetime * 2.5F, 1.0F);
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSpriteFromAge(this.sprites);
        }
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    /**
     * @author Mr. GodDavid
     * @since 10/13/2026
     */
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(
                final SimpleParticleType options,
                final ClientLevel level,
                final double x,
                final double y,
                final double z,
                final double xAux,
                final double yAux,
                final double zAux,
                final RandomSource random
        ) {
            return new OakieRegenerationParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
        }
    }
}
