package net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.Level;

public class EnderExalterBlockRenderState extends BlockEntityRenderState {

    public Level level;
    public float firstSlotRotation;
    public float secondSlotRotation;

    public float firstSlotYPos;
    public float secondSlotYPos;

    final ItemStackRenderState firstSlotItemStackRenderState = new ItemStackRenderState();
    final ItemStackRenderState secondSlotItemStackRenderState = new ItemStackRenderState();
}
