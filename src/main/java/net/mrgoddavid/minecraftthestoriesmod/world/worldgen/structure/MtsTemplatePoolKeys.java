package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 11/16/2026
 */
public final class MtsTemplatePoolKeys {

    public static final ResourceKey<StructureTemplatePool> ABANDONED_HAY_WAGON = register("abandoned_hay_wagon");

    private static ResourceKey<StructureTemplatePool> register(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, Constants.modId(name));
    }

    public static void register() {
        MtsLogger.info("Custom Structure Template Pool Keys");
    }

    private MtsTemplatePoolKeys() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
