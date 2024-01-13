package com.corn.callofthecorn.enums;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import static net.minecraft.sounds.SoundEvents.*;

public class CustomMaterials {

    public static ArmorMaterial cornmetal = new ArmorMaterial() {

        @Override
        public int getDurabilityForSlot(EquipmentSlot IN) {
            return 90;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot IN) {

            if(IN==EquipmentSlot.HEAD){return 2;}
            if(IN==EquipmentSlot.CHEST){return 6;}
            if(IN==EquipmentSlot.LEGS){return 5;}
            if(IN==EquipmentSlot.FEET){return 2;}
            else return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 1;
        }

        @Override
        public SoundEvent getEquipSound() {
            return ARMOR_EQUIP_CHAIN;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
//            Stream<? extends Ingredient.Value> hold = null;
//          CornMetalIngredient test = new CornMetalIngredient(hold);
//            return test;
        }

        @Override
        public String getName() {
            return "CornMetal";
        }

        @Override
        public float getToughness() {
            return 1;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }


    };





    public static ArmorMaterial mazierite = new ArmorMaterial() {

        @Override
        public int getDurabilityForSlot(EquipmentSlot IN) {
            return 300;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot IN) {

            if(IN==EquipmentSlot.HEAD){return 3;}
            if(IN==EquipmentSlot.CHEST){return 8;}
            if(IN==EquipmentSlot.LEGS){return 6;}
            if(IN==EquipmentSlot.FEET){return 3;}
            else return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 1;
        }

        @Override
        public SoundEvent getEquipSound() {
            return ARMOR_EQUIP_CHAIN;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "Maizerite";
        }

        @Override
        public float getToughness() {
            return 2;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

    };

    public static ArmorMaterial Pumpkin = new ArmorMaterial() {

        @Override
        public int getDurabilityForSlot(EquipmentSlot IN) {
            return 6000;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot IN) {

            if(IN==EquipmentSlot.HEAD){return 1;}
            if(IN==EquipmentSlot.CHEST){return 8;}
            if(IN==EquipmentSlot.LEGS){return 6;}
            if(IN==EquipmentSlot.FEET){return 3;}
            else return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 1;
        }

        @Override
        public SoundEvent getEquipSound() {
            return ARMOR_EQUIP_CHAIN;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "Pumpkin";
        }

        @Override
        public float getToughness() {
            return 3;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

    };


}



   ////// private enum ArmorMaterial implements IArmorMaterial {
    //    CornMetal("CornMetal", 16, new int[]{2, 5, 6, 2}, 18, () -> SoundEvents.ARMOR_EQUIP_CHAIN, () -> ModItems.manaSteel, 0)
   // }
