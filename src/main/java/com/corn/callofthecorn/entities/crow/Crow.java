package com.corn.callofthecorn.entities.crow;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.entities.harvester.Harvester;
import com.corn.callofthecorn.entities.scarecrow.Scarecrow;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class Crow extends Parrot {

    public static final int MAX_HP = 3;
    public static final int DAMAGE = 4;

    private static final int LIFESPAN = 600;

    public Crow(EntityType<? extends Crow> crow, Level level) {
        super(crow, level);
        this.entityData.set(REMAINING_LIFETIME, LIFESPAN);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Scarecrow.class, 8.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Harvester.class)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
    }

    private static final EntityDataAccessor<Integer> REMAINING_LIFETIME = SynchedEntityData.defineId(Crow.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(REMAINING_LIFETIME, LIFESPAN);
    }

    @Override
    protected void applyTamingSideEffects() {
//        this.entityData.set(REMAINING_LIFETIME, 100000);
        this.setAggressive(false);
    }


    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }


    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean p_21387_) {
        super.dropCustomDeathLoot(level, source, p_21387_);
        if (source != level().damageSources().genericKill()) {
            this.spawnAtLocation(level, Items.FEATHER.getDefaultInstance().copyWithCount(random.nextInt(3)));
            if(isOnFire()) {
                this.spawnAtLocation(level, Items.COOKED_CHICKEN);
            } else {
                this.spawnAtLocation(level, Items.CHICKEN);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        int life = this.entityData.get(REMAINING_LIFETIME);
        if(life != 100000) {
            this.entityData.set(REMAINING_LIFETIME, life-1);
        }
        if (life <= 0 && !this.dead) {
            this.causeFallDamage(100, 100, level().damageSources().genericKill());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.store("Lifespan", Codec.INT, this.entityData.get(REMAINING_LIFETIME));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        int life = tag.read("Lifespan", Codec.INT).orElse(LIFESPAN);
        this.entityData.set(REMAINING_LIFETIME, life);
    }
}


