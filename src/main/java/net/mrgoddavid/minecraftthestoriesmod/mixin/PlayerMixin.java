package net.mrgoddavid.minecraftthestoriesmod.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.thirst.ThirstManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
@Mixin(Player.class)
public class PlayerMixin implements ThirstHolder {

    @Unique
    private final ThirstManager mts$thirstManager = new ThirstManager();

    @Override
    public ThirstManager mts$getThirstManager() {
        return this.mts$thirstManager;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void mts$saveThirstData(ValueOutput output, CallbackInfo ci) {
        ThirstManager thirstManager = ((ThirstHolder) this).mts$getThirstManager();

        output.putInt("mts_thirst", thirstManager.getThirst());
        output.putFloat("mts_thirst_exhaustion", thirstManager.getExhaustion());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void mts$loadThirstData(ValueInput input, CallbackInfo ci) {
        int mtsThirst = input.getIntOr("mts_thirst", ThirstManager.MAX_THIRST);
        float mtsThirstExhaustion = input.getFloatOr("mts_thirst_exhaustion", 0.0F);
        this.mts$thirstManager.setThirst(mtsThirst);
        this.mts$thirstManager.setExhaustion(mtsThirstExhaustion);
    }
}
