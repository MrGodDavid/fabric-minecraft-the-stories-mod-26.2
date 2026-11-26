package net.mrgoddavid.minecraftthestoriesmod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public class MtsKeyMappings {

    private static final KeyMapping.Category CATEGORY = registerKeyMappingCategory("category");

    public static final KeyMapping DODGE_ROLL = registerKeyMap("dodge_roll", InputConstants.Type.KEYSYM, InputConstants.KEY_R, CATEGORY);

    private static KeyMapping registerKeyMap(String name, InputConstants.Type type, int keyMap, KeyMapping.Category category) {
        return KeyMappingHelper.registerKeyMapping(new KeyMapping(Constants.keyMappingName(name), type, keyMap, category));
    }

    private static KeyMapping.Category registerKeyMappingCategory(String name) {
        return KeyMapping.Category.register(Constants.modId(name));
    }

    public static void register() {
        MtsLogger.info("Custom Key Mappings");
    }

    private MtsKeyMappings() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
