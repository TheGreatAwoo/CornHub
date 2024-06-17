package com.corn.callofthecorn.entities.harvester;

import com.corn.callofthecorn.entities.LightingBall;
import com.corn.callofthecorn.init.CornItems;
import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class Harvester extends AbstractSkeleton {

    public static final int MAX_HP = 300;
    public static final int AttackDamage = 10;
    private int barrageTracker = 0;
    private int lightningTracker = 0;
    private int crowTracker = 10;
    private boolean hasUlted = false;
    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    @Override
    public int getExperienceReward() {
        return 120;
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
        this.setCanPickUpLoot(false);
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new HarvesterAttackGoal<>(this, 1.5D, 60, 25.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    public void reassessWeaponGoal() {

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
    }


    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        this.level().addParticle(ParticleTypes.DRAGON_BREATH, this.getX() + this.random.nextGaussian(), this.getY(), this.getZ() + this.random.nextGaussian(), this.random.nextGaussian(), 0.7F, this.random.nextGaussian());
        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE));
    }

    @Override
    public boolean hurt(DamageSource src, float amount) {
        if(src.is(DamageTypeTags.IS_EXPLOSION)) {
            return false;
        }
        return super.hurt(src, amount);
    }

    public void castSpell(LivingEntity target, boolean canSeeTarget) {
        int bossStage = 1;

        if (this.getHealth() < (this.getMaxHealth() / 4)) {
            bossStage = 4;
        } else if (this.getHealth() < (this.getMaxHealth() / 3)) {
            bossStage = 3;
        } else if (this.getHealth() < (this.getMaxHealth() / 2)) {
            bossStage = 2;
        }

        if (bossStage == 4 && !hasUlted) {
            summonCrows(bossStage+2);
            castUltimate(target, bossStage);
            hasUlted = true;
        } else if (barrageTracker > 6) {
            castFireBarrage(target, bossStage);
            barrageTracker = 0;
        } else if (lightningTracker > 5) {
            castLighting(target, bossStage);
            lightningTracker = 0;
        } else if (distanceTo(target) < 3 + bossStage) {
            castFireStorm(target, bossStage);
        } else {
            castFireballs(target, bossStage);
        }
        barrageTracker++;
        lightningTracker++;
        crowTracker++;

        if (crowTracker > 6) {
            summonCrows(bossStage);
            crowTracker = 0;
        }
    }

    private void castUltimate(LivingEntity target, int bossStage) {
        this.setDeltaMovement(0, .5, 0);
        this.setNoGravity(true);
        castLighting(target, bossStage);
    }

    private void castFireStorm(LivingEntity target, int bossStage) {
        boolean sky = level().canSeeSky(this.blockPosition());
        for (int i = 0; i < 5 * bossStage; i++) {
            int xran = random.nextInt(10 + 2*bossStage) - 5 - bossStage;
            int zran = random.nextInt(10 + 2*bossStage) - 5 - bossStage;
            int yoff = sky ? 30 : 10;

            LargeFireball largefireball = new LargeFireball(level(), this, 0,-1,0, bossStage);
            largefireball.setPos(this.getX() + xran, this.getY() + yoff, largefireball.getZ() + zran);

            largefireball.xPower = 0;
            largefireball.zPower = 0;
            largefireball.yPower = -0.3 - random.nextFloat()*.2;
            level().addFreshEntity(largefireball);
        }
    }


    private void castFireBarrage(LivingEntity target, int bossStage) {
        Vec3 viewVector = this.getViewVector(1.0F);
        Vec3 castPos = this.getPosition(1.f).add(viewVector.scale(3));
        Vec3 delta = target.getEyePosition().subtract(castPos);

        for (int i = 0; i < bossStage + 2; i++) {
            Vec3 offset = new Vec3(random.nextFloat() - 0.5, random.nextFloat() - 0.5, random.nextFloat() - 0.5).scale(5);
            LargeFireball ball = new LargeFireball(level(), this, delta.x, delta.y, delta.z, bossStage);
            ball.setPos(castPos.add(offset));
            level().addFreshEntity(ball);
        }
    }

    private void castLighting(LivingEntity target, int bossStage) {
        Vec3 viewVector = this.getViewVector(1.0F);
        Vec3 castPos = this.getPosition(1.f).add(viewVector.scale(3));
        Vec3 delta = target.getEyePosition().subtract(castPos);
        LightingBall ball = new LightingBall(level(), this, delta.x, delta.y, delta.z, bossStage);
        ball.setPos(castPos);
        level().addFreshEntity(ball);
    }

    private void castFireballs(LivingEntity target, int bossStage) {
        Vec3 viewVector = this.getViewVector(1.0F);
        Vec3 castPos = this.getPosition(1.f).add(viewVector.scale(3));
        Vec3 delta = target.getEyePosition().subtract(castPos);
        LargeFireball ball = new LargeFireball(level(), this, delta.x, delta.y, delta.z, bossStage);
        ball.setPos(castPos);
        level().addFreshEntity(ball);
    }

    private void summonCrows(int bossStage) {
        for (int i = 0; i < 10 * bossStage; i++) {
            EntityType<?> entitytype = CornMobs.CROW.get();
            int xran = random.nextInt(40);
            int zran = random.nextInt(40);
            BlockPos spawnPos = this.blockPosition().offset(-20 + xran, 4, -20 + zran);
            if(xran*xran + zran*zran > 16 && level().isEmptyBlock(spawnPos)) {
                entitytype.spawn((ServerLevel) level(), spawnPos, MobSpawnType.MOB_SUMMONED);
            }
        }
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

}


