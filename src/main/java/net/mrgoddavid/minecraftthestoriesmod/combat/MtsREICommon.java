package net.mrgoddavid.minecraftthestoriesmod.combat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.combat.content.enricher.EnricherDisplay;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.enricher.EnricherRecipe;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public class MtsREICommon implements REICommonPlugin {

    public static final CategoryIdentifier<EnricherDisplay> ENRICHER = CategoryIdentifier.of(MinecraftTheStoriesMod.MOD_ID, "enricher");

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(ENRICHER.getIdentifier(), EnricherDisplay.SERIALIZER);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(EnricherRecipe.class).fill(EnricherDisplay::new);
    }
}
