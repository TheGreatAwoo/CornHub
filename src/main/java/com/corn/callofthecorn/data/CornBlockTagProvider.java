package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class CornBlockTagProvider extends BlockTagsProvider {


    public CornBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Main.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}