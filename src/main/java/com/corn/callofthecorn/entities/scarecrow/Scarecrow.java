package com.corn.callofthecorn.entities.scarecrow;

import com.corn.callofthecorn.entities.crow.Crow;
import com.corn.callofthecorn.init.CornItems;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class Scarecrow extends AbstractSkeleton {

    public boolean active = false;
    public static int MAX_HP = 16;
    private static final EntityDataAccessor<Boolean> ALWAYS_ACTIVE = SynchedEntityData.defineId(Scarecrow.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ScarecrowFreezeGoal<>(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Crow.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.BOGGED_STEP;
    }

    public Scarecrow(EntityType<? extends Scarecrow> type, Level level) {
        super(type, level);
        int stillVal = random.nextInt(10);
        if(stillVal == 0) {
            this.entityData.set(ALWAYS_ACTIVE, true);
        }
//        this.setNoAi(true);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        if (active) {
            return SoundEvents.GHAST_HURT;
        } else {
            return SoundEvents.WOOL_BREAK;
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ALWAYS_ACTIVE, false);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33579_) {
        if (active) {
            return SoundEvents.SKELETON_HURT;
        } else {
            return SoundEvents.WOOL_HIT;
        }
    }

    @Override
    protected int getBaseExperienceReward(ServerLevel level) {
        if (this.active) {
            return super.getBaseExperienceReward(level);
        }
        return 0;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        super.populateDefaultEquipmentSlots(randomSource, difficultyInstance);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource p_31464_, boolean p_31466_) {
        super.dropCustomDeathLoot(level, p_31464_, p_31466_);
        if (active) {
            ItemEntity itementity = this.spawnAtLocation(level, CornItems.LESSER_SOUL.get());
            if (itementity != null) {
                itementity.setExtendedLifetime();
            }
        }
    }

    private boolean wakeTime() {
        return level().getSkyDarken() > 2;
    }


    @Override
    public void tick() {
        super.tick();
        int targetdis = 12;

        if (entityData.get(ALWAYS_ACTIVE)) {
            active = true;
        } else {
            active = false;
        }

        if (wakeTime()) {
            targetdis = 30;
            active = true;
        }

        if (getTarget() == null) {
            active = false;
        } else {
            double distance = distanceTo(getTarget());
            if(distance > targetdis) {
                active = false;
            }
        }
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.store("AlwaysActive", Codec.BOOL, this.entityData.get(ALWAYS_ACTIVE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        boolean aa = tag.read("AlwaysActive", Codec.BOOL).orElse(false);
        this.entityData.set(ALWAYS_ACTIVE, aa);
    }
}


