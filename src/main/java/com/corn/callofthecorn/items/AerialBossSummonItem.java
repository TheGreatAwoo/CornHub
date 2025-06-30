package com.corn.callofthecorn.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class AerialBossSummonItem extends BossSummonItem {

    public AerialBossSummonItem(Supplier<EntityType<? extends Mob>> typeSupplier, Properties properties) {
        super(typeSupplier, 0,0,0,0,  properties);
    }

    @Override
    public BlockPos trySpawn(ServerLevel level, BlockPos centrePos) {
        BlockPos pos = centrePos.atY(level.getMaxY() + 10);
        if(typeSupplier.get().spawn(level, (ItemStack) null, null, pos, EntitySpawnReason.SPAWN_ITEM_USE, true, false) != null) {
            return pos;
        }
        return null;
    }

}
