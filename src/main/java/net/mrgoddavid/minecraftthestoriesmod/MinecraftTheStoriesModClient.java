package net.mrgoddavid.minecraftthestoriesmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.advancement.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterBlockRenderer;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorBlockRenderer;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorFreewheelModel;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsMenuScreens;
import net.mrgoddavid.minecraftthestoriesmod.client.MtsFluidRenderingRegistries;

import net.mrgoddavid.minecraftthestoriesmod.test.MtsTestWorld;
import net.mrgoddavid.minecraftthestoriesmod.tooltip.MtsItemTooltips;

public class MinecraftTheStoriesModClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {

        MtsTestWorld.changeDevTitleScreen();
//        ModelLayerRegistry.registerModelLayer(OreCompressorBlockRenderer.MODEL_LAYER, OreCompressorModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(OreCompressorBlockRenderer.MODEL_LAYER, OreCompressorFreewheelModel::getTexturedModelData);
        BlockEntityRenderers.register(MtsBlockEntities.ORE_COMPRESSOR_BE, OreCompressorBlockRenderer::new);
        BlockEntityRenderers.register(MtsBlockEntities.ENDER_EXALTER_BE, EnderExalterBlockRenderer::new);
        MtsMenuScreens.register();
        MtsItemTooltips.register();
        MtsFluidRenderingRegistries.register();

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipFlag, lines) -> {
            if (stack.is(Items.DIAMOND_SWORD) || stack.is(Items.NETHERITE_SWORD)) {
                lines.add(Component.literal("A weapon forged in ancient times").withStyle(ChatFormatting.GOLD));
            }
        });

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, hitResult) -> {
            if (!(player instanceof ServerPlayer serverPlayer)) {
                return;
            }

            ItemStack tool = player.getMainHandItem();
            MtsAdvancementTriggers.MINE_BLOCK_WITH_TOOL_TRIGGER.trigger(
                    serverPlayer, state, tool
            );
        });
    }
}
