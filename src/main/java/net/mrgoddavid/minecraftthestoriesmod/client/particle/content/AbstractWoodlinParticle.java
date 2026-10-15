package net.mrgoddavid.minecraftthestoriesmod.client.particle.content;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 10/13/2026
 */
@SuppressWarnings("NullableProblems")
public abstract class AbstractWoodlinParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    protected AbstractWoodlinParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites) {
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
            this.yd = (this.yd + 0.003) * 0.96;
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
    public static abstract class AbstractProvider<P extends AbstractWoodlinParticle> implements ParticleProvider<SimpleParticleType> {

        protected final SpriteSet sprites;

        public AbstractProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        protected abstract P create(ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux);

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            return this.create(level, x, y, z, xAux, yAux, zAux);
        }
    }
}
