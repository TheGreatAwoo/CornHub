package com.corn.callofthecorn.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.extensions.IForgeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AerialBossSummonItem extends BossSummonItem {

    public AerialBossSummonItem(Supplier<EntityType<? extends Mob>> typeSupplier, Properties properties) {
        super(typeSupplier, 0,0,0,0,  properties);
    }

    @Override
    public BlockPos trySpawn(ServerLevel level, BlockPos centrePos) {
        BlockPos pos = centrePos.atY(level.getMaxBuildHeight() + 10);
        if(typeSupplier.get().spawn(level, (ItemStack) null, null, pos, MobSpawnType.SPAWN_EGG, true, false) != null) {
            return pos;
        }
        return null;
    }

}
