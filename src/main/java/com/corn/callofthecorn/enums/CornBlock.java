package com.corn.callofthecorn.enums;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;


public class CornBlock extends SugarCaneBlock {
    public CornBlock(Properties p_57168_) {
        super(p_57168_);
    }

    @Override
    public boolean canSurvive(BlockState p_57175_, LevelReader p_57176_, BlockPos p_57177_) {

        BlockState soil = p_57176_.getBlockState(p_57177_.below());
        if (soil.canSustainPlant(p_57176_, p_57177_.below(), Direction.UP, this)) return true;
        BlockState blockstate = p_57176_.getBlockState(p_57177_.below());
        if (blockstate.is(this)) {
            return true;

        } else {
            if (blockstate.is(BlockTags.DIRT)) {
                BlockPos blockpos = p_57177_.below();
                        return true;
                    }
                }
            return false;
        }

}
