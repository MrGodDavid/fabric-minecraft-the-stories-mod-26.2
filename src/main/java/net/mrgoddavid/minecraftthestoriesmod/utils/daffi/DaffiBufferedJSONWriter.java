package net.mrgoddavid.minecraftthestoriesmod.utils.daffi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.directory.Directory;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public final class DaffiBufferedJSONWriter {

    public static void main(String[] args) {
        writeSGACharacterJSON();
    }

    public static void writeSGACharacterJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(SGACharacters.CHARACTERS_BY_ID);

        Directory outputDirectory = Directory.builder().src().main_dir().resources().assets().minecraft_the_stories_mod().textures().daffi().build();
        String filename = "sga_characters.json";

        Path path = Paths.get(outputDirectory.toString(), filename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(json);

            System.out.println("Successfully wrote to " + path.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
