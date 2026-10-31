package net.mrgoddavid.minecraftthestoriesmod.item.content;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.component.MtsDataComponentTypes;
import net.mrgoddavid.minecraftthestoriesmod.event.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.contents.ThirstNetworking;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 10/2/2026
 */
public class DrinkableItem extends BlockItem {

    public DrinkableItem(int thirstRestoration, final Block block, final Item.Properties properties) {
        super(block, properties.component(MtsDataComponentTypes.THIRST_RESTORATION, thirstRestoration));
    }

    public DrinkableItem(int thirstRestoration, Properties properties) {
        this(thirstRestoration, Blocks.AIR, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player.isCreative()) {
            player.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        boolean isHungerFull = player.getFoodData().getFoodLevel() >= Constants.Universal.MAX_SATURATION;
        boolean isThirstFull = thirstManager != null && thirstManager.isThirstFull();
        if (isHungerFull && isThirstFull) {
            return InteractionResult.FAIL;
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }
}
