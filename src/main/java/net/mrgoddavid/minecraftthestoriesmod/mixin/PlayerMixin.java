package net.mrgoddavid.minecraftthestoriesmod.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.item.content.CudgelItem;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.progress.StoryBookProgress;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.progress.StoryBookProgressHolder;
import net.mrgoddavid.minecraftthestoriesmod.event.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.sound.MtsSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
@Mixin(Player.class)
public abstract class PlayerMixin implements ThirstHolder, StoryBookProgressHolder {

    @Unique
    private final ThirstManager mts$thirstManager = new ThirstManager();
    @Unique
    private final StoryBookProgress mts$getStoryBookProgress = new StoryBookProgress();

    // ====================================================================================================
    //                                      CUSTOM WEAPON HIT SOUNDS
    // ====================================================================================================
    // Shadow methods.
    @Shadow
    protected abstract void playServerSideSound(final SoundEvent sound);
    @Shadow
    public abstract void crit(final Entity entity);
    @Shadow
    public abstract void magicCrit(Entity entity);

    @Inject(method = "attackVisualEffects", at = @At(value = "HEAD"), cancellable = true)
    private void mts$replaceCudgelBonkSound(Entity entity, boolean criticalAttack, boolean sweepAttack, boolean fullStrengthAttack, boolean stabAttack, float magicBoost, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if (player.getMainHandItem().getItem() instanceof CudgelItem) {
            if (criticalAttack) {
                this.playServerSideSound(MtsSounds.BONK);
                this.crit(entity);
            }
            if (!criticalAttack && !sweepAttack && !stabAttack) {
                this.playServerSideSound(MtsSounds.BONK);
            }
            if (magicBoost > 0.0F) {
                this.magicCrit(entity);
            }
            ci.cancel();
        }
    }

    // ====================================================================================================
    //                                      THIRST SYSTEM MECHANISM
    // ====================================================================================================
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

    // ====================================================================================================
    //                                    STORY BOOK SYSTEM MECHANISM
    // ====================================================================================================
    @Override
    public StoryBookProgress mts$getStoryBookProgress() {
        return this.mts$getStoryBookProgress;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void mts$saveStorybookProgress(ValueOutput output, CallbackInfo ci) {
        this.mts$getStoryBookProgress.save(output);

    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void mts$loadStorybookProgress(ValueInput input, CallbackInfo ci) {
        this.mts$getStoryBookProgress.load(input);
    }
}
