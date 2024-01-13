package com.corn.callofthecorn;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class FleetfootEnchantment extends Enchantment {


    public FleetfootEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);

    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

//     public static void onEntityMoved(LivingEntity p_45019_, Level p_45020_, BlockPos p_45021_, int p_45022_) {
//        if (p_45019_.isOnGround()) {
//
//        }
//    }


    @Override
    public boolean checkCompatibility(Enchantment p_45024_) {
        return super.checkCompatibility(p_45024_) && p_45024_ != Enchantments.DEPTH_STRIDER && p_45024_ != Enchantments.FROST_WALKER;
    }








}
