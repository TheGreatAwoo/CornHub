package com.corn.callofthecorn.entities.farmhand;

import com.corn.callofthecorn.init.CornItems;
import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;


public class Farmhand extends AbstractSkeleton {

    public static final int MAX_HP = 250;
    public static final int ATTACK_DAMAGE = 8;
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    public Farmhand(EntityType<? extends Farmhand> type, Level level) {
        super(type, level);
        this.xpReward = 20;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    public float lasthp = this.getHealth();

    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        int hold = new Random().nextInt(3);

        if (lasthp > this.getHealth() + 4) {
            if (hold == 1 && getTarget() != null) { // 1/3 chance of spawning enemy per 4 hp of damage
                EntityType<?> watcher = CornMobs.CROPWATCHER.get();
                BlockPos blockpos1;
                BlockPos blockpos = this.blockPosition();
                Direction direction = this.getDirection();
                blockpos1 = blockpos.relative(direction);

                watcher.spawn((ServerLevel) level(), null, level().getNearestPlayer(this, 0),
                        blockpos1, EntitySpawnReason.TRIGGERED, true, !Objects.equals(blockpos, blockpos1)
                                && direction == Direction.UP);
            }
            lasthp = Math.max(this.getHealth(), lasthp - 8);

        }

        if(getTarget() != null){
            double distance = distanceTo(getTarget());

            if (distance > 10) {
                teleportTowards(getTarget());
            }

            distance = distanceTo(getTarget());

            if (distance > 5) {
                setAttackState(level(), true);
            } else if(distance < 3) {
                setAttackState(level(), false);
            }


        }

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

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance diff, EntitySpawnReason reason, @Nullable SpawnGroupData data) {
        setAttackState(level, false);
        return super.finalizeSpawn(level, diff, reason, data);
    }

    private void setAttackState(LevelAccessor level, boolean ranged) {
        if(ranged) {
            if(getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                ItemStack itemstack = new ItemStack(Items.BOW);
                Holder.Reference<Enchantment> power = level.registryAccess().lookup(Registries.ENCHANTMENT).get().get(Enchantments.POWER).get();
                itemstack.enchant(power, 3);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
            }
        } else if (!getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()){
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource p_31464_, boolean p_31466_) {
        super.dropCustomDeathLoot(level, p_31464_, p_31466_);

        ItemEntity itementity = this.spawnAtLocation(level, CornItems.MILD_SOUL.get().getDefaultInstance().copyWithCount(2 + random.nextInt(3)));
        if (itementity != null) {
            itementity.setGlowingTag(true);
            itementity.setInvulnerable(true);
        }
        if(random.nextInt(10) == 0) {
            ItemEntity foot = this.spawnAtLocation(level, CornItems.CROWSFOOT.get());
            if (foot != null) {
                foot.setGlowingTag(true);
                foot.setInvulnerable(true);
            }
        }
    }


    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        this.level().broadcastEntityEvent(this, (byte) 4);
        float f = ATTACK_DAMAGE;
        float f1 = (int)f > 0 ? f / 2.0F + this.random.nextInt((int)f) : f;
        DamageSource damagesource = this.damageSources().mobAttack(this);
        boolean flag = target.hurtServer(level, damagesource, f1);
        if (flag) {
            double d0 = target instanceof LivingEntity livingentity ? livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) : 0.0;
            double d1 = Math.max(0.0, 1.0 - d0);
            target.setDeltaMovement(target.getDeltaMovement().add(0.0, 0.4F * d1, 0.0));
            EnchantmentHelper.doPostAttackEffects(level, target, damagesource);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_VILLAGER_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource p_33579_) {
        return SoundEvents.ZOMBIE_VILLAGER_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_VILLAGER_DEATH;
    }
    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_VILLAGER_STEP;
    }




    boolean teleportTowards(Entity p_32501_) {
        Vec3 vec3 = new Vec3(this.getX() - p_32501_.getX(), this.getY(0.5D) - p_32501_.getEyeY(), this.getZ() - p_32501_.getZ());
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.x * 16.0D;
        double d2 = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    protected boolean teleport() {
        if (!this.level().isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (double)(this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        } else {
            return false;
        }
    }

    private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

        while(blockpos$mutableblockpos.getY() > this.level().getMinY() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.neoforged.neoforge.event.entity.EntityTeleportEvent.EnderEntity event = net.neoforged.neoforge.event.EventHooks.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
            if (event.isCanceled()) return false;
            Vec3 vec3 = this.position();
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                this.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
                if (!this.isSilent()) {
                    this.level().playSound((Player)null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            }

            return flag2;
        } else {
            return false;
        }
    }







}


