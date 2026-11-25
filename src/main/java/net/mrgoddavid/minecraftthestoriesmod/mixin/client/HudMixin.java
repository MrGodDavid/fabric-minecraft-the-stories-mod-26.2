package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Mr. GodDavid
 * @since 11/24/2026
 */
@Mixin(Hud.class)
public class HudMixin {

    @ModifyExpressionValue(method = "extractAirBubbles", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean mts$modifyAirBubbleRenderCriteria(
            final boolean original,
            final GuiGraphicsExtractor graphics,
            final Player player,
            final int vehicleHearts,
            int yLineAir,
            final int xRight
    ) {
        boolean playerInTheEnd = EntityUtils.isPlayerInSurvival(player) && player.level().dimension() == Level.END;
        return original || playerInTheEnd;
    }
}
