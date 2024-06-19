package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.init.CornBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

public class CornBlockStateProvider  extends BlockStateProvider {

    public CornBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Example:
        //simpleBlock(BlocksInit.THE_BLOCK.get());


        // Any block that looks different in the inventory goes in here
        List<Block> differentItemTexture = new Vector<>();
        differentItemTexture.add(CornBlocks.CORN_SEED.get());

        // Add the item models to all registered blocks, except the exceptions above
        for(Block block : CornBlocks.BLOCKS.getEntries().stream().map(Supplier::get).toList()) {
            String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
            if(differentItemTexture.contains(block)) {
                continue;
            }
            simpleBlockItem(block, models().getExistingFile(modLoc("block/" + name)));
        }
    }

}
