package com.corn.callofthecorn.init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.blocks.CornBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class CornBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
	//public static final RegistryObject<Block> CORN_SEED = BLOCKS.register("corn_seed", () -> new CornBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS)));
	public static final RegistryObject<Block> CORN_SEED = BLOCKS.register("corn_seed", () -> new CornBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE)));


}