package net.mrgoddavid.minecraftthestoriesmod.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

import java.util.function.Function;

/**
 * Holds all mod items.
 *
 * @author Mr. GodDavid
 * @since 8/12/2026
 */
public class MtsItems {

    /**
     * Private constructor.
     */
    private MtsItems() {
        throw new IllegalStateException("You can't instantiate ModItems class!");
    }

    // Custom mod items go here.
    public static final Item RAW_STRONG_AMETHYST = registerItem("raw_strong_amethyst", Item::new);
    public static final Item RAW_STRONG_DIAMOND = registerItem("raw_strong_diamond", Item::new);
    public static final Item RAW_STRONG_EMERALD = registerItem("raw_strong_emerald", Item::new);
    public static final Item RAW_STRONG_RUBY = registerItem("raw_strong_ruby", Item::new);
    public static final Item RAW_STRONG_TOPAZ = registerItem("raw_strong_topaz", Item::new);
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

    // #################################################################################################################
    // #                                                AMETHYST TOOLS                                                 #
    // #################################################################################################################
    public static final Item STRONG_AMETHYST_AXE = registerItem("strong_amethyst_axe", properties ->
            new AxeItem(MtsItemToolMaterials.AMETHYST, 6f, -3.2f, properties.fireResistant()));
    public static final Item STRONG_AMETHYST_HOE = registerItem("strong_amethyst_hoe", properties ->
            new HoeItem(MtsItemToolMaterials.AMETHYST, 0f, -3.0f, properties.fireResistant()));
    public static final Item STRONG_AMETHYST_PICKAXE = registerItem("strong_amethyst_pickaxe", properties ->
            new Item(properties.pickaxe(MtsItemToolMaterials.AMETHYST, 1f, -2.8f).fireResistant()));
    public static final Item STRONG_AMETHYST_SHOVEL = registerItem("strong_amethyst_shovel", properties ->
            new ShovelItem(MtsItemToolMaterials.AMETHYST, 1.5f, -3.0f, properties.fireResistant()));
    public static final Item STRONG_AMETHYST_SPEAR = registerItem("strong_amethyst_spear", properties ->
            new Item(properties.spear(MtsItemToolMaterials.AMETHYST,
                    0.95f, 0.95f, 0.6f, 2.5f, 11.0f, 6.75f, 5.1f, 8.25f, 4.6f).fireResistant()));
    public static final Item STRONG_AMETHYST_SWORD = registerItem("strong_amethyst_sword", properties ->
            new Item(properties.sword(MtsItemToolMaterials.AMETHYST, 3.0f, -2.4f).fireResistant()));

    // #################################################################################################################
    // #                                                EMERALD TOOLS                                                  #
    // #################################################################################################################
    public static final Item EMERALD_AXE = registerItem("emerald_axe", properties ->
            new AxeItem(MtsItemToolMaterials.EMERALD, 6f, -3.2f, properties));
    public static final Item EMERALD_HOE = registerItem("emerald_hoe", properties ->
            new HoeItem(MtsItemToolMaterials.EMERALD, 0f, -3.0f, properties));
    public static final Item EMERALD_PICKAXE = registerItem("emerald_pickaxe", properties ->
            new Item(properties.pickaxe(MtsItemToolMaterials.EMERALD, 1f, -2.8f)));
    public static final Item EMERALD_SHOVEL = registerItem("emerald_shovel", properties ->
            new ShovelItem(MtsItemToolMaterials.EMERALD, 1.5f, -3.0f, properties));
    public static final Item EMERALD_SPEAR = registerItem("emerald_spear", properties ->
            new Item(properties.spear(MtsItemToolMaterials.EMERALD,
                    1.0f, 1.0f, 1.0f, 2.75f, 11.5f, 6.75f, 5.1f, 11.0f, 4.6f)));
    public static final Item EMERALD_SWORD = registerItem("emerald_sword", properties ->
            new Item(properties.sword(MtsItemToolMaterials.EMERALD, 3.0f, -2.4f)));

