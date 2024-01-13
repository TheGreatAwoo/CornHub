package com.corn.callofthecorn.enums;

import com.corn.callofthecorn.Init.MobInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Objects;

public class Egg extends SpawnEggItem {
int Types;

    public Egg(EntityType<? extends Mob> p_43207_, int p_43208_, int p_43209_, Properties p_43210_,int Type) {
        super(p_43207_, p_43208_, p_43209_, p_43210_);
        Types=Type;
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

        EntityType<?> entitytype = null;
        if (Types == 1) {

            entitytype = MobInit.SCARECROW.get();



//        if (blockstate.is(Blocks.SPAWNER)) {
//
//            BlockEntity blockentity = level.getBlockEntity(blockpos);
//
//            if (blockentity instanceof SpawnerBlockEntity) {
//                BaseSpawner basespawner = ((SpawnerBlockEntity) blockentity).getSpawner();
//                basespawner.setEntityId(entitytype);
//                blockentity.setChanged();
//                level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
//                itemstack.shrink(1);
//                return InteractionResult.CONSUME;
//            }
//        }

        }

        if(Types==2){
            entitytype = MobInit.FARMHAND.get();}

        if(Types==3){

          entitytype = MobInit.HARVESTER.get() ;

        }

        if(Types==4){
            entitytype = MobInit.HARVEST.get();}

        if(Types==5){
            entitytype = MobInit.CROPWATCHER.get();}


        if (blockstate.is(Blocks.SPAWNER)) {

            BlockEntity blockentity = level.getBlockEntity(blockpos);

            if (blockentity instanceof SpawnerBlockEntity) {
                BaseSpawner basespawner = ((SpawnerBlockEntity) blockentity).getSpawner();
                basespawner.setEntityId(entitytype);
                blockentity.setChanged();
                level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                itemstack.shrink(1);
                return InteractionResult.CONSUME;
            }
        }





        if (entitytype.spawn((ServerLevel)level, itemstack, p_43223_.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
            itemstack.shrink(1);
            level.gameEvent(p_43223_.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
        }
        return InteractionResult.CONSUME;
    }
}


}
