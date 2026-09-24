package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.page;

import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.progress.StoryBookProgress;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.progress.StoryBookProgressHolder;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.directory.Directory;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public class StoryBookPages {

    private static final String basePath = Directory.builder().textures().item().storybook_pages().build().toString() + "storybook_pages_";

    private static final List<StoryBookPage> PAGES = List.of(
            new StoryBookPage(
                    Constants.modId("introduction_page"),
                    Constants.modId(basePath + "1"),
//                    player -> getProgress(player).hasInteractedEntity(Constants.modId("brown_bear"))
                    player -> true
            )
    );

    private StoryBookPages() {
    }

    public static List<StoryBookPage> getUnlockedPages(Player player) {
        return PAGES.stream().filter(page -> page.isUnlocked(player)).toList();
    }

    public static StoryBookProgress getProgress(Player player) {
        return ((StoryBookProgressHolder) player).mts$getStoryBookProgress();
    }
}
