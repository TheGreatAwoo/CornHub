package com.corn.callofthecorn.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.extensions.IItemExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BossSummonItem extends Item implements IItemExtension {
    protected final Supplier<EntityType<? extends Mob>> typeSupplier;
    private final int minSpawnLocations;
    private final double minDistance;
    private final int dxz;
    private final int dy;

    public BossSummonItem(Supplier<EntityType<? extends Mob>> typeSupplier, int minSpawnLocations, double minSpawnDistance, int horizontalSpawnDistance, int verticalSpawnDistance, Properties properties) {
        super(properties);
        this.typeSupplier = typeSupplier;
        this.minSpawnLocations = minSpawnLocations;
        this.minDistance = minSpawnDistance;
        this.dxz = horizontalSpawnDistance;
        this.dy = verticalSpawnDistance;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!(level instanceof ServerLevel)) {
            return InteractionResultHolder.success(stack);
        } else {
            BlockPos spawnPos = trySpawn((ServerLevel) level, player.getOnPos());
            if (spawnPos != null) {
                stack.shrink(1);
                level.gameEvent(player, GameEvent.ENTITY_PLACE, spawnPos);
            }
            return InteractionResultHolder.consume(stack);
        }
    }

    public BlockPos trySpawn(ServerLevel level, BlockPos centrePos) {
        List<BlockPos> positions = new ArrayList<>();
        BlockPos.betweenClosedStream(centrePos.offset(-dxz,-dy,-dxz), centrePos.offset(dxz, dy, dxz)).forEach(pos -> {
            boolean goodAbove = !level.getBlockState(pos.above()).isSuffocating(level, pos.above());
            boolean goodHere = !level.getBlockState(pos).isSuffocating(level, pos);
            boolean goodBelow = level.getBlockState(pos.below()).isSolid();
            boolean notTooClose = pos.distSqr(centrePos) > minDistance*minDistance;
            if(goodBelow && goodHere && goodAbove && notTooClose) {
                positions.add(pos.immutable()); // Can't just add pos or filter the stream because pos is the MutableBlockPos cursor object
            }
        });
        if(positions.size() < minSpawnLocations) { // They like the open air
            return null;
        }
        int idx = level.random.nextInt(positions.size());
        BlockPos pos = positions.get(idx);
        if(typeSupplier.get().spawn(level, (ItemStack) null, null, pos, MobSpawnType.SPAWN_EGG, true, false) != null) {
            return pos;
        }
        return null;
    }


}
