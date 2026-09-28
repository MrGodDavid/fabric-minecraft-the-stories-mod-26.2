package net.mrgoddavid.minecraftthestoriesmod.utils.math;

import org.joml.Quaternionf;

/**
 * Quaternion in Blender 3D software.
 *
 * @author Mr. GodDavid
 * @since 9/27/2026
 */
public class BlenderQuaternionf {

    private BlenderQuaternionf() {
    }

    public static Quaternionf toMinecraft(float w, float x, float y, float z) {
        Quaternionf blender = new Quaternionf(x, y, z, w).normalize();
        return convertBasis(blender);
    }

    public static Quaternionf relativeQuaternion(
            float restW, float restX, float restY, float restZ,
            float absoluteW, float absoluteX, float absoluteY, float absoluteZ
    ) {
        Quaternionf rest = toMinecraft(restW, restX, restY, restZ).normalize();
        Quaternionf absolute = toMinecraft(absoluteW, absoluteX, absoluteY, absoluteZ).normalize();
        return new Quaternionf(rest).invert().mul(absolute).normalize();
    }

    public static Quaternionf boneLocalToBlenderWorld(float w, float x, float y, float z) {
        Quaternionf boneLocal = new Quaternionf(x, y, z, w).normalize();
        return convertBoneBasis(boneLocal);
    }

    public static Quaternionf boneLocalToMinecraft(float w, float x, float y, float z) {
        Quaternionf blenderWorld = boneLocalToBlenderWorld(w, x, y, z);
        return convertBasis(blenderWorld);
    }

    private static Quaternionf convertBasis(Quaternionf q) {
        float x = q.x();
        float y = q.y();
        float z = q.z();
        float w = q.w();
        float xx = x * x;
        float yy = y * y;
        float zz = z * z;
        float xy = x * y;
        float xz = x * z;
        float yz = y * z;
        float wx = w * x;
        float wy = w * y;
        float wz = w * z;
        float m00 = 1.0F - 2.0F * yy - 2.0F * zz;
        float m01 = 2.0F * xy - 2.0F * wz;
        float m02 = 2.0F * xz + 2.0F * wy;
        float m10 = 2.0F * xy + 2.0F * wz;
        float m11 = 1.0F - 2.0F * xx - 2.0F * zz;
        float m12 = 2.0F * yz - 2.0F * wx;
        float m20 = 2.0F * xz - 2.0F * wy;
        float m21 = 2.0F * yz + 2.0F * wx;
        float m22 = 1.0F - 2.0F * xx - 2.0F * yy;
        float r00 = m00;
        float r01 = m02;
        float r02 = -m01;
        float r10 = m20;
        float r11 = m22;
        float r12 = -m21;
        float r20 = -m10;
        float r21 = -m12;
        float r22 = m11;
        return fromRotationMatrix(
                r00, r01, r02,
                r10, r11, r12,
                r20, r21, r22
        ).normalize();
    }

    private static Quaternionf convertBoneBasis(Quaternionf q) {
        float x = q.x();
        float y = q.y();
        float z = q.z();
        float w = q.w();
        float xx = x * x;
        float yy = y * y;
        float zz = z * z;
        float xy = x * y;
        float xz = x * z;
        float yz = y * z;
        float wx = w * x;
        float wy = w * y;
        float wz = w * z;
        float m00 = 1.0F - 2.0F * yy - 2.0F * zz;
        float m01 = 2.0F * xy - 2.0F * wz;
        float m02 = 2.0F * xz + 2.0F * wy;
        float m10 = 2.0F * xy + 2.0F * wz;
        float m11 = 1.0F - 2.0F * xx - 2.0F * zz;
        float m12 = 2.0F * yz - 2.0F * wx;
        float m20 = 2.0F * xz - 2.0F * wy;
        float m21 = 2.0F * yz + 2.0F * wx;
        float m22 = 1.0F - 2.0F * xx - 2.0F * yy;
        float r00 = m00;
        float r01 = m02;
        float r02 = -m01;
        float r10 = m20;
        float r11 = m22;
        float r12 = -m21;
        float r20 = -m10;
        float r21 = -m12;
        float r22 = m11;
        return fromRotationMatrix(
                r00, r01, r02,
                r10, r11, r12,
                r20, r21, r22
        ).normalize();
    }

    private static Quaternionf fromRotationMatrix(
            float m00, float m01, float m02,
            float m10, float m11, float m12,
            float m20, float m21, float m22
    ) {
        float trace = m00 + m11 + m22;
        if (trace > 0.0F) {
            float s = (float) Math.sqrt(trace + 1.0F) * 2.0F;
            return new Quaternionf(
                    (m21 - m12) / s,
                    (m02 - m20) / s,
                    (m10 - m01) / s,
                    0.25F * s
            );
        }
        if (m00 > m11 && m00 > m22) {
            float s = (float) Math.sqrt(1.0F + m00 - m11 - m22) * 2.0F;
            return new Quaternionf(
                    0.25F * s,
                    (m01 + m10) / s,
                    (m02 + m20) / s,
                    (m21 - m12) / s
            );
        }
        if (m11 > m22) {
            float s = (float) Math.sqrt(1.0F + m11 - m00 - m22) * 2.0F;
            return new Quaternionf(
                    (m01 + m10) / s,
                    0.25F * s,
                    (m12 + m21) / s,
                    (m02 - m20) / s
            );
        }
        float s = (float) Math.sqrt(1.0F + m22 - m00 - m11) * 2.0F;
        return new Quaternionf(
                (m02 + m20) / s,
                (m12 + m21) / s,
                0.25F * s,
                (m10 - m01) / s
        );
    }
}
