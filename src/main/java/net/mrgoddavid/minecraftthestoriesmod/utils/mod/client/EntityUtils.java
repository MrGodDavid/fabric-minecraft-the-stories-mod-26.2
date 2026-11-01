package net.mrgoddavid.minecraftthestoriesmod.utils.mod.client;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import org.jspecify.annotations.Nullable;

import java.util.function.Predicate;

/**
 * @author Mr. GodDavid
 * @since 10/21/2026
 */
public final class EntityUtils {

    public static @Nullable BlockPos getNearestBlockPos(LivingEntity entity, Predicate<BlockState> blockStatePredicate, int searchRadius) {
        return getNearestBlockPos(entity, blockStatePredicate, searchRadius, searchRadius);
    }

    public static @Nullable BlockPos getNearestBlockPos(LivingEntity entity, Predicate<BlockState> blockStatePredicate, int xSearchRadius, int zSearchRadius) {
        BlockPos mobPos = entity.blockPosition();
        BlockPos nearestBlockPos = null;
        double nearestDistanceSq = Double.MAX_VALUE;

        for (int x = -xSearchRadius; x <= xSearchRadius; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -zSearchRadius; z <= zSearchRadius; z++) {
                    BlockPos checkPos = mobPos.offset(x, y, z);
                    BlockState checkState = entity.level().getBlockState(checkPos);

                    if (!blockStatePredicate.test(checkState)) continue;
                    double distanceSq = mobPos.distSqr(checkPos);

                    if (distanceSq < nearestDistanceSq) {
                        nearestBlockPos = checkPos;
                        nearestDistanceSq = distanceSq;
                    }
                }
            }
        }
        return nearestBlockPos;
    }

    public static double distanceSqBetweenMobAndBlockPos(LivingEntity mob, BlockPos pos) {
        double x = mob.getX() - pos.getX();
        double y = mob.getY() - pos.getY();
        double z = mob.getZ() - pos.getZ();
        return x * x + y * y + z * z;
    }

    private EntityUtils() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
