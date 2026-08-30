package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since unknown date in August 2026.
 */
public class MineBlockWithToolTrigger extends SimpleCriterionTrigger<MineBlockWithToolTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockState state, ItemStack tool) {
        this.trigger(player, instance -> instance.matches(state, tool));
    }

    public record TriggerInstance(Optional<HolderSet<Block>> blocks, Optional<ItemPredicate> tool) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC =
                RecordCodecBuilder.create(instance -> instance.group(
                        RegistryCodecs.homogeneousList(Registries.BLOCK).optionalFieldOf("blocks").forGetter(TriggerInstance::blocks),
                        ItemPredicate.CODEC.optionalFieldOf("tool").forGetter(TriggerInstance::tool)
                ).apply(instance, TriggerInstance::new));

        public boolean matches(BlockState state, ItemStack toolStack) {
            boolean blockMatches = blocks.isEmpty() || blocks.get().contains(state.getBlock().builtInRegistryHolder());
            boolean toolMatches = tool.isEmpty() || tool.get().test(toolStack);
            return blockMatches && toolMatches;
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return Optional.empty();
        }
    }
}
