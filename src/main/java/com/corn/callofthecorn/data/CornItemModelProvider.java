package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class CornItemModelProvider extends ItemModelProvider {

    public CornItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Item> handheldItems = new HashSet();
        Set<Item> bowItems = new HashSet();
        for(Supplier<Item> registryObject : CornItems.HANDHELD_ITEMS) {
            handheldItems.add(registryObject.get());
        }
        for(Supplier<Item> registryObject : CornItems.BOW_ITEMS) {
            bowItems.add(registryObject.get());
        }
        for(DeferredHolder<Item, ? extends Item> regOb : CornItems.ITEMS.getEntries()) {
            Item item = regOb.get();
            if(item instanceof BlockItem) continue;
            String name = BuiltInRegistries.ITEM.getKey(item).getPath();
            if(handheldItems.contains(item)) {
                singleTexture(name, new ResourceLocation("item/handheld"), "layer0", modLoc("item/" + name));
            } else if(bowItems.contains(item)) {
                singleTexture(name, new ResourceLocation("item/handheld"), "layer0", modLoc("item/" + name)); // TODO fix bows
            } else {
                singleTexture(name, new ResourceLocation("item/generated"), "layer0", modLoc("item/" + name));
            }
        }
    }
}