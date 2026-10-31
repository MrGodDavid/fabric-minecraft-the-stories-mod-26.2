package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.component.MtsDataComponentTypes;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 10/30/2026
 */
public final class MtsModifyVanillaFoodItems {

    public static void register() {
        MtsLogger.info("Custom Thirst Restoration Of Vanilla Food Items");

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.APPLE, builder -> builder.set(MtsDataComponentTypes.THIRST_RESTORATION, 2));
        });
    }

    private MtsModifyVanillaFoodItems() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
