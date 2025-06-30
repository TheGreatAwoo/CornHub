package com.corn.callofthecorn.items.armour;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class PumpCrown extends Item {

    public PumpCrown(Properties p_40388_) {
        super(p_40388_);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        if (entity instanceof LivingEntity livingEntity && slot != null  && slot.isArmor()) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1), livingEntity);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.HASTE, 1), livingEntity);
        }
    }

}
