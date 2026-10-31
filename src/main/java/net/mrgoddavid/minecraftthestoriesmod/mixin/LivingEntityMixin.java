package net.mrgoddavid.minecraftthestoriesmod.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.common.ThirstUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 10/30/2026
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract ItemStack getUseItem();

    @Unique
    private ItemStack mts$consumedItemStack;

    @Inject(method = "completeUsingItem", at = @At("HEAD"))
    private void mts$headCompleteUseItemStack(CallbackInfo ci) {
        this.mts$consumedItemStack = this.getUseItem().copy();
    }

    @Inject(method = "completeUsingItem", at = @At("TAIL"))
    private void mts$restoreThirst(CallbackInfo ci) {
        if (this.mts$consumedItemStack == null) return;
        ThirstUtils.restoreThirst((LivingEntity) (Object) this, this.mts$consumedItemStack);
        this.mts$consumedItemStack = null;
    }
}
