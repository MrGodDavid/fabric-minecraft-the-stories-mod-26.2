package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 11/16/2026
 */
public final class MtsTemplatePools {

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);

        register(context, MtsTemplatePoolKeys.ABANDONED_HAY_WAGON, emptyPool, "abandoned_hay_wagon");
    }

    private static void register(BootstrapContext<StructureTemplatePool> context, ResourceKey<StructureTemplatePool> structureKey, Holder<StructureTemplatePool> fallback, String name) {
        context.register(
                structureKey,
                new StructureTemplatePool(
                        fallback,
                        ImmutableList.of(Pair.of(StructurePoolElement.single(Constants.modId(name).toString()), 1)),
                        StructureTemplatePool.Projection.RIGID
                ));
    }

    private MtsTemplatePools() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
