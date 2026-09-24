package net.mrgoddavid.minecraftthestoriesmod.utils.daffi;

import java.awt.*;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public record SGACharacter(Rectangle bounds) {

    public static final SGACharacter EMPTY = new SGACharacter(new Rectangle());
    public static final SGACharacter WHITESPACE = new SGACharacter(new Rectangle(40, 0, 1, 5));
}
