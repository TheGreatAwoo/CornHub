package com.corn.callofthecorn.enums;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CornTab extends CreativeModeTab {


    public CornTab( String name) {
        super( name);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.CARVED_PUMPKIN);
    }

    public static final CreativeModeTab TAB_Corn = new CornTab( "Corn") ;


}
