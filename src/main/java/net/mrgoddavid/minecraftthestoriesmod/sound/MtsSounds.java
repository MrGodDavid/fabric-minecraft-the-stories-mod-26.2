package net.mrgoddavid.minecraftthestoriesmod.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/28/2026
 */
public final class MtsSounds {

    public static final SoundEvent BONK = registerSoundEvent("bonk");

    public static void register() {
        MtsLogger.info("Custom Sounds");
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Constants.modId(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
