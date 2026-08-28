package net.mrgoddavid.minecraftthestoriesmod.block.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterMenu;

public class MtsMenuTypes {

    public static final MenuType<EnderExalterMenu> ENDER_EXALTER_MENU = register("ender_exalter_menu", EnderExalterMenu::new);
    public static final MenuType<EnricherMenu> ENRICHER_MENU = register("enricher_menu", EnricherMenu::new);
    public static final MenuType<SuperCrafterMenu> SUPER_CRAFTER_MENU = register("super_crafter_menu", SuperCrafterMenu::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(final String path, ExtendedMenuType.ExtendedFactory<T, BlockPos> factory) {
        return Registry.register(BuiltInRegistries.MENU,
                Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path),
                new ExtendedMenuType<>(factory, BlockPos.STREAM_CODEC)
        );
    }

    public static void registerMenuTypes() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Menu Types for " + MinecraftTheStoriesMod.MOD_ID);
    }
}
