package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.component.MtsDataComponentTypes;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.world.drink.DietaryWaterProperties;

/**
 * @author Mr. GodDavid
 * @since 10/30/2026
 */
public final class MtsModifyVanillaFoodItems {

    public static void register() {
        MtsLogger.info("Custom Thirst Restoration Of Vanilla Food Items");

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.APPLE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(2).thirstExhaustion(-5.0F).build()));
            context.modify(Items.BAKED_POTATO, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-3).thirstExhaustion(7.5F).build()));
            context.modify(Items.BEETROOT, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(1).thirstExhaustion(-5.0F).build()));
            context.modify(Items.BEETROOT_SOUP, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(10).thirstExhaustion(-10.0F).build()));
            context.modify(Items.BREAD, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-1).thirstExhaustion(-8.0F).build()));
            context.modify(Items.CAKE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(0).thirstExhaustion(0.1F).build()));
            context.modify(Items.CARROT, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(2).thirstExhaustion(-4.5F).build()));
            context.modify(Items.CHORUS_FRUIT, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(2).thirstExhaustion(-3.5F).build()));
            context.modify(Items.COOKED_CHICKEN, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(1.0F).build()));
            context.modify(Items.COOKED_COD, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-1).thirstExhaustion(0.5F).build()));
            context.modify(Items.COOKED_MUTTON, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-4).thirstExhaustion(1.25F).build()));
            context.modify(Items.COOKED_PORKCHOP, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-6).thirstExhaustion(1.5F).build()));
            context.modify(Items.COOKED_RABBIT, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-3).thirstExhaustion(1.0F).build()));
            context.modify(Items.COOKED_SALMON, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-1).thirstExhaustion(0.5F).build()));
            context.modify(Items.COOKIE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(0.75F).build()));
            context.modify(Items.DRIED_KELP, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-10).thirstExhaustion(10.0F).build()));
            context.modify(Items.ENCHANTED_GOLDEN_APPLE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-8).thirstExhaustion(4.0F).build()));
            context.modify(Items.GOLDEN_APPLE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-4).thirstExhaustion(2.0F).build()));
            context.modify(Items.GLOW_BERRIES, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(4).thirstExhaustion(-7.5F).build()));
            context.modify(Items.GOLDEN_CARROT, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-4).thirstExhaustion(2.0F).build()));
            context.modify(Items.HONEY_BOTTLE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-1).thirstExhaustion(-0.5F).build()));
            context.modify(Items.MELON_SLICE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(8).thirstExhaustion(-7.0F).build()));
            context.modify(Items.MUSHROOM_STEW, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(8).thirstExhaustion(-2.5F).build()));
            context.modify(Items.POISONOUS_POTATO, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-7).thirstExhaustion(-8.5F).build()));
            context.modify(Items.POTATO, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(1).thirstExhaustion(-0.5F).build()));
            context.modify(Items.PUFFERFISH, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-1).thirstExhaustion(-7.5F).build()));
            context.modify(Items.PUMPKIN_PIE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-4).thirstExhaustion(-9.5F).build()));
            context.modify(Items.RABBIT_STEW, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(6).thirstExhaustion(-8.5F).build()));
            context.modify(Items.BEEF, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(-2.0F).build()));
            context.modify(Items.CHICKEN, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(-2.0F).build()));
            context.modify(Items.COD, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(1).thirstExhaustion(-1.0F).build()));
            context.modify(Items.MUTTON, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(-2.0F).build()));
            context.modify(Items.PORKCHOP, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(-2.0F).build()));
            context.modify(Items.RABBIT, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-2).thirstExhaustion(-2.0F).build()));
            context.modify(Items.SALMON, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(1).thirstExhaustion(-1.0F).build()));
            context.modify(Items.ROTTEN_FLESH, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-5).thirstExhaustion(7.5F).build()));
            context.modify(Items.SPIDER_EYE, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(1).thirstExhaustion(-.5F).build()));
            context.modify(Items.COOKED_BEEF, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(-8).thirstExhaustion(8.5F).build()));
            context.modify(Items.SUSPICIOUS_STEW, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(8).thirstExhaustion(-2.5F).build()));
            context.modify(Items.SWEET_BERRIES, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(6).thirstExhaustion(-7.0F).build()));
            context.modify(Items.TROPICAL_FISH, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(1).thirstExhaustion(-1.0F).build()));
            context.modify(Items.MILK_BUCKET, builder -> builder.set(MtsDataComponentTypes.DIETARY_WATER,
                    new DietaryWaterProperties.Builder().thirstRestoration(4).thirstExhaustion(-5.0F).build()));
        });
    }

    private MtsModifyVanillaFoodItems() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
