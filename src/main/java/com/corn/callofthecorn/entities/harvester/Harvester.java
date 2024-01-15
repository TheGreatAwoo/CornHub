package com.corn.callofthecorn.entities.harvester;

import com.corn.callofthecorn.init.CornItems;
import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.Random;

import com.corn.callofthecorn.entities.LightingBall;

public class Harvester extends Skeleton {

    public static int MAX_HP = 300;
    public static int AttackDamage = 10;
    public LivingEntity target;
    public double distance;
    private int explosionPower = 1;
    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

//
//    @Nullable
//    @Override
//    public Team getTeam() {
//        return Main.CTeam;
//    }


    @Override
    public int getExperienceReward() {
        return (super.getExperienceReward() * 120);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        this.bossEvent.addPlayer(p_31483_);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer p_31488_) {
        super.stopSeenByPlayer(p_31488_);
        this.bossEvent.removePlayer(p_31488_);
    }


    public Harvester(EntityType<? extends Harvester> type, Level level) {
        super(type, level);
        this.canSpawnSprintParticle();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_31464_, int p_31465_, boolean p_31466_) {
        super.dropCustomDeathLoot(p_31464_, p_31465_, p_31466_);

        ItemEntity itementity = this.spawnAtLocation(CornItems.GREATERSOUL.get().getDefaultInstance().copyWithCount(2 + random.nextInt(3)));
        itementity.setGlowingTag(true);
        itementity.setInvulnerable(true);

        if (random.nextInt(5) == 0) {
            this.spawnAtLocation(CornItems.HARVESTERSCYTHE.get());
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(CornItems.HARVESTERSCYTHE.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.BOW));

    }


    @Override
    public boolean doHurtTarget(Entity target) {
        if (distance < 1) {
            this.level().broadcastEntityEvent(this, (byte) 4);
            float f = AttackDamage;
            float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
            boolean flag = target.hurt(level().damageSources().mobAttack(this), f1);
            if (flag) {
                target.setDeltaMovement(target.getDeltaMovement().add(5F, 0.0F, 0.0D));
                this.doEnchantDamageEffects(this, target);
                this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.BOW));

            }

