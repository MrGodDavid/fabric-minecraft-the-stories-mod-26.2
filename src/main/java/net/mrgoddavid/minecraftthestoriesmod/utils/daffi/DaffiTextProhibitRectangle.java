package net.mrgoddavid.minecraftthestoriesmod.utils.daffi;

import java.awt.*;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public final class DaffiTextProhibitRectangle extends Rectangle {

    public DaffiTextProhibitRectangle() {
        super();
    }

    public DaffiTextProhibitRectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isInRegion(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
}
