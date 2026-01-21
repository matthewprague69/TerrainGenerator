package util;

public class MatrixUtils {
    public static float[] identity() {
        return new float[] {
                1f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f,
                0f, 0f, 1f, 0f,
                0f, 0f, 0f, 1f
        };
    }

    public static float[] multiply(float[] a, float[] b) {
        float[] result = new float[16];
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                result[col * 4 + row] =
                        a[0 * 4 + row] * b[col * 4 + 0] +
                        a[1 * 4 + row] * b[col * 4 + 1] +
                        a[2 * 4 + row] * b[col * 4 + 2] +
                        a[3 * 4 + row] * b[col * 4 + 3];
            }
        }
        return result;
    }

    public static float[] ortho(float left, float right, float bottom, float top, float near, float far) {
        float[] m = identity();
        m[0] = 2f / (right - left);
        m[5] = 2f / (top - bottom);
        m[10] = -2f / (far - near);
        m[12] = -(right + left) / (right - left);
        m[13] = -(top + bottom) / (top - bottom);
        m[14] = -(far + near) / (far - near);
        return m;
    }

    public static float[] lookAt(float eyeX, float eyeY, float eyeZ,
                                 float centerX, float centerY, float centerZ,
                                 float upX, float upY, float upZ) {
        float fx = centerX - eyeX;
        float fy = centerY - eyeY;
        float fz = centerZ - eyeZ;
        float flen = (float) Math.sqrt(fx * fx + fy * fy + fz * fz);
        fx /= flen;
        fy /= flen;
        fz /= flen;

        float sx = fy * upZ - fz * upY;
        float sy = fz * upX - fx * upZ;
        float sz = fx * upY - fy * upX;
        float slen = (float) Math.sqrt(sx * sx + sy * sy + sz * sz);
        sx /= slen;
        sy /= slen;
        sz /= slen;

        float ux = sy * fz - sz * fy;
        float uy = sz * fx - sx * fz;
        float uz = sx * fy - sy * fx;

        float[] m = identity();
        m[0] = sx;
        m[4] = sy;
        m[8] = sz;

        m[1] = ux;
        m[5] = uy;
        m[9] = uz;

        m[2] = -fx;
        m[6] = -fy;
        m[10] = -fz;

        m[12] = -(sx * eyeX + sy * eyeY + sz * eyeZ);
        m[13] = -(ux * eyeX + uy * eyeY + uz * eyeZ);
        m[14] = fx * eyeX + fy * eyeY + fz * eyeZ;

        return m;
    }
}
