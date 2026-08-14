package net.mrgoddavid.minecraftthestoriesmod.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

import java.util.function.Function;

/**
 * @author Mr. GodDavid
 * @since 8/12/2026
 */
public class ModItems {

    /**
     * Private constructor.
     */
    private ModItems() {
        throw new IllegalStateException("You can't instantiate ModItems class!");
    }

    // Custom mod items go here.
    public static final Item RAW_AMETHYST = registerItem("raw_amethyst", Item::new);
    public static final Item RAW_DIAMOND = registerItem("raw_diamond", Item::new);
    public static final Item RAW_EMERALD = registerItem("raw_emerald", Item::new);
    public static final Item RAW_RUBY = registerItem("raw_ruby", Item::new);
    public static final Item RAW_TOPAZ = registerItem("raw_topaz", Item::new);
    public static final Item STRONG_AMETHYST = registerItem("strong_amethyst", Item::new);
    public static final Item STRONG_AMETHYST_INGOT = registerItem("strong_amethyst_ingot", Item::new);
    public static final Item STRONG_DIAMOND = registerItem("strong_diamond", Item::new);
    public static final Item STRONG_DIAMOND_INGOT = registerItem("strong_diamond_ingot", Item::new);
    public static final Item STRONG_EMERALD = registerItem("strong_emerald", Item::new);
    public static final Item STRONG_EMERALD_INGOT = registerItem("strong_emerald_ingot", Item::new);
    public static final Item STRONG_GOLD = registerItem("strong_gold", Item::new);
    public static final Item STRONG_GOLD_INGOT = registerItem("strong_gold_ingot", Item::new);
    public static final Item STRONG_IRON_INGOT = registerItem("strong_iron_ingot", Item::new);
    public static final Item STRONG_IRON = registerItem("strong_iron", Item::new);
    public static final Item STRONG_RUBY = registerItem("strong_ruby", Item::new);
    public static final Item STRONG_RUBY_INGOT = registerItem("strong_ruby_ingot", Item::new);
    public static final Item STRONG_TOPAZ = registerItem("strong_topaz", Item::new);
    public static final Item STRONG_TOPAZ_INGOT = registerItem("strong_topaz_ingot", Item::new);

    // #######################################################
    // #                     EMERALD TOOLS                   #
    // #######################################################
    public static final Item EMERALD_SWORD = registerItem("emerald_sword", properties ->
            new Item(properties.sword(ModItemToolMaterials.EMERALD, 3.0f, -2.4f)));

    /**
     * Gets the resource key of the mod item.
     *
     * @param item mod item.
     * @return the resource key of the mod item.
     */
    public static ResourceKey<Item> getResourceKey(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    /**
     * Helper method when we register every new mod item to vanilla Minecraft. Obviously, this method returns an
     * {@link Item}. We first calls the {@link Registry#register(Registry, Identifier, Object)} to register our item
     * into core project, so Fabric can add this item into Minecraft.
     * <p>{@link Registry#register(Registry, Identifier, Object)} receives three parameters. The first one is
     * {@link Registry}, which in this case is a {@link net.minecraft.core.DefaultedRegistry} called ITEM. This defines
     * the type of registry that we want to register. The second parameter is an {@link Identifier}. We call the
     * {@link Identifier#fromNamespaceAndPath(String, String)} method, which first check the validity of namespace
     * (mod_id), and the name of the item. If both of them are valid, we create a new {@link Identifier} that consists
     * the mod id and name of our mod item. The third parameter is the value of the type parameter {@code T}, which
     * {@code T extends V}. The type parameter, in this case, of the {@link Registry} is {@link Item}, so
     * <pre>{@code Function<Item.Properties, Item> function}</pre> must returns the {@link Item}/subclasses of
     * {@link Item}.</p>
     * <p>In the function.apply(), we first create a new instance of {@link Item.Properties} and then immediately set
     * the id of it. The {@link Item.Properties#setId(ResourceKey)} receives a {@link ResourceKey}. At the same time,
     * we create a new resource key by calling the {@link ResourceKey#create(ResourceKey, Identifier)}. The first
     * parameter {@link ResourceKey#create(ResourceKey, Identifier)} is a resource key with type parameter that
     * extends {@link Registry}. The second parameter of it method is an Identifier, which we had done previously.</p>
     *
     * @param name     of the mod item to be registered.
     * @param function a functional interface that applies, in this case, function to {@link Item.Properties}, which
     *                 returns {@link Item}.
     * @return the registered Item.
     */
    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        Identifier id = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, id);
        return Registry.register(
                BuiltInRegistries.ITEM, id,
                function.apply(new Item.Properties().setId(resourceKey))
        );
    }

    /**
     * Registers all mod items to vanilla Minecraft.
     */
    public static void registerModItems() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Mod Items for " + MinecraftTheStoriesMod.MOD_ID);

        // put the items to the creative mode tab -> ingredients tab.
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(RAW_AMETHYST);
            fabricCreativeModeTabOutput.accept(RAW_DIAMOND);
            fabricCreativeModeTabOutput.accept(RAW_EMERALD);
            fabricCreativeModeTabOutput.accept(RAW_RUBY);
            fabricCreativeModeTabOutput.accept(RAW_TOPAZ);
            fabricCreativeModeTabOutput.accept(STRONG_AMETHYST);
            fabricCreativeModeTabOutput.accept(STRONG_AMETHYST_INGOT);
            fabricCreativeModeTabOutput.accept(STRONG_DIAMOND);
            fabricCreativeModeTabOutput.accept(STRONG_DIAMOND_INGOT);
            fabricCreativeModeTabOutput.accept(STRONG_EMERALD);
            fabricCreativeModeTabOutput.accept(STRONG_EMERALD_INGOT);
            fabricCreativeModeTabOutput.accept(STRONG_GOLD);
            fabricCreativeModeTabOutput.accept(STRONG_GOLD_INGOT);
            fabricCreativeModeTabOutput.accept(STRONG_IRON);
            fabricCreativeModeTabOutput.accept(STRONG_IRON_INGOT);
            fabricCreativeModeTabOutput.accept(STRONG_RUBY);
            fabricCreativeModeTabOutput.accept(STRONG_RUBY_INGOT);
            fabricCreativeModeTabOutput.accept(STRONG_TOPAZ);
            fabricCreativeModeTabOutput.accept(STRONG_TOPAZ_INGOT);
        });
    }
}
