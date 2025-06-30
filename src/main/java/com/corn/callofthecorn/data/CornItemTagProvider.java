package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class CornItemTagProvider extends ItemTagsProvider {
    public CornItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> tp) {
        super(output, provider, tp, Main.MOD_ID);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(CornTags.Items.CORNMETAL_SET_ITEMS).add(CornItems.CORNMETALHEAD.get());
        this.tag(CornTags.Items.CORNMETAL_SET_ITEMS).add(CornItems.CORNMETALCHEST.get());
        this.tag(CornTags.Items.CORNMETAL_SET_ITEMS).add(CornItems.CORNMETALPANTS.get());
        this.tag(CornTags.Items.CORNMETAL_SET_ITEMS).add(CornItems.CORNMETALFEET.get());
        this.tag(CornTags.Items.CORNMETAL_SET_ITEMS).add(CornItems.PUMPKINGSCROWN.get());
        this.tag(CornTags.Items.CORNMETAL_SET_ITEMS).add(CornItems.CROWSFOOT.get());

        this.tag(CornTags.Items.MAIZERITE_SET_ITEMS).add(CornItems.MAIZERITEHEAD.get());
        this.tag(CornTags.Items.MAIZERITE_SET_ITEMS).add(CornItems.MAIZERITECHEST.get());
        this.tag(CornTags.Items.MAIZERITE_SET_ITEMS).add(CornItems.MAIZERITEPANTS.get());
        this.tag(CornTags.Items.MAIZERITE_SET_ITEMS).add(CornItems.MAIZERITEFEET.get());
        this.tag(CornTags.Items.MAIZERITE_SET_ITEMS).add(CornItems.PUMPKINGSCROWN.get());

        this.tag(CornTags.Items.KERNEL_SET_ITEMS).add(CornItems.PUMPKINGSCROWN.get());

        this.tag(ItemTags.SWORDS).add(CornItems.CORNMETALSWORD.get());
        this.tag(ItemTags.SWORDS).add(CornItems.MAIZERITESWORD.get());

        this.tag(ItemTags.PICKAXES).add(CornItems.CORNMETALPICK.get());
        this.tag(ItemTags.PICKAXES).add(CornItems.MAIZERITEPICK.get());

        this.tag(ItemTags.SHOVELS).add(CornItems.CORNMETALSHOVEL.get());
        this.tag(ItemTags.SHOVELS).add(CornItems.MAIZERITESHOVEL.get());

        this.tag(ItemTags.AXES).add(CornItems.CORNMETALAXE.get());
        this.tag(ItemTags.AXES).add(CornItems.MAIZERITEAXE.get());
        this.tag(ItemTags.AXES).add(CornItems.KERNAL.get());

        this.tag(ItemTags.HOES).add(CornItems.CORNMETALHOE.get());
        this.tag(ItemTags.HOES).add(CornItems.MAIZERITEHOE.get());

        this.tag(CornTags.Items.NECKLACES).add(CornItems.CROWSFOOT.get());

    }
}