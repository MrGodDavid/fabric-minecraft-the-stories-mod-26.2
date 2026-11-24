package net.mrgoddavid.minecraftthestoriesmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Mr. GodDavid
 * @since 11/23/2026
 */
@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public abstract boolean isEyeInFluid(TagKey<Fluid> type);

    @ModifyReturnValue(method = "isInLiquid", at = @At("RETURN"))
    private boolean mts$isInLiquid(boolean original) {
        return original || this.isEyeInFluid(MtsTags.Fluids.ENRICHER_WASTE) || this.isEyeInFluid(MtsTags.Fluids.BLUE_FUEL);
    }

}
