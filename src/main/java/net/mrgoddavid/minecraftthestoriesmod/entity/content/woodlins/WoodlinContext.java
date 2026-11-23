package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 11/3/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public final class WoodlinContext {

    public static final byte WOODLIN_ABILITY_USE_RADIUS_SQ = 0;
    public static final byte TAMED_ANIMAL_PANIC_SPEED_MODIFIER = 1;
    public static final byte TAMED_ANIMAL_FOLLOW_OWNER_SPEED_MODIFIER = 2;
    public static final byte TAMED_ANIMAL_FOLLOW_OWNER_START_DISTANCE = 3;
    public static final byte TAMED_ANIMAL_FOLLOW_OWNER_STOP_DISTANCE = 4;
    public static final byte ENTITY_RANDOMLY_LOOK_DISTANCE = 5;
    public static final byte WOODLIN_FLEEING_FORBIDDEN_BIOME_SAFE_DISTANCE = 6;
    public static final byte WOODLIN_FLEEING_FORBIDDEN_BIOME_WALK_SPEED_MODIFIER = 7;
    public static final byte WOODLIN_FLEEING_FORBIDDEN_BIOME_SPRINT_SPEED_MODIFIER = 8;

    private final Map<Byte, Number> contextByteKeyNumberValueMap;

    private WoodlinContext(Builder builder) {
        this.contextByteKeyNumberValueMap = Map.copyOf(builder.contextByteKeyNumberValueMap);
    }

    public int getIntOrThrow(byte contextKey) {
        return Optional.ofNullable(this.contextByteKeyNumberValueMap.get(contextKey)).orElseThrow().intValue();
    }

    public double getDoubleOrThrow(byte contextKey) {
        return Optional.ofNullable(this.contextByteKeyNumberValueMap.get(contextKey)).orElseThrow().doubleValue();
    }

    public float getFloatOrThrow(byte contextKey) {
        return Optional.ofNullable(this.contextByteKeyNumberValueMap.get(contextKey)).orElseThrow().floatValue();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @author Mr. GodDavid
     * @since 11/3/2026
     */
    public static final class Builder {

        private final Map<Byte, Number> contextByteKeyNumberValueMap;

        private Builder() {
            this.contextByteKeyNumberValueMap = Maps.newHashMap();
        }

        public Builder woodlinAbilityUsageRadius(double radius) {
            double radiusSq = radius * radius;
            this.contextByteKeyNumberValueMap.put(WOODLIN_ABILITY_USE_RADIUS_SQ, radiusSq);
            return this;
        }

        public Builder tamedAnimalPanicSpeedModifier(double panicSpeedModifier) {
            this.contextByteKeyNumberValueMap.put(TAMED_ANIMAL_PANIC_SPEED_MODIFIER, panicSpeedModifier);
            return this;
        }

        public Builder tamedAnimalFollowOwnerSpeedModifier(double followOwnerSpeedModifier) {
            this.contextByteKeyNumberValueMap.put(TAMED_ANIMAL_FOLLOW_OWNER_SPEED_MODIFIER, followOwnerSpeedModifier);
            return this;
        }

        public Builder tamedAnimalFollowOwnerStartDistance(double followOwnerStartDistance) {
            this.contextByteKeyNumberValueMap.put(TAMED_ANIMAL_FOLLOW_OWNER_START_DISTANCE, followOwnerStartDistance);
            return this;
        }

        public Builder tamedAnimalFollowOwnerStopDistance(double followOwnerStopDistance) {
            this.contextByteKeyNumberValueMap.put(TAMED_ANIMAL_FOLLOW_OWNER_STOP_DISTANCE, followOwnerStopDistance);
            return this;
        }

        public Builder entityRandomlyLookDistance(double lookDistance) {
            this.contextByteKeyNumberValueMap.put(ENTITY_RANDOMLY_LOOK_DISTANCE, lookDistance);
            return this;
        }

        public Builder woodlinForbiddenBiomeFleeSafeDistance(float maxDistance) {
            this.contextByteKeyNumberValueMap.put(WOODLIN_FLEEING_FORBIDDEN_BIOME_SAFE_DISTANCE,  maxDistance);
            return this;
        }

        public Builder woodlinForbiddenBiomeFleeingWalkSpeedModifier(double fleeingWalkSpeedModifier) {
            this.contextByteKeyNumberValueMap.put(WOODLIN_FLEEING_FORBIDDEN_BIOME_WALK_SPEED_MODIFIER, fleeingWalkSpeedModifier);
            return this;
        }

        public Builder woodlinForbiddenBiomeFleeingSprintSpeedModifier(double fleeingSprintSpeedModifier) {
            this.contextByteKeyNumberValueMap.put(WOODLIN_FLEEING_FORBIDDEN_BIOME_SPRINT_SPEED_MODIFIER, fleeingSprintSpeedModifier);
            return this;
        }

        public WoodlinContext build() {
            return new WoodlinContext(this);
        }
    }
}
