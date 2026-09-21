package net.mrgoddavid.minecraftthestoriesmod.general;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer.SqueezerMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterMenu;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.StoryBookMenu;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

public final class MtsMenuTypes {

    // BLOCKS
    public static final MenuType<EnderExalterMenu> ENDER_EXALTER_MENU = register("ender_exalter_menu",EnderExalterMenu::new);
    public static final MenuType<EnricherMenu> ENRICHER_MENU = register("enricher_menu", EnricherMenu::new);
    public static final MenuType<SuperCrafterMenu> SUPER_CRAFTER_MENU = register("super_crafter_menu", SuperCrafterMenu::new);
    public static final MenuType<OreCompressorMenu> ORE_COMPRESSOR_MENU = register("ore_compressor_menu", OreCompressorMenu::new);
    public static final MenuType<SqueezerMenu> SQUEEZER_MENU = register("squeezer_menu", SqueezerMenu::new);

    // ITEMS
    public static final MenuType<StoryBookMenu> STORY_BOOK_MENU = register("story_book_menu", StoryBookMenu::new);

    public static void register() {
        MtsLogger.info("Menu Types");
    }

    public static <T extends AbstractContainerMenu> MenuType<T> register(final String path, ExtendedMenuType.ExtendedFactory<T, BlockPos> factory) {
        return Registry.register(BuiltInRegistries.MENU,
                Constants.modId(path), new ExtendedMenuType<>(factory, BlockPos.STREAM_CODEC)
        );
    }

    public static <T extends AbstractContainerMenu> MenuType<T> register(final String path, final MenuType.MenuSupplier<T> supplier) {
        return Registry.register(BuiltInRegistries.MENU, Constants.modId(path), new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS));
    }

    private MtsMenuTypes() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