            return flag;
        } else {
            this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.BOW));
            return false;
        }

    }


    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        this.level().addParticle(ParticleTypes.DRAGON_BREATH, this.getX() + this.random.nextGaussian(), this.getY() + (double) (this.random.nextFloat() * 3.3F), this.getZ() + this.random.nextGaussian(), 0.7F, 0.7F, 0.9F);
        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE));
    }

    int Barrage = 0;
    int Lightning = 0;


    @Override
    public void performRangedAttack(LivingEntity p_32141_, float p_32142_) {

        target = p_32141_;
        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
        distance = d3;
        boolean action = false;
        int mobLevel = 1;

        if (this.getHealth() < (this.getMaxHealth() / 4)) {
            explosionPower = 4;
            mobLevel = 4;
        } else if (this.getHealth() < (this.getMaxHealth() / 3)) {
            explosionPower = 3;
            mobLevel = 3;
        } else if (this.getHealth() < (this.getMaxHealth() / 2)) {
            explosionPower = 2;
            mobLevel = 2;
        }

        SummonCrows(mobLevel, action);
        if (this.getHealth() < this.getMaxHealth() / 5) {
            Ultimate(p_32141_, mobLevel, action);
            action = true;
        }


        if (!action) {
            Fireballs(p_32141_);
            if (Barrage > 6) {
                action = FireBarrage(p_32141_);
                Barrage = 0;
            }
            if (Lightning > 5) {
                action = Lighting(p_32141_);
                Lightning = 0;
            }
            if (distance < 5) {
                action = FireStorm(p_32141_);
            }
        }

        if (!action) {
            Fireballs(p_32141_);
            Barrage = Barrage + 1;
            Lightning = Lightning + 1;
        }
    }


    private void Ultimate(LivingEntity p_32141_, int mobLevel, boolean action) {
        this.setDeltaMovement(0, 1, 0);
        this.setNoGravity(true);
        Boolean test2 = Lighting(p_32141_);
    }


    private boolean FireStorm(LivingEntity p_32141_) {
        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
        d1 = 4.0D;
        Vec3 vec3 = this.getViewVector(1.0F);
        d2 = p_32141_.getX() - (this.getX() + vec3.x * 4.0D);
        d3 = p_32141_.getY(0.5D) - (0.5D + this.getY(0.5D));
        double d4 = p_32141_.getZ() - (this.getZ() + vec3.z * 4.0D);

        for (int x = 0; x < 5 * explosionPower; x++) {
            int xran = random.nextInt(20);
            int zran = random.nextInt(20);

            LargeFireball largefireball = new LargeFireball(level(), this, d2, d3, d4, this.explosionPower);
            largefireball.setPos(this.getX() + xran - 10, this.getY() + 30, largefireball.getZ() + zran - 10);

            largefireball.xPower = 0;
            largefireball.zPower = 0;
            largefireball.yPower = -.5;
            level().addFreshEntity(largefireball);
        }
        return true;

    }


    private boolean FireBarrage(LivingEntity p_32141_) {

        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
        d1 = 4.0D;
        d1 = 4.0D;
        Vec3 vec3 = this.getViewVector(1.0F);
        d2 = p_32141_.getX() - (this.getX() + vec3.x * 4.0D);
        d3 = p_32141_.getY(0.5D) - (0.5D + this.getY(0.5D));
        double d4 = p_32141_.getZ() - (this.getZ() + vec3.z * 4.0D);

        for (int x = 0; x < explosionPower + 2; x++) {
            int xran = random.nextInt(3);
            int zran = random.nextInt(3);
            int yran = random.nextInt(3);

            LargeFireball largefireball = new LargeFireball(level(), this, d2, d3, d4, this.explosionPower);
            largefireball.setPos(this.getX() + zran + vec3.x * 4.0D, this.getY(0.5D) + yran + 0.5D, largefireball.getZ() + xran + vec3.z * 4.0D);
            level().addFreshEntity(largefireball);
        }
        return true;
    }

    private boolean Lighting(LivingEntity p_32141_) {

        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
        d1 = 4.0D;
        d1 = 4.0D;
        Vec3 vec3 = this.getViewVector(1.0F);
        d2 = p_32141_.getX() - (this.getX() + vec3.x * 4.0D);
        d3 = p_32141_.getY(0.5D) - (0.5D + this.getY(0.5D));
        double d4 = p_32141_.getZ() - (this.getZ() + vec3.z * 4.0D);

        LightingBall largefireball = new LightingBall(level(), this, d2, d3, d4, this.explosionPower);
        largefireball.setPos(this.getX() + vec3.x * 4.0D, this.getY(0.5D) + 0.5D, largefireball.getZ() + vec3.z * 4.0D);
        level().addFreshEntity(largefireball);
        return true;
    }

    private void Fireballs(LivingEntity p_32141_) {
        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.BOW));
        d1 = 4.0D;
        Vec3 vec3 = this.getViewVector(1.0F);
        d2 = p_32141_.getX() - (this.getX() + vec3.x * 4.0D);
        d3 = p_32141_.getY(0.5D) - (0.5D + this.getY(0.5D));
        double d4 = p_32141_.getZ() - (this.getZ() + vec3.z * 4.0D);
        LargeFireball largefireball = new LargeFireball(level(), this, d2, d3, d4, this.explosionPower);
        largefireball.setPos(this.getX() + vec3.x * 4.0D, this.getY(0.5D) + 0.5D, this.getZ() + vec3.z * 4.0D);
        level().addFreshEntity(largefireball);
    }

    private void SummonCrows(int mobLevel, boolean action) {

        for (int X = 0; X < 5 * mobLevel; X++) {
            EntityType<?> entitytype = CornMobs.CROW.get();
            ItemStack itemstack = new ItemStack(Items.WITHER_SKELETON_SPAWN_EGG);
            BlockPos blockpos1;
            BlockPos blockpos = this.blockPosition();
            int xran = random.nextInt(40);
            int zran = random.nextInt(40);
            blockpos = blockpos.offset(-20 + xran, 4, -20 + zran);
            Direction direction = this.getDirection();
            blockpos1 = blockpos.relative(direction);

            entitytype.spawn((ServerLevel) level(), itemstack, target.level().getNearestPlayer(this, 0),
                    blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1)
                            && direction == Direction.UP);
        }
    }


}


