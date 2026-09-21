package net.mrgoddavid.minecraftthestoriesmod.gui.sprites;

import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public final class MtsWidgetTextures {

    public static final Identifier STORY_BOOK_PAGE_FORWARD = getTexture("story_book_page_forward");
    public static final Identifier STORY_BOOK_PAGE_BACKWARD = getTexture("story_book_page_backward");
    public static final Identifier STORY_BOOK_PAGE_FORWARD_HIGHLIGHTED = getTexture("story_book_page_forward_highlighted");
    public static final Identifier STORY_BOOK_PAGE_BACKWARD_HIGHLIGHTED = getTexture("story_book_page_backward_highlighted");

    public static void register() {
        MtsLogger.info("Custom Widget Textures");
    }

    private static Identifier getTexture(String name) {
        return Constants.modId(withPath(name));
    }

    private static String withPath(String name) {
        return "textures/gui/sprites/widget/" + name + ".png";
    }

    private MtsWidgetTextures() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
