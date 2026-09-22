package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.progress;

import net.minecraft.resources.Identifier;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public class StoryBookProgress {

    private final Set<Identifier> seenEntities = new HashSet<>();
    private final Set<Identifier> interactedEntities = new HashSet<>();

    public void seenEntity(Identifier entity) {
        this.seenEntities.add(entity);
    }

    public boolean hasSeenEntity(Identifier entity) {
        return this.seenEntities.contains(entity);
    }

    public void interactedEntity(Identifier entity) {
        this.interactedEntities.add(entity);
    }

    public boolean hasInteractedEntity(Identifier entity) {
        return this.interactedEntities.contains(entity);
    }

    public Set<Identifier> getSeenEntities() {
        return this.seenEntities;
    }

    public Set<Identifier> getInteractedEntities() {
        return this.interactedEntities;
    }
}
