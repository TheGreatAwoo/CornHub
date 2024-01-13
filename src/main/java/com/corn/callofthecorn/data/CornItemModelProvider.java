package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Init.ItemInit;
import com.corn.callofthecorn.Main;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class CornItemModelProvider extends ItemModelProvider {

    public CornItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Item> handheldItems = new HashSet();
        Set<Item> bowItems = new HashSet();
        for(RegistryObject<Item> registryObject : ItemInit.HANDHELD_ITEMS) {
            handheldItems.add(registryObject.get());
        }
        for(RegistryObject<Item> registryObject : ItemInit.BOW_ITEMS) {
            bowItems.add(registryObject.get());
        }
        for(RegistryObject<Item> regOb : ItemInit.ITEMS.getEntries()) {
            Item item = regOb.get();
            if(item instanceof BlockItem) continue;
            String name = ForgeRegistries.ITEMS.getKey(item).getPath();
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