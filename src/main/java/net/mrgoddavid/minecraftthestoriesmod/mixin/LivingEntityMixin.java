package net.mrgoddavid.minecraftthestoriesmod.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;
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

    @Unique
    private int mts$airSupplyBeforeTick;

    @Unique
    private boolean mts$preserveAirSupply;

    @Inject(method = "baseTick", at = @At("HEAD"))
    private void mts$captureAirSupply(CallbackInfo ci) {
        this.mts$preserveAirSupply = false;
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof ServerPlayer player
                && EntityUtils.isPlayerInSurvival(player)
                && player.level().dimension() == Level.END) {
            this.mts$airSupplyBeforeTick = player.getAirSupply();
            this.mts$preserveAirSupply = true;
        }
    }

    @Inject(method = "baseTick", at = @At("TAIL"))
    private void mts$restoreAirSupply(CallbackInfo ci) {
        if (this.mts$preserveAirSupply) {
            LivingEntity livingEntity = (LivingEntity) (Object) this;
            if (livingEntity instanceof ServerPlayer player) {
                player.setAirSupply(this.mts$airSupplyBeforeTick);
                this.mts$preserveAirSupply = false;
            }
        }
    }

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
