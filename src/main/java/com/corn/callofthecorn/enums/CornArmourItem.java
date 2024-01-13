package com.corn.callofthecorn.enums;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

public abstract class CornArmourItem extends ArmorItem implements IForgeItem {

    public CornArmourItem(ArmorMaterial material, Type armourType, Properties properties) {
        super(material, armourType, properties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        Inventory inv = player.getInventory();
        int vanillaIndex = slotIndex;
        if (slotIndex >= inv.items.size()) {
            vanillaIndex = slotIndex - inv.items.size();
            if (vanillaIndex >= inv.armor.size()) {
                vanillaIndex -= inv.armor.size();
            } else {
                this.doArmourTick(stack, level, player);
            }
        }
        stack.inventoryTick(level, player, vanillaIndex, selectedIndex == vanillaIndex);
    }

    public abstract void doArmourTick(ItemStack stack, Level world, Player player);
}
