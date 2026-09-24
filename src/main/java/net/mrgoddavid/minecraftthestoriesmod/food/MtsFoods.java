package net.mrgoddavid.minecraftthestoriesmod.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/13/2026
 */
public class MtsFoods {

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build();
    public static final FoodProperties RAW_STRAWBERRY = new FoodProperties.Builder().nutrition(1).saturationModifier(0.125f).build();
    public static final FoodProperties BLUEBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).build();
    public static final FoodProperties RAW_BLUEBERRY = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();

    public static final Consumable STRAWBERRY_CONSUMABLE = Consumables.defaultFood().consumeSeconds(1.2f).build();
    public static final Consumable RAW_STRAWBERRY_CONSUMABLE = Consumables.defaultFood().consumeSeconds(1.2f).build();
    public static final Consumable BLUEBERRY_CONSUMABLE = Consumables.defaultFood().consumeSeconds(1.0f).build();
    public static final Consumable RAW_BLUEBERRY_CONSUMABLE = Consumables.defaultFood().consumeSeconds(1.0f).build();

    public static void register() {
        MtsLogger.info("Custom Foods");
    }
}
