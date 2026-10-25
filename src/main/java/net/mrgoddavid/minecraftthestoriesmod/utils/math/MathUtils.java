package net.mrgoddavid.minecraftthestoriesmod.utils.math;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import org.joml.Vector3f;

import javax.swing.text.html.parser.Entity;

/**
 * @author Mr. GodDavid
 * @since 9/27/2026
 */
public final class MathUtils {

    private MathUtils() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    public static Vec3 slerp(Vector3f a, Vector3f b, float t) {
        Vector3f aHat = new Vector3f(a).normalize();
        Vector3f bHat = new Vector3f(b).normalize();
        float dot = Math.clamp(aHat.dot(bHat), -1.0F, 1.0F);
        float theta = (float) Math.acos(dot);
        if (theta < 1.0E-6F) {
            return new Vec3(new Vector3f(aHat).lerp(bHat, t).normalize());
        }
        float sinTheta = (float) Math.sin(theta);
        float c = (float) (Math.sin((1.0F - t) * theta) / sinTheta);
        float d = (float) (Math.sin(t * theta) / sinTheta);
        Vector3f v = new Vector3f(aHat).mul(c).add(new Vector3f(bHat).mul(d)).normalize();
        return new Vec3(v);
    }

    public static int argbToColor(int a, int r, int g, int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }

    public static double distanceSqBetweenMobAndBlockPos(LivingEntity mob, BlockPos pos) {
        double x = mob.getX() - pos.getX();
        double y = mob.getY() - pos.getY();
        double z = mob.getZ() - pos.getZ();
        return x * x + y * y + z * z;
    }

    public static float linearInterpolation(float x, float originalMin, float originalMax, float targetMin, float targetMax) {
        float progress = x - originalMin;
        float targetDifference = targetMax - targetMin;
        float originalDifference = originalMax - originalMin;
        return targetMin + (progress * targetDifference) / originalDifference;
    }
}
