package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/28/2026
 */
public class MtsDamageTypes {

    public static final ResourceKey<DamageType> CUDGEL_BONK = create("cudgel_bonk");

    public static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Constants.modId(name));
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(CUDGEL_BONK, new DamageType("cudgel_bonk", 0.1f, DamageEffects.HURT));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }

    public static void register() {
        MtsLogger.info("Custom Damage Types");
    }
}