    // #################################################################################################################
    // #                                                RUBY TOOLS                                                     #
    // #################################################################################################################
    public static final Item STRONG_RUBY_AXE = registerItem("strong_ruby_axe", properties ->
            new AxeItem(MtsItemToolMaterials.RUBY, 6f, -3.2f, properties.fireResistant()));
    public static final Item STRONG_RUBY_HOE = registerItem("strong_ruby_hoe", properties ->
            new HoeItem(MtsItemToolMaterials.RUBY, 0f, -3.0f, properties.fireResistant()));
    public static final Item STRONG_RUBY_PICKAXE = registerItem("strong_ruby_pickaxe", properties ->
            new Item(properties.pickaxe(MtsItemToolMaterials.RUBY, 1f, -2.8f).fireResistant()));
    public static final Item STRONG_RUBY_SHOVEL = registerItem("strong_ruby_shovel", properties ->
            new ShovelItem(MtsItemToolMaterials.RUBY, 1.5f, -3.0f, properties.fireResistant()));
    public static final Item STRONG_RUBY_SPEAR = registerItem("strong_ruby_spear", properties ->
            new Item(properties.spear(MtsItemToolMaterials.RUBY,
                    1.15f, 1.05f, 0.55f, 2.75f, 10.0f, 6.75f, 5.1f, 9.25f, 4.6f).fireResistant()));
    public static final Item STRONG_RUBY_SWORD = registerItem("strong_ruby_sword", properties ->
            new Item(properties.sword(MtsItemToolMaterials.RUBY, 3.0f, -2.4f).fireResistant()));

    // #################################################################################################################
    // #                                                TOPAZ TOOLS                                                    #
    // #################################################################################################################
    public static final Item STRONG_TOPAZ_AXE = registerItem("strong_topaz_axe", properties ->
            new AxeItem(MtsItemToolMaterials.TOPAZ, 6f, -3.2f, properties));
    public static final Item STRONG_TOPAZ_HOE = registerItem("strong_topaz_hoe", properties ->
            new HoeItem(MtsItemToolMaterials.TOPAZ, 0f, -3.0f, properties));
    public static final Item STRONG_TOPAZ_PICKAXE = registerItem("strong_topaz_pickaxe", properties ->
            new Item(properties.pickaxe(MtsItemToolMaterials.TOPAZ, 1f, -2.8f)));
    public static final Item STRONG_TOPAZ_SHOVEL = registerItem("strong_topaz_shovel", properties ->
            new ShovelItem(MtsItemToolMaterials.TOPAZ, 1.5f, -3.0f, properties));
    public static final Item STRONG_TOPAZ_SPEAR = registerItem("strong_topaz_spear", properties ->
            new Item(properties.spear(MtsItemToolMaterials.TOPAZ,
                    1.11f, 0.85f, 0.75f, 3.0f, 11.0f, 7.0f, 3.3f, 9.75f, 4.6f)));
    public static final Item STRONG_TOPAZ_SWORD = registerItem("strong_topaz_sword", properties ->
            new Item(properties.sword(MtsItemToolMaterials.TOPAZ, 3.0f, -2.4f)));

    public static final Item DIAMOND_BATTLE_AXE = registerItem("diamond_battle_axe", properties ->
            new Item(properties.sword(ToolMaterial.DIAMOND, 5.5f, -3.5f)));

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
            fabricCreativeModeTabOutput.accept(RAW_STRONG_AMETHYST);
            fabricCreativeModeTabOutput.accept(RAW_STRONG_DIAMOND);
            fabricCreativeModeTabOutput.accept(RAW_STRONG_EMERALD);
            fabricCreativeModeTabOutput.accept(RAW_STRONG_RUBY);
            fabricCreativeModeTabOutput.accept(RAW_STRONG_TOPAZ);
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
