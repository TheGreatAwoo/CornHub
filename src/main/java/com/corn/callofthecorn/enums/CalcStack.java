package com.corn.callofthecorn.enums;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.extensions.IForgeItem;

public class CalcStack {

    public int CalcCornMetal(Player player){

        int Setbonus=0;
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Cornmetal) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Cornmetal) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Cornmetal) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Cornmetal) Setbonus=Setbonus+1;


        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof PumpCrown) Setbonus=Setbonus+1;

        return Setbonus;
    }


    public int CalcMaize(Player player){

        int Setbonus=0;

        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Mazierite) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Mazierite) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Mazierite) Setbonus=Setbonus+1;
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Mazierite) Setbonus=Setbonus+1;

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
