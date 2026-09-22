package net.mrgoddavid.minecraftthestoriesmod.combat.content.enricher;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.mrgoddavid.minecraftthestoriesmod.combat.MtsREICommon;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.enricher.EnricherRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public record EnricherDisplay(EntryIngredient input, EntryIngredient fuel, EntryIngredient output, Optional<Identifier> location) implements Display {

    public static final DisplaySerializer<EnricherDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().fieldOf("input").forGetter(EnricherDisplay::input),
                    EntryIngredient.codec().fieldOf("fuel").forGetter(EnricherDisplay::fuel),
                    EntryIngredient.codec().fieldOf("output").forGetter(EnricherDisplay::output),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(EnricherDisplay::location)
            ).apply(instance, EnricherDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec(), EnricherDisplay::fuel,
                    EntryIngredient.streamCodec(), EnricherDisplay::input,
                    EntryIngredient.streamCodec(), EnricherDisplay::output,
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC), EnricherDisplay::location,
                    EnricherDisplay::new
            ));

    public EnricherDisplay(RecipeHolder<EnricherRecipe> entry) {
        this(entry.id().identifier(), entry.value());
    }

    public EnricherDisplay(Identifier identifier, EnricherRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.input()), EntryIngredients.ofIngredient(recipe.fuel()), EntryIngredients.of(recipe.output().create()), Optional.of(identifier));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(input, fuel);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MtsREICommon.ENRICHER;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return location;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
