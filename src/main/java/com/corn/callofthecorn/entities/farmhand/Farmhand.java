package com.corn.callofthecorn.entities.farmhand;

import com.corn.callofthecorn.init.CornItems;
import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class Farmhand extends Skeleton{

    public static int MAX_HP = 250;
    public static int AttackDamage=8;
    public LivingEntity target;
    public double distance;
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    public Farmhand(EntityType<? extends Farmhand> type, Level level) {
        super(type, level);

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }


    @Override
    public int getExperienceReward (){
        return 20;
    }

    public float LastHp = this.getMaxHealth();

    @Override
        public void tick() {
            super.tick();
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());


            int Hold = new Random().nextInt(3);

            if (Hold == 1 && LastHp > this.getHealth() && target != null) {
                EntityType<?> entitytype = CornMobs.CROPWATCHER.get();
                ItemStack itemstack = new ItemStack(Items.ZOMBIE_VILLAGER_SPAWN_EGG);
                BlockPos blockpos1;
                BlockPos blockpos = this.blockPosition();
                Direction direction = this.getDirection();
                blockpos1 = blockpos.relative(direction);


                entitytype.spawn((ServerLevel) level(), itemstack, target.level().getNearestPlayer(this, 0),
                        blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1)
                                && direction == Direction.UP);
            }

        LastHp = this.getHealth();


        if(target!=null){
        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
        distance = d3;
        System.out.println(distance);

        if (distance > 10) {
            teleportTowards(target);
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
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));

            ItemStack itemstack = this.getMainHandItem();
            if (itemstack.is(Items.BOW)) {
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack);
                map.putIfAbsent(Enchantments.POWER_ARROWS, 3);
                EnchantmentHelper.setEnchantments(map, itemstack);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
            }


    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_31464_, int p_31465_, boolean p_31466_) {
        super.dropCustomDeathLoot(p_31464_, p_31465_, p_31466_);

        ItemEntity itementity = this.spawnAtLocation(CornItems.MILDSOUL.get().getDefaultInstance().copyWithCount(2 + random.nextInt(3)));
        if (itementity != null) {
            itementity.setGlowingTag(true);
            itementity.setInvulnerable(true);
        }
        if(random.nextInt(10) == 0) {
            ItemEntity foot = this.spawnAtLocation(CornItems.CROWSFOOT.get());
            if (foot != null) {
                foot.setGlowingTag(true);
                foot.setInvulnerable(true);
            }
        }
    }


    @Override
    public boolean doHurtTarget(Entity p_28837_) {
        if (distance < 4) {
           // this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(null));
            this.level().broadcastEntityEvent(this, (byte) 4);
            float f = AttackDamage;
            float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
            boolean flag = p_28837_.hurt(level().damageSources().mobAttack(this), f1);
            if (flag) {
                p_28837_.setDeltaMovement(p_28837_.getDeltaMovement().add(0.5D, (double) 0.8F, 0.0D));
                this.doEnchantDamageEffects(this, p_28837_);
            }

            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));

            ItemStack itemstack = this.getMainHandItem();
            if (itemstack.is(Items.BOW)) {
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack);
                map.putIfAbsent(Enchantments.POWER_ARROWS, 3);
                EnchantmentHelper.setEnchantments(map, itemstack);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
            }

            return flag;
        }
        else{
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));

            ItemStack itemstack = this.getMainHandItem();
            if (itemstack.is(Items.BOW)) {
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack);
                map.putIfAbsent(Enchantments.POWER_ARROWS, 3);
                EnchantmentHelper.setEnchantments(map, itemstack);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
            }

            return false;}

    }

    @Override
    public void performRangedAttack(LivingEntity p_32141_, float p_32142_) {
        target = p_32141_;
        double d0 = target.getX() - this.getX();
        double d1 = target.getY() - this.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2+d1*d1);
        distance = d3;
        System.out.println(distance);

        if (distance > 10) {teleportTowards(target);}

        if (distance > 4) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
            ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
            AbstractArrow abstractarrow = this.getArrow(itemstack, p_32142_);
             d0 = p_32141_.getX() - this.getX();
             d1 = p_32141_.getY(0.3333333333333333D) - abstractarrow.getY();
             d2 = p_32141_.getZ() - this.getZ();
             d3 = Math.sqrt(d0 * d0 + d2 * d2);
            abstractarrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrow);
            abstractarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
            this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.level().addFreshEntity(abstractarrow);
        } else {
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
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

        while(blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
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


