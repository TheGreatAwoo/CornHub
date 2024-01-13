package com.corn.callofthecorn.entities.scarecrow;

import com.corn.callofthecorn.init.CornItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public class Scarecrow extends Skeleton {

    public boolean active = false;
    public static int MAX_HP = 16;
    public int stillVal;


//    @Override
//    public boolean isAngryAtAllPlayers(Level p_21671_) {
//        return true;
//    }


//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
//        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
//        this.goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.6D, false));
//        this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6D));
//        this.goalSelector.addGoal(5, new OfferFlowerGoal(this));
//        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
//        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(1, new DefendVillageTargetGoal(this));
//        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
////        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
////            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
////        }));
//        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
//    }


    @Override
    public boolean doHurtTarget(Entity p_28837_) {
        this.level().broadcastEntityEvent(this, (byte) 4);
        float f = 3;
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        boolean flag = p_28837_.hurt(level().damageSources().mobAttack(this), f1);
        if (flag) {
            this.doEnchantDamageEffects(this, p_28837_);
        }
        return flag;
    }

    @Override
    public boolean hurt(DamageSource p_28848_, float p_28849_) {

        boolean flag = super.hurt(p_28848_, p_28849_);
        return flag;
    }


    @Override
    protected void playStepSound(BlockPos p_28864_, BlockState p_28865_) {
//        super.playStepSound(p_28864_, p_28865_);
    }

    public Scarecrow(EntityType<? extends Scarecrow> type, Level level) {
        super(type, level);
        stillVal = random.nextInt(10);

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33579_) {
        if (active) {
            return SoundEvents.SKELETON_HURT;
        } else {
            stillVal = random.nextInt(10);
            return null;
        }
    }

    @Override
    public int getExperienceReward() {
        if (this.active) {
            return (super.getExperienceReward());
        }
        return (0);

    }


    @Override
    protected void dropCustomDeathLoot(DamageSource p_31464_, int p_31465_, boolean p_31466_) {
        super.dropCustomDeathLoot(p_31464_, p_31465_, p_31466_);
        if (stillVal == 1 || active) {
            ItemEntity itementity = this.spawnAtLocation(CornItems.LESSERSOUL.get());
            if (itementity != null) {
                itementity.setExtendedLifetime();
            }
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.CARVED_PUMPKIN));
        this.isSunBurnTick();
        this.setNoAi(true);

    }

    Player target = null;
    int targetdis = 12;


    @Override
    public void tick() {
        super.tick();


        if (!level().isNight()) {
            targetdis = 12;
            active = false;
        }

        if (stillVal == 1) {
            active = true;
            targetdis = 12;
        }

        if (level().isNight() || level().isRaining()) {
            targetdis = 30;
            active = true;
        }


        if (target != null) {
            double d0 = target.getX() - this.getX();
            double d1 = target.getY() - this.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
            double distance = d3;
            this.setNoAi(!(distance < targetdis) || !active);
        }
        if (level().isNight()) {
            this.setNoAi(false);
        }


    }

    @Override
    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        target = p_31483_;
    }


    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

}


