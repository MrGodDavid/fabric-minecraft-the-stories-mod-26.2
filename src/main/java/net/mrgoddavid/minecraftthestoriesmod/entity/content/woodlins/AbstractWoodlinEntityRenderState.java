package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import com.geckolib.constant.dataticket.DataTicket;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.mrgoddavid.minecraftthestoriesmod.entity.AbstractMtsEntityRenderState;

import java.util.Map;
import java.util.UUID;

/**
 * @author Mr. GodDavid
 * @since 11/5/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AbstractWoodlinEntityRenderState extends AbstractMtsEntityRenderState {

    public final AnimationState enchantingAnimationState = new AnimationState();
    public final AnimationState disenchantingAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    public final AnimationState abilityUseAnimationState = new AnimationState();
    public boolean enchanting = false;
    public UUID entityUUID;
    public int eyeColor;
    public boolean isAlerting; // tame + isDefending.
    public boolean isWarning; // tame + !isDefending.

    @Override
    public Map<DataTicket<?>, Object> getDataMap() {
        return Map.of();
    }
}
