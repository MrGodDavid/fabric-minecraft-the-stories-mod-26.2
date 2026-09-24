package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.page;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

/**
 * @param id              a unique identifier of this story book page.
 * @param texture         the identifier that points to the texture of this page of story book.
 * @param unlockCriterion criteria for unlocking this page in story book.
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public record StoryBookPage(Identifier id, Identifier texture, Predicate<Player> unlockCriterion) {

    public boolean isUnlocked(Player player) {
        return unlockCriterion.test(player);
    }
}
