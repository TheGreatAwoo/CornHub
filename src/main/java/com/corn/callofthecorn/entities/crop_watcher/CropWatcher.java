package com.corn.callofthecorn.entities.crop_watcher;

import com.corn.callofthecorn.init.CornItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class CropWatcher extends AbstractSkeleton {

    public static int MAX_HP =16;
    public static int AttackDamage=3;


    public CropWatcher(EntityType<? extends AbstractSkeleton> p_33262_, Level p_33263_) {
        super(p_33262_, p_33263_);
    }


    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.BOGGED_STEP;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        super.populateDefaultEquipmentSlots(p_219059_, p_219060_);

        Random ran = new Random();
        int hold = ran.nextInt(10);

        if (hold ==1){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(CornItems.CORNMETALAXE.get()));
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(CornItems.CORNMETALHEAD.get()));
            this.setItemSlot(EquipmentSlot.FEET, new ItemStack(CornItems.CORNMETALFEET.get()));
            this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(CornItems.CORNMETALCHEST.get()));
            this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(CornItems.CORNMETALPANTS.get()));
        }

        if (hold ==4 ||hold ==3|| hold ==2) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }

        if (hold >5) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
        }


        //this.populateDefaultEquipmentSlots(randomsource, p_32147_);
//        this.populateDefaultEquipmentEnchantments(p_219059_, p_219060_);
        //this.reassessWeaponGoal();
        this.setCanPickUpLoot(true);

    }


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
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean p_31466_) {
        super.dropCustomDeathLoot(level, source, p_31466_);
            ItemEntity itementity = this.spawnAtLocation(level, CornItems.LESSER_SOUL.get());
    }

}


