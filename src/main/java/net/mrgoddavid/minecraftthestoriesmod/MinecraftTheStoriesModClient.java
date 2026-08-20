package net.mrgoddavid.minecraftthestoriesmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter.EnricherBlockEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.tooltip.MtsItemTooltips;

public class MinecraftTheStoriesModClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {

        BlockEntityRenderers.register(MtsBlockEntities.ENRICHER_BE, EnricherBlockEntityRenderer::new);
        MtsItemTooltips.register();

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipFlag, lines) -> {
            if (stack.is(Items.DIAMOND_SWORD) ||  stack.is(Items.NETHERITE_SWORD)) {
                lines.add(Component.literal("A weapon forged in ancient times").withStyle(ChatFormatting.GOLD));
            }

//            if (stack.is(MtsTags.Items.MTS_COMMON_WEAPONS_SWORDS)) {
//                lines.add(Component.literal("Common Weapons").withStyle(ChatFormatting.GRAY));
//            } else if (stack.is(MtsTags.Items.MTS_UNCOMMON_WEAPONS)) {
//                lines.add(Component.literal("Uncommon Weapons").withStyle(ChatFormatting.DARK_GREEN));
//            }
        });
    }
}
