package net.mrgoddavid.minecraftthestoriesmod.mixin;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.mrgoddavid.minecraftthestoriesmod.event.content.StructureEnterCallback;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

/**
 * @author Mr. GodDavid
 * @since 11/23/2026
 */
@SuppressWarnings("resource")
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Shadow
    public abstract Level level();

    @Unique
    private final Set<Holder<Structure>> currentStructures = Sets.newHashSet();
    @Unique
    private final Timer cooldownTimer = new Timer(20);

    @Inject(method = "tick", at = @At("TAIL"))
    private void mts$onTickCheckStructures(CallbackInfo ci) {
        if (this.cooldownTimer.pass(() -> true)) {
            this.cooldownTimer.reset();

            ServerPlayer serverPlayer = (ServerPlayer) (Object) this;
            ServerLevel serverLevel = serverPlayer.level();
            BlockPos playerPos = serverPlayer.blockPosition();
            StructureManager structureManager = serverLevel.structureManager();
            Registry<Structure> structureRegistry = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE);
            Set<Holder<Structure>> activeStructures = Sets.newHashSet();

            for (Structure structure : structureRegistry) {
                StructureStart start = structureManager.getStructureWithPieceAt(playerPos, structure);
                if (start != null && start.isValid()) {
                    structureRegistry.getResourceKey(structure)
                            .flatMap(structureRegistry::get)
                            .ifPresent(activeStructures::add);
                }
            }

            for (Holder<Structure> entered : activeStructures) {
                if (!this.currentStructures.contains(entered)) {
                    StructureEnterCallback.EVENT.invoker().onStructureEnter(serverPlayer, entered);
                }
            }

            this.currentStructures.clear();
            this.currentStructures.addAll(activeStructures);
        }
    }
}
