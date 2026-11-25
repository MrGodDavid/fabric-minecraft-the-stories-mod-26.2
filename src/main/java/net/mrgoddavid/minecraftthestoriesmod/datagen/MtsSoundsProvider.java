package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.mrgoddavid.minecraftthestoriesmod.sound.MtsSounds;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * @author Mr. GodDavid
 * @since 9/28/2026
 */
@SuppressWarnings("NullableProblems")
public class MtsSoundsProvider extends FabricSoundsProvider {

    public MtsSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Implement this method and then use {@link BiConsumer#accept} to register sound events to be data-generated.
     *
     * <p>Registered sound types will be appended to their own {@code sounds.json} in a namespace corresponding to
     * the id of the sound event they are assigned to.
     */
    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        exporter.add(MtsSounds.BONK, configureSoundInner(MtsSounds.BONK, "bonk"));
        exporter.add(MtsSounds.END_SUFFOCATION, configureSoundInner(MtsSounds.END_SUFFOCATION, "gasping_of_air", 4, true));
    }

    private SoundTypeBuilder configureSoundInner(SoundEvent sound, String soundName, int count, boolean replace) {
        return SoundTypeBuilder.of(sound)
                .replace(replace)
                .subtitle(this.concatSoundSubtitle(soundName))
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Constants.modId(soundName)), count);
    }

    private SoundTypeBuilder configureSoundInner(SoundEvent sound, String soundName, int count) {
        return this.configureSoundInner(sound, soundName, count, false);
    }

    private SoundTypeBuilder configureSoundInner(SoundEvent sound, String soundName) {
        return SoundTypeBuilder.of(sound).subtitle(concatSoundSubtitle(soundName))
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Constants.modId(soundName)));
    }

    private String concatSoundSubtitle(String name) {
        return "sound.minecraft-the-stories-mod." + name;
    }

    @Override
    public String getName() {
        return "Mts Mod Sounds";
    }
}
