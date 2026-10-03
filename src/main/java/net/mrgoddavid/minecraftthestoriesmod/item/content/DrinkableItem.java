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
import net.mrgoddavid.minecraftthestoriesmod.event.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.contents.ThirstNetworking;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

/**
 * @author Mr. GodDavid
 * @since 10/2/2026
 */
public class DrinkableItem extends BlockItem {

    private final int thirstRestoration;

    public DrinkableItem(int thirstRestoration, final Block block, final Item.Properties properties) {
        super(block, properties);
        this.thirstRestoration = thirstRestoration;
    }

    public DrinkableItem(int thirstRestoration, Properties properties) {
        this(thirstRestoration, Blocks.AIR, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof ServerPlayer serverPlayer) {
            ThirstManager thirstManager = ((ThirstHolder) serverPlayer).mts$getThirstManager();
            if (thirstManager != null && thirstManager.restoreThirst(thirstRestoration)) {
                ThirstNetworking.sync(serverPlayer, thirstManager);
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        if (player.getFoodData().getFoodLevel() >= Constants.Universal.MAX_SATURATION && thirstManager.isThirstFull()) {
            return InteractionResult.FAIL;
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }
}
