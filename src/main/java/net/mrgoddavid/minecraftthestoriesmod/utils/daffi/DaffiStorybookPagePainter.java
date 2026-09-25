package net.mrgoddavid.minecraftthestoriesmod.utils.daffi;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.directory.Directory;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public final class DaffiStorybookPagePainter {

    static final Directory EXPECTED_SGA_CHARACTER_JSON_FILE_DIR = Directory.builder().src().main_dir().resources().assets().minecraft_the_stories_mod().textures().daffi().build();

    public static void main(String[] args) {
        if (!sgaCharacterJSONFileExisted()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type mapType = new TypeToken<Map<Integer, SGACharacter>>() {
            }.getType();

            try (BufferedReader reader = new BufferedReader(new FileReader(EXPECTED_SGA_CHARACTER_JSON_FILE_DIR.toString().concat("sga_characters.json")))) {
                Map<Integer, SGACharacter> sgaCharacters = gson.fromJson(reader, mapType);
                paintStoryPage(sgaCharacters);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause() + " | " + e.getMessage());
            }
        }
    }

    private static void paintStoryPage(Map<Integer, SGACharacter> sgaCharacters) {
        DaffiStoryBookPageContent content = new DaffiStoryBookPageContent(sgaCharacters);
        content.addAllProhibitedAreas(
                new DaffiTextProhibitRectangle(5, 5, 50, 50)
        );
        content.paint(1, "Hello World My Name is David 1 1111 11 11111 1111 1111 11 1111 11 111 11 111");
    }

    private static boolean sgaCharacterJSONFileExisted() {
        String filepath = EXPECTED_SGA_CHARACTER_JSON_FILE_DIR.toString().concat("sga_characters.json");
        Path path = Paths.get(filepath);
        return Files.exists(path) && Files.isRegularFile(path);
    }
}
