package com.corn.callofthecorn.items.armour;

import com.corn.callofthecorn.Main;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.IItemExtension;

public abstract class CornArmourItem extends ArmorItem implements IItemExtension {

    public CornArmourItem(ArmorMaterial material, Type armourType, Properties properties) {
        super(material, armourType, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotIndex, boolean isSelected) {
        if (slotIndex >= 36 && slotIndex <= 39) {
            doArmourTick(stack, level, entity);
        }
    }

    // By 1.21 this method will be removed, simply delete as inventoryTick will work properly by then.
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player entity) {
        doArmourTick(stack, world, entity);
    }

    public abstract void doArmourTick(ItemStack stack, Level world, Entity entity);
}
