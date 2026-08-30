package net.mrgoddavid.minecraftthestoriesmod.fluid;

import net.fabricmc.fabric.api.registry.fluid.EntityFluidInteractionRegistry;
import net.fabricmc.fabric.api.registry.fluid.FluidBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.particle.MtsParticleTypes;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public class MtsEntityFluidInteractions {

    public static void registerInteractions() {
        MinecraftTheStoriesMod.LOGGER.info("Registering custom entity fluid interactions for " + MinecraftTheStoriesMod.MOD_ID);

        EntityFluidInteractionRegistry.register(MtsFluidTags.ENRICHER_WASTE,
                FluidBehavior.simple().onEnteredFluid((
                                        (entity, firstTick) -> {
                                            if (entity.level().isClientSide()) {

                                                // float volumeModifier = 0.2f;
                                                // Vec3 movement = entity.getDeltaMovement();
                                                //  float speed = Math.min(1.0f, (float) Math.sqrt(movement.x * movement.x * (double) 0.2F + movement.y * movement.y + movement.z * movement.z * (double) 0.2F) * volumeModifier);

                                                //     Vec3 movement = entity.getDeltaMovement();
                                                //     float yt = (float) Math.floor(entity.getY());
//
                                                //     for(int i = 0; (float)i < 1.0F + entity.getBbWidth() * 20.0F; ++i) {
                                                //         double xo = (entity.getRandom().nextDouble() * (double)2.0F - (double)1.0F) * (double)entity.getBbWidth();
                                                //         double zo = (entity.getRandom().nextDouble() * (double)2.0F - (double)1.0F) * (double)entity.getBbWidth();
                                                //         entity.level().addParticle(MtsParticleTypes.ENRICHER_WASTE_PARTICLE, entity.getX() + xo, (double)(yt + 0.5F), entity.getZ() + zo, movement.x, movement.y - entity.getRandom().nextDouble() * (double)0.2F, movement.z);
                                                //     }
//
                                                //     for(int i = 0; (float)i < 1.0F + entity.getBbWidth() * 20.0F; ++i) {
                                                //         double xo = (entity.getRandom().nextDouble() * (double)2.0F - (double)1.0F) * (double)entity.getBbWidth();
                                                //         double zo = (entity.getRandom().nextDouble() * (double)2.0F - (double)1.0F) * (double)entity.getBbWidth();
                                                //         entity.level().addParticle(MtsParticleTypes.ENRICHER_WASTE_PARTICLE, entity.getX() + xo, (double)(yt + 0.5F), entity.getZ() + zo, movement.x, movement.y, movement.z);
                                                //     }
                                            }
                                        })
                        )
                        .movementSpeed(0.01F)
                        .movementSlowdown(0.4F, 0.6F)
                        .gravityMultiplier(1.0f / 8.0f)
                        .flowingPushScale(0.005d)
                        .allowSwimming(true)
                        .makeMobsFloat(true)
                        .allowBoats(true)
                        .allowSprinting(false)
                        .build()
        );

        EntityFluidInteractionRegistry.register(MtsFluidTags.BLUE_FUEL, FluidBehavior.simple()
                .movementSpeed(0.015F)
                .movementSlowdown(0.4F, 0.6F)
                .gravityMultiplier(1.0f / 10.0f)
                .flowingPushScale(0.01d)
                .allowSwimming(true)
                .makeMobsFloat(true)
                .allowBoats(true)
                .allowSprinting(false)
                .build());
    }
}
