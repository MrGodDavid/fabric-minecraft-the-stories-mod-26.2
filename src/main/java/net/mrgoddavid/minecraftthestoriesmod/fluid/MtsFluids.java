package net.mrgoddavid.minecraftthestoriesmod.fluid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.fluid.custom.EnricherWasteFluid;

public class MtsFluids {

    public static final FlowingFluid ENRICHER_WASTE_STILL = createFluid("enricher_waste_still", new EnricherWasteFluid.Source());
    public static final FlowingFluid ENRICHER_WASTE_FLOWING = createFluid("enricher_waste_flowing", new EnricherWasteFluid.Flowing());

    private static FlowingFluid createFluid(String name, FlowingFluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, ResourceKey.create(
                Registries.FLUID, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name)
        ), fluid);
    }

    private MtsFluids() throws IllegalAccessException {
        throw new IllegalAccessException("You can't instantiate MtsFluids!");
    }

    public static void registerFluids() {
        MinecraftTheStoriesMod.LOGGER.info("Registering MtsFluids for: " + MinecraftTheStoriesMod.MOD_ID);
    }

    public static ResourceKey<Fluid> getResourceKey(Fluid fluid) {
        return BuiltInRegistries.FLUID.getResourceKey(fluid).get();
    }
}
