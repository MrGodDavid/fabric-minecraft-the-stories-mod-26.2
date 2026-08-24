package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;

/**
 * Mts Armor materials.
 *
 * @author Mr. GodDavid
 * @since 8/23/2026
 */
public class MtsArmorMaterials {

    public static final ResourceKey<? extends Registry<EquipmentAsset>> ARMOR_MATERIAL_REGISTRY_KEY =
            ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> EMERALD_KEY = createArmorMaterialKey("emerald");
    public static final ResourceKey<EquipmentAsset> TOPAZ_KEY = createArmorMaterialKey("topaz");
    public static final ResourceKey<EquipmentAsset> RUBY_KEY = createArmorMaterialKey("ruby");
    public static final ResourceKey<EquipmentAsset> AMETHYST_KEY = createArmorMaterialKey("amethyst");

    public static final ArmorMaterial EMERALD_ARMOR_MATERIAL = new ArmorMaterial(
            750, ArmorMaterials.makeDefense(3, 5, 7, 3, 8),
            10, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0f, 0.0f, MtsTags.Items.EMERALD_REPAIR, EMERALD_KEY
    );
    public static final ArmorMaterial TOPAZ_ARMOR_MATERIAL = new ArmorMaterial(
            750, ArmorMaterials.makeDefense(3, 6, 7, 7, 7),
            12, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.5f, 0.05f, MtsTags.Items.TOPAZ_REPAIR, TOPAZ_KEY
    );
    public static final ArmorMaterial RUBY_ARMOR_MATERIAL = new ArmorMaterial(
            750, ArmorMaterials.makeDefense(3, 6, 7, 7, 8),
            10, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.5f, 0.075f, MtsTags.Items.RUBY_REPAIR, RUBY_KEY
    );
    public static final ArmorMaterial AMETHYST_ARMOR_MATERIAL = new ArmorMaterial(
            750, ArmorMaterials.makeDefense(4, 7, 8, 9, 11),
            30, SoundEvents.ARMOR_EQUIP_DIAMOND, 4.0f, 0.15f, MtsTags.Items.AMETHYST_REPAIR, AMETHYST_KEY
    );

    private static ResourceKey<EquipmentAsset> createArmorMaterialKey(final String path) {
        return ResourceKey.create(ARMOR_MATERIAL_REGISTRY_KEY, Identifier.fromNamespaceAndPath(
                MinecraftTheStoriesMod.MOD_ID, path
        ));
    }
}
