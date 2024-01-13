package com.corn.callofthecorn.items.armour;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class CalcStack {

    public int CalcCornMetal(Player player){

        int Setbonus=0;
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CornMetalArmourItem) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CornMetalArmourItem) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CornMetalArmourItem) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CornMetalArmourItem) Setbonus=Setbonus+1;


        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;

        return Setbonus;
    }


    public int CalcMaize(Player player){

        int Setbonus=0;

        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof MazieriteArmourItem) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof MazieriteArmourItem) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof MazieriteArmourItem) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof MazieriteArmourItem) Setbonus=Setbonus+1;

        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;

        return Setbonus;
    }

    public int CalcKernal(Player player){

        int Setbonus=0;

        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;

        return Setbonus;
    }
}
