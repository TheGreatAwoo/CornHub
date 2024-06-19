package com.corn.callofthecorn.init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.blocks.CornBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class CornBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MOD_ID);
	public static final Supplier<Block> CORN_SEED = BLOCKS.register("corn_seed", () -> new CornBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE)));


}