package net.mrgoddavid.minecraftthestoriesmod.particle.content;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;

/**
 * I copied most of the behavior code of this particle from {@link net.minecraft.client.particle.PlayerCloudParticle}.
 * I feel like it's stupid to use a Mixin of {@link net.minecraft.client.particle.PlayerCloudParticle} so that's why I
 * didn't use it.
 *
 * @author Mr. GodDavid
 * @since 10/8/2026
 */
public class ColoredPlayerCloudParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    private ColoredPlayerCloudParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites, int colorRGBA) {
        super(level, x, y, z, 0.0, 0.0, 0.0, sprites.first());
        this.friction = 0.96F;
        this.sprites = sprites;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.xd += xa;
        this.yd += ya;
        this.zd += za;

        this.rCol = ((colorRGBA >> 24) & 0xFF) / 255.0F;
        this.gCol = ((colorRGBA >> 16) & 0xFF) / 255.0F;
        this.bCol = ((colorRGBA >> 8) & 0xFF) / 255.0F;
        this.alpha = (colorRGBA & 0xFF) / 255.0F; // this was broken. I had this.alpha = (colorRGBA) / 255.0F; before.

        this.quadSize *= 1.875F;
        int baseLifetime = (int) (8.0 / (this.random.nextFloat() * 0.8 + 0.3));
        this.lifetime = (int) Math.max(baseLifetime * 2.5F, 1.0F);
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    protected @NonNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    public float getQuadSize(final float a) {
        return this.quadSize * Mth.clamp((this.age + a) / this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSpriteFromAge(this.sprites);
            Player player = this.level.getNearestPlayer(this.x, this.y, this.z, 2.0, false);
            if (player != null) {
                double playerY = player.getY();
                if (this.y > playerY) {
                    this.y = this.y + (playerY - this.y) * 0.2;
                    this.yd = this.yd + (player.getDeltaMovement().y - this.yd) * 0.2;
                    this.setPos(this.x, this.y, this.z);
                }
            }
        }
    }

    /**
     * Same for this one. I copied most of the code from the class
     * {@link net.minecraft.client.particle.PlayerCloudParticle.Provider}.
     *
     * @author Mr. GodDavid
     * @since 10/8/2026
     */
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;
        private int colorRGBA;

        public Provider(final SpriteSet sprites) {
            this.sprites = sprites;
            this.colorRGBA = 0xFFFFFFFF;
        }

        public Provider color(int r, int g, int b, int a) {
            if ((r | g | b | a) < 0 || (r > 255 || g > 255 || b > 255 || a > 255)) {
                throw new IllegalArgumentException("Color RGBA value must be between 0 and 255!");
            }
            this.colorRGBA = r << 24 | g << 16 | b << 8 | a;
            return this;
        }

        @Override
        public Particle createParticle(
                final SimpleParticleType options,
                final @NonNull ClientLevel level,
                final double x,
                final double y,
                final double z,
                final double xAux,
                final double yAux,
                final double zAux,
                final @NonNull RandomSource random
        ) {
            return new ColoredPlayerCloudParticle(level, x, y, z, xAux, yAux, zAux, this.sprites, this.colorRGBA);
        }
    }
}
