package com.corn.callofthecorn.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CustomHoeItem extends HoeItem {

    public CustomHoeItem(Tier p_41336_, int p_41337_, float p_41338_, Properties p_41339_) {

        super(p_41336_, p_41337_, p_41338_, p_41339_);

    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }



}

