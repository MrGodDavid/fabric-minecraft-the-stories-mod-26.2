package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.page;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

/**
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public record StoryBookPage(Identifier id, Identifier texture, Predicate<Player> unlockCriterion) {

    public boolean isUnlocked(Player player) {
        return unlockCriterion.test(player);
    }
}
