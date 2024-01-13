package com.corn.callofthecorn.entities.CropWatcher;

import com.corn.callofthecorn.Init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class CropWatcher extends Skeleton {

    public static int MAX_HP =16;
    public static int AttackDamage=3;


    public CropWatcher(EntityType<? extends Skeleton> p_33262_, Level p_33263_) {
        super(p_33262_, p_33263_);
    }


    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        super.populateDefaultEquipmentSlots(p_219059_, p_219060_);

        Random ran = new Random();
        int hold = ran.nextInt(10);

        if (hold ==1){
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemInit.CORNMETALAXE.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ItemInit.CORNMETALHEAD.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(ItemInit.CORNMETALFEET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ItemInit.CORNMETALCHEST.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ItemInit.CORNMETALPANTS.get()));}

        if (hold ==4||hold ==3||hold ==2){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));}

        if (hold >5){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
          }


        //this.populateDefaultEquipmentSlots(randomsource, p_32147_);
        this.populateDefaultEquipmentEnchantments(p_219059_, p_219060_);
        //this.reassessWeaponGoal();
        this.setCanPickUpLoot(true);

    }

//    @Override
//    public Team getTeam() {
//        return Main.CTeam;
//    }


    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource p_32750_) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_HURT;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_31464_, int p_31465_, boolean p_31466_) {
        super.dropCustomDeathLoot(p_31464_, p_31465_, p_31466_);
            ItemEntity itementity = this.spawnAtLocation(ItemInit.LESSERSOUL.get());}



}


