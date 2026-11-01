package net.mrgoddavid.minecraftthestoriesmod.gui.hud.content;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.ItemUtils;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/31/2026
 */
public class QuickInventoryInfoHud {

    private static final int SPACING = 5;
    private static final int DURABILITY_BAR_BACKGROUND_COLOR = MathUtils.argbToColor(255, 0, 0, 0);
    private static final int TEXT_COLOR = MathUtils.argbToColor(255, 255, 255, 255);

    public static void extractRenderState(GuiGraphicsExtractor graphics, @SuppressWarnings("unused") DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        Font font = mc.font;
        if (player != null) {
            int x = Constants.GUI.GUI_SCALE_WIDTH - 16 - SPACING;
            int originalY = (Constants.GUI.GUI_SCALE_HEIGHT - (16 + 3 * SPACING)) / 2;
            int y = originalY;
            // helmet
            ItemStack helmet = Optional.of(player.getItemBySlot(EquipmentSlot.HEAD)).orElse(ItemStack.EMPTY);
            drawEquipmentIcon(graphics, helmet, x, y);
            drawEquipmentDurabilityText(graphics, helmet, font, x, y);
            // chest
            y += 16 + SPACING;
            ItemStack chest = Optional.of(player.getItemBySlot(EquipmentSlot.CHEST)).orElse(ItemStack.EMPTY);
            drawEquipmentIcon(graphics, chest, x, y);
            drawEquipmentDurabilityText(graphics, chest, font, x, y);
            // leggings
            y += 16 + SPACING;
            ItemStack leggings = Optional.of(player.getItemBySlot(EquipmentSlot.LEGS)).orElse(ItemStack.EMPTY);
            drawEquipmentIcon(graphics, leggings, x, y);
            drawEquipmentDurabilityText(graphics, leggings, font, x, y);
            // boots
            y += 16 + SPACING;
            ItemStack boots = Optional.of(player.getItemBySlot(EquipmentSlot.FEET)).orElse(ItemStack.EMPTY);
            drawEquipmentIcon(graphics, boots, x, y);
            drawEquipmentDurabilityText(graphics, boots, font, x, y);
            x -= 45;
            y = originalY;
            // main hand item
            ItemStack mainHandItem = Optional.of(player.getItemBySlot(EquipmentSlot.MAINHAND)).orElse(ItemStack.EMPTY);
            drawItemsInHandInfo(graphics, mainHandItem, x, y, font);
            // off-hand item
            y += 16 + SPACING;
            ItemStack offHandItem = Optional.of(player.getItemBySlot(EquipmentSlot.OFFHAND)).orElse(ItemStack.EMPTY);
            drawItemsInHandInfo(graphics, offHandItem, x, y, font);
            // num of arrows
            y += 16 + SPACING;
            drawEquipmentIcon(graphics, new ItemStack(Items.ARROW), x, y);
            drawItemTotalStackCount(graphics, Items.ARROW, player, font, x, y);
            // num of fireworks
            y += 16 + SPACING;
            drawEquipmentIcon(graphics, new ItemStack(Items.FIREWORK_ROCKET), x, y);
            drawItemTotalStackCount(graphics, Items.FIREWORK_ROCKET, player, font, x, y);
        }
    }

    private static void drawItemsInHandInfo(GuiGraphicsExtractor graphics, ItemStack itemStack, int x, int y, Font font) {
        drawEquipmentIcon(graphics, itemStack, x, y);
        if (isDurableItems(itemStack)) {
            drawEquipmentDurabilityText(graphics, itemStack, font, x, y);
        } else {
            drawItemStackCountText(graphics, itemStack, font, x, y);
        }
    }

    private static void drawItemTotalStackCount(GuiGraphicsExtractor graphics, Item item, Player player, Font font, int x, int y) {
        int count = ItemUtils.obtainTotalNumOf(player, item);
        if (count != 1) {
            drawCountText(graphics, font, x, y, count);
        }
    }

    private static void drawItemStackCountText(GuiGraphicsExtractor graphics, ItemStack item, Font font, int x, int y) {
        int count = item.getCount();
        if (item.isEmpty() || count <= 1) return;
        drawCountText(graphics, font, x, y, count);
    }

    private static void drawCountText(GuiGraphicsExtractor graphics, Font font, int x, int y, int count) {
        String stackCountText = String.valueOf(count);
        int len = font.width(stackCountText);
        int textX = x + 19 - len;
        int textY = y + 9;
        graphics.text(font, stackCountText, textX, textY, TEXT_COLOR, true);
    }

    private static boolean isDurableItems(ItemStack itemStack) {
        return itemStack.is(MtsTags.Items.DURABLE_WEAPONS);
    }

    private static void drawEquipmentDurabilityText(GuiGraphicsExtractor graphics, ItemStack item, Font font, int x, int y) {
        String durability = String.valueOf(ItemUtils.durabilityOf(item));
        int length = font.width(durability);
        int textHeight = font.lineHeight;
        graphics.text(font, durability, x - 3 - length, y + textHeight / 2, TEXT_COLOR);
    }

    private static void drawEquipmentIcon(GuiGraphicsExtractor graphics, ItemStack equipment, int x, int y) {
        graphics.item(equipment, x, y);
        int barWidth = equipment.getBarWidth();
        int barColor = equipment.getBarColor();
        int barX = x + 2;
        int barY = y + 13;
        if (isDurableItems(equipment)) {
            graphics.fill(barX, barY, barX + 13, barY + 2, DURABILITY_BAR_BACKGROUND_COLOR);
        }
        graphics.fill(barX, barY, barX + barWidth, barY + 1, ARGB.opaque(barColor));
    }

    private QuickInventoryInfoHud() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
