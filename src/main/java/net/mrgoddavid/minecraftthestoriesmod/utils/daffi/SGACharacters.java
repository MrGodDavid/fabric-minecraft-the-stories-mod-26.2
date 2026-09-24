package net.mrgoddavid.minecraftthestoriesmod.utils.daffi;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public final class SGACharacters {

    private static final List<SGACharacter> CHARACTERS = List.of(
            new SGACharacter(new Rectangle(0, 0, 3, 5)), // 0
            new SGACharacter(new Rectangle(4, 0, 3, 5)), // 1
            new SGACharacter(new Rectangle(8, 0, 3, 5)), // 2
            new SGACharacter(new Rectangle(12, 0, 3, 5)), // 3
            new SGACharacter(new Rectangle(16, 0, 3, 5)), // 4
            new SGACharacter(new Rectangle(20, 0, 3, 5)), // 5
            new SGACharacter(new Rectangle(24, 0, 3, 5)), // 6
            new SGACharacter(new Rectangle(28, 0, 3, 5)), // 7
            new SGACharacter(new Rectangle(32, 0, 3, 5)), // 8
            new SGACharacter(new Rectangle(36, 0, 3, 5)), // 9
            new SGACharacter(new Rectangle(0, 6, 3, 5)), // A
            new SGACharacter(new Rectangle(4, 6, 3, 5)), // B
            new SGACharacter(new Rectangle(8, 6, 3, 5)), // C
            new SGACharacter(new Rectangle(12, 6, 3, 5)), // D
            new SGACharacter(new Rectangle(16, 6, 3, 5)), // E
            new SGACharacter(new Rectangle(20, 6, 3, 5)), // F
            new SGACharacter(new Rectangle(24, 6, 3, 5)), // G
            new SGACharacter(new Rectangle(28, 6, 3, 5)), // H
            new SGACharacter(new Rectangle(32, 6, 3, 5)), // I
            new SGACharacter(new Rectangle(36, 6, 3, 5)), // J
            new SGACharacter(new Rectangle(40, 6, 3, 5)), // K
            new SGACharacter(new Rectangle(44, 6, 3, 5)), // L
            new SGACharacter(new Rectangle(48, 6, 5, 5)), // M
            new SGACharacter(new Rectangle(54, 6, 4, 5)), // N
            new SGACharacter(new Rectangle(59, 6, 3, 5)), // O
            new SGACharacter(new Rectangle(63, 6, 3, 5)), // P
            new SGACharacter(new Rectangle(67, 6, 4, 5)), // Q
            new SGACharacter(new Rectangle(0, 12, 3, 5)), // R
            new SGACharacter(new Rectangle(4, 12, 3, 5)), // S
            new SGACharacter(new Rectangle(8, 12, 3, 5)), // T
            new SGACharacter(new Rectangle(12, 12, 3, 5)), // U
            new SGACharacter(new Rectangle(16, 12, 3, 5)), // V
            new SGACharacter(new Rectangle(20, 12, 5, 5)), // W
            new SGACharacter(new Rectangle(26, 12, 3, 5)), // X
            new SGACharacter(new Rectangle(30, 12, 3, 5)), // Y
            new SGACharacter(new Rectangle(34, 12, 3, 5)), // Z
            new SGACharacter(new Rectangle(0, 19, 3, 5)), // SGA - A
            new SGACharacter(new Rectangle(4, 19, 3, 5)), // SGA - B
            new SGACharacter(new Rectangle(8, 19, 3, 5)), // SGA - C
            new SGACharacter(new Rectangle(12, 19, 3, 5)), // SGA - D
            new SGACharacter(new Rectangle(16, 19, 3, 5)), // SGA - E
            new SGACharacter(new Rectangle(20, 19, 5, 5)), // SGA - F
            new SGACharacter(new Rectangle(26, 19, 3, 5)), // SGA - G
            new SGACharacter(new Rectangle(30, 19, 3, 5)), // SGA - H
            new SGACharacter(new Rectangle(34, 19, 3, 5)), // SGA - I
            new SGACharacter(new Rectangle(39, 19, 3, 5)), // SGA - J
            new SGACharacter(new Rectangle(43, 19, 3, 5)), // SGA - K
            new SGACharacter(new Rectangle(47, 19, 3, 5)), // SGA - L
            new SGACharacter(new Rectangle(52, 19, 3, 5)), // SGA - M
            new SGACharacter(new Rectangle(56, 19, 3, 5)), // SGA - N
            new SGACharacter(new Rectangle(60, 19, 3, 5)), // SGA - O
            new SGACharacter(new Rectangle(64, 19, 3, 5)), // SGA - P
            new SGACharacter(new Rectangle(68, 19, 3, 5)), // SGA - Q
            new SGACharacter(new Rectangle(0, 25, 3, 5)), // SGA - R
            new SGACharacter(new Rectangle(4, 25, 3, 5)), // SGA - S
            new SGACharacter(new Rectangle(8, 25, 3, 5)), // SGA - T
            new SGACharacter(new Rectangle(12, 25, 3, 5)), // SGA - U
            new SGACharacter(new Rectangle(16, 25, 3, 5)), // SGA - V
            new SGACharacter(new Rectangle(20, 25, 3, 5)), // SGA - W
            new SGACharacter(new Rectangle(24, 25, 3, 5)), // SGA - X
            new SGACharacter(new Rectangle(28, 25, 3, 5)), // SGA - Y
            new SGACharacter(new Rectangle(32, 25, 3, 5)) // SGA - Z
    );

    public static final Map<Integer, SGACharacter> CHARACTERS_BY_ID;

    static {
        CHARACTERS_BY_ID = new HashMap<>();
        for (int index = 0; index < SGACharacters.CHARACTERS.size(); index++) {
            CHARACTERS_BY_ID.put(index, CHARACTERS.get(index));
        }
    }

    private SGACharacters() {
    }
}
