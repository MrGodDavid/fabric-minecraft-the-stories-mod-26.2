package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 11/16/2026
 */
public final class MtsStructureSets {

    public static void boostrap(BootstrapContext<StructureSet> context) {
        context.register(
                MtsStructureSetKeys.ABANDONED_HAY_KEY,
                new StructureSet(
                        context.lookup(Registries.STRUCTURE).getOrThrow(MtsStructureKeys.ABANDONED_HAY_WAGON),
                        new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 137421)
                ));
    }

    private MtsStructureSets() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
