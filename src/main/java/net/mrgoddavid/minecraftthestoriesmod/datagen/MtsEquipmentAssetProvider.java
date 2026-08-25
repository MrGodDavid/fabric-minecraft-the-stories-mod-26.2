package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsArmorMaterials;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Datagen provider of Mts mod's equipment asset.
 *
 * @author Mr. GodDavid
 * @since 8/23/2026
 */
public class MtsEquipmentAssetProvider implements DataProvider {

    private final PackOutput.PathProvider pathProvider;

    public MtsEquipmentAssetProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
        MinecraftTheStoriesMod.LOGGER.info("Providing data of MTS Equipment Assets for: " + MinecraftTheStoriesMod.MOD_ID);
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(MtsArmorMaterials.EMERALD_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "emerald"))
                .build());
        consumer.accept(MtsArmorMaterials.TOPAZ_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "topaz"))
                .build());
        consumer.accept(MtsArmorMaterials.RUBY_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "ruby"))
                .build());
        consumer.accept(MtsArmorMaterials.AMETHYST_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "amethyst"))
                .build());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @Override
    public String getName() {
        return "Mts Equipment Asset Definitions";
    }
}
