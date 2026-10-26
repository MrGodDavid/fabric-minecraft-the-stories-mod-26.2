package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
public class OakieHealingBallEntity extends PathfinderMob {

    public OakieHealingBallEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier createCubeAttributes() {
        return null;
    }
}
