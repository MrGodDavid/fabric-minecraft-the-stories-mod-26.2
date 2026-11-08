package net.mrgoddavid.minecraftthestoriesmod.utils.mod.client;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Mr. GodDavid
 * @since 10/21/2026
 */
public final class EntityUtils {

    public static <T extends LivingEntity, S extends LivingEntity> Optional<T> getNearestLivingEntity(ServerLevel serverLevel, S self, Class<T> entityClass, float radius) {
        return getNearestLivingEntity(serverLevel, self, entityClass, _ -> true, radius);
    }

    public static <T extends LivingEntity> boolean containsEnemiesInRegion(ServerLevel serverLevel, LivingEntity center, Class<T> targetClass, float radius) {
        return !getEntitiesInCircularRegion(serverLevel, center, targetClass, entity -> entity instanceof Enemy, radius).isEmpty();
    }

    public static List<BlockPos> getBlockPosInCircle(LivingEntity livingEntity, int radius) {
        final double radiusSq = radius * radius;
        BlockPos center = livingEntity.blockPosition();
        BlockPos minCorner = center.offset(-radius, 0, -radius);
        BlockPos maxCorner = center.offset(radius, 0, radius);
        return BlockPos.betweenClosedStream(minCorner, maxCorner)
                .filter(pos -> {
                    double dx = pos.getX() - center.getX();
                    double dz = pos.getZ() - center.getZ();
                    double distanceSquared = dx * dx + dz * dz;
                    return distanceSquared <= radiusSq;
                })
                .map(BlockPos::immutable)
                .collect(Collectors.toList());
    }

    /**
     * Select the closest living entity (Target) around a living entity (Self).
     *
     * @param serverLevel        the level of the world must be a server level since we are doing this on the server
     *                           side.
     * @param self               the centered living entity. The center of the searching area.
     * @param searchedClass      the class of entity will be searched.
     * @param selectionPredicate rules for filtering the valid target living entities.
     * @param radius             search radius/distance.
     * @param <S>                type of the living entity in the center of the search circle.
     * @param <T>                type of the targeted living entity that is being searched.
     * @return the closets entity that fits all the previous criteria.s
     */
    public static <S extends LivingEntity, T extends LivingEntity> Optional<T> getNearestLivingEntity(ServerLevel serverLevel, S self, Class<T> searchedClass, Predicate<T> selectionPredicate, float radius) {
        return serverLevel.getEntitiesOfClass(searchedClass, self.getBoundingBox().inflate(radius), selectionPredicate)
                .stream()
                .min(Comparator.comparingDouble(entity -> entity.distanceToSqr(self)));
    }

    public static <T extends LivingEntity> List<T> getEntitiesInCircularRegion(ServerLevel serverLevel, LivingEntity center, Class<T> targetClass, float radius) {
        return getEntitiesInCircularRegion(serverLevel, center, targetClass, _ -> true, radius);
    }

    public static <T extends LivingEntity> List<T> getEntitiesInCircularRegion(ServerLevel serverLevel, LivingEntity center, Class<T> targetClass, Predicate<T> entitySelector, float radius) {
        float radiusSq = radius * radius;
        AABB searchBox = center.getBoundingBox().inflate(radius);
        return serverLevel.getEntitiesOfClass(targetClass, searchBox, entity -> (center.distanceToSqr(entity) <= radiusSq) && entitySelector.test(entity));
    }

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
