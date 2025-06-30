package com.corn.callofthecorn.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;


public class CornBlock extends SugarCaneBlock {
    public CornBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        if (below.canSustainPlant(level, pos.below(), Direction.UP, state).isTrue()) {
            return true;
        }
        return below.is(this) || below.is(BlockTags.DIRT);
    }
}
