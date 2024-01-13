package com.corn.callofthecorn.items;

import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Objects;

public class BossSummonItem extends SpawnEggItem {
private final int type;

    public BossSummonItem(EntityType<? extends Mob> p_43207_, int p_43208_, int p_43209_, Properties p_43210_, int type) {
        super(p_43207_, p_43208_, p_43209_, p_43210_);
        this.type = type;
    }

@Override
public InteractionResult useOn(UseOnContext p_43223_) {
    Level level = p_43223_.getLevel();
    if (!(level instanceof ServerLevel)) {
        return InteractionResult.SUCCESS;
    } else {
        ItemStack itemstack = p_43223_.getItemInHand();
        BlockPos blockpos = p_43223_.getClickedPos();
        Direction direction = p_43223_.getClickedFace();
        BlockState blockstate = level.getBlockState(blockpos);
        BlockPos blockpos1;
        if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
            blockpos1 = blockpos;
        } else {
            blockpos1 = blockpos.relative(direction);
        }

        EntityType<?> entityType = null;
        if (type == 1) {
            entityType = CornMobs.SCARECROW.get();
        }

        if(type ==2){
            entityType = CornMobs.FARMHAND.get();
        }

        if(type ==3){
          entityType = CornMobs.HARVESTER.get() ;
        }

        if(type ==4){
            entityType = CornMobs.HARVEST.get();
        }

        if(type ==5){
            entityType = CornMobs.CROPWATCHER.get();
        }

        if (blockstate.is(Blocks.SPAWNER)) {
            BlockEntity blockentity = level.getBlockEntity(blockpos);
            if (blockentity instanceof SpawnerBlockEntity) {
                SpawnerBlockEntity spawnerBlockEntity = (SpawnerBlockEntity)blockentity;
                spawnerBlockEntity.setEntityId(entityType, level.getRandom());
                blockentity.setChanged();
                level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                level.gameEvent(p_43223_.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
                itemstack.shrink(1);
                return InteractionResult.CONSUME;
            }
        }

        if (entityType.spawn((ServerLevel)level, itemstack, p_43223_.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
            itemstack.shrink(1);
            level.gameEvent(p_43223_.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
        }
        return InteractionResult.CONSUME;
    }
}


}
