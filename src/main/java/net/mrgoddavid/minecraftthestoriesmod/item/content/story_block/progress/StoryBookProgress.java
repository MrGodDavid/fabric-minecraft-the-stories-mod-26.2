package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.progress;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public class StoryBookProgress {

    private static final String SEEN_ENTITIES_KEY = "seen_entities";
    private static final String INTERACTED_ENTITIES_KEY = "interacted_entities";

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

    public void save(ValueOutput output) {
        output.store(SEEN_ENTITIES_KEY, Identifier.CODEC.listOf(), this.seenEntities.stream().toList());
        output.store(INTERACTED_ENTITIES_KEY, Identifier.CODEC.listOf(), this.interactedEntities.stream().toList());
    }

    public void load(ValueInput input) {
        input.read(SEEN_ENTITIES_KEY, Identifier.CODEC.listOf()).ifPresent(list -> {
            this.seenEntities.clear();
            this.seenEntities.addAll(list);
        });
        input.read(INTERACTED_ENTITIES_KEY, Identifier.CODEC.listOf()).ifPresent(list -> {
            this.interactedEntities.clear();
            this.interactedEntities.addAll(list);
        });
    }
}
