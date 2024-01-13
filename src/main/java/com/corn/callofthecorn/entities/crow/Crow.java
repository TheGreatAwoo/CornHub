package com.corn.callofthecorn.entities.crow;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class Crow extends Parrot {

    public boolean active;
    public static int MAX_HP = 3;
    public static int Damage = 4;

    private int Lifespan = 600;

    public Crow(EntityType<? extends Parrot> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
        this.moveControl = new FlyingMoveControl(this, 10, true);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
    }

    public void setRemainingPersistentAngerTime(int p_30404_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, 10);
    }
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.INT);


    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }


    @Override
    protected void dropCustomDeathLoot(DamageSource p_21385_, int p_21386_, boolean p_21387_) {
        ItemEntity itementity = this.spawnAtLocation(Items.FEATHER);
        ItemEntity itementity2 = this.spawnAtLocation(Items.FEATHER);
        ItemEntity itementity3 = this.spawnAtLocation(Items.CHICKEN);
        super.dropCustomDeathLoot(p_21385_, p_21386_, p_21387_);
    }



    @Override
    public void tick() {
        this.moveControl = new FlyingMoveControl(this, 10, true);
        super.tick();
       Lifespan--;
       if (Lifespan==0)this.kill();
    }
}


