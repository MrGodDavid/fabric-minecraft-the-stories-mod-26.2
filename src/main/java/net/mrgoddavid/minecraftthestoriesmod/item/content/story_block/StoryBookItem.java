package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;

/**
 * @author Mr. GodDavid
 * @since 9/20/2026
 */
public class StoryBookItem extends Item {

    public StoryBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer && itemStack.is(MtsItems.STORY_BOOK)) {
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, inventory, player1) -> new StoryBookMenu(containerId, inventory),
                    Component.translatable("container.minecraft-the-stories.story_book"))
            );
        }

        return InteractionResult.SUCCESS;
    }
}
