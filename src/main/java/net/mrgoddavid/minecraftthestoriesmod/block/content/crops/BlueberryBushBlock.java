package net.mrgoddavid.minecraftthestoriesmod.block.content.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;

/**
 * @author Mr. GodDavid
 * @since 9/14/2026
 */
public class BlueberryBushBlock extends SweetBerryBushBlock {

    public static final int AGE_NOT_FULLY_GROWN = MAX_AGE - 1;

    public BlueberryBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(MtsItems.BLUEBERRY);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        boolean isGrown = age == MAX_AGE;
        if (age > 1) {
            int count = 1 + level.getRandom().nextInt(2);
            popResource(level, pos, new ItemStack(notFullyGrown(age) ? MtsItems.RAW_BLUEBERRY : MtsItems.BLUEBERRY, notFullyGrown(age) ? 1 : count + (isGrown ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            BlockState newState = state.setValue(AGE, 1);
            level.setBlock(pos, newState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    private boolean notFullyGrown(int age) {
        return age == AGE_NOT_FULLY_GROWN;
    }
}
