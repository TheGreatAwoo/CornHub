package com.corn.callofthecorn.items.armour;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicInteger;

public class CalcStack {

    public static int getSetBonus(Player player, TagKey<Item> tag){
        AtomicInteger setBonus = new AtomicInteger(0); // atomic for the lambda below
        for(ItemStack i : player.getArmorSlots()) {
            if(i.is(tag)) {
                setBonus.getAndIncrement();
            }
        }

        CuriosApi.getCuriosInventory(player).ifPresent((itemHandler)-> {
            int slots = itemHandler.getEquippedCurios().getSlots();
            for(int i = 0; i < slots; i++) {
                ItemStack stack = itemHandler.getEquippedCurios().getStackInSlot(i);
                if(stack.is(tag)) {
                    setBonus.getAndIncrement();
                }
            }
        });

        return setBonus.get();
    }
}
