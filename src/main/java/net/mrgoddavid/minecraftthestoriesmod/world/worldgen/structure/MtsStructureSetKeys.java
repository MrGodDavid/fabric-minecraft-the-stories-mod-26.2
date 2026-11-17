package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 11/16/2026
 */
public final class MtsStructureSetKeys {

    public static final ResourceKey<StructureSet> ABANDONED_HAY_KEY = register("abandoned_hay_wagon");

    private static ResourceKey<StructureSet> register(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Constants.modId(name));
    }

    public static void register() {
        MtsLogger.info("Custom Structure Set Keys");
    }

    private MtsStructureSetKeys() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
