package net.mrgoddavid.minecraftthestoriesmod.utils.math;

import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

/**
 * @author Mr. GodDavid
 * @since 9/27/2026
 */
public final class MathUtils {

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
}
