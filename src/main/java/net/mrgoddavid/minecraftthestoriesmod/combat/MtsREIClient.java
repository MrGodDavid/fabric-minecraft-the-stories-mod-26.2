package net.mrgoddavid.minecraftthestoriesmod.combat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherScreen;
import net.mrgoddavid.minecraftthestoriesmod.combat.content.enricher.EnricherCategory;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public class MtsREIClient implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new EnricherCategory());

        registry.addWorkstations(MtsREICommon.ENRICHER, EntryStacks.of(MtsBlocks.ENRICHER));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                ((screen.height - 166) / 2) + 30, 20, 25), EnricherScreen.class, MtsREICommon.ENRICHER);
    }
}
