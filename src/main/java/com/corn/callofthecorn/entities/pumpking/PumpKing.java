package com.corn.callofthecorn.entities.pumpking;

import com.corn.callofthecorn.entities.crow.Crow;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableWitchTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class PumpKing extends Witch {
    public static int MAX_HP =150;
    public static int AttackDamage=5;
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    public PumpKing(EntityType<? extends Witch> p_34166_, Level p_34167_) {
        super(p_34166_, p_34167_);
        xpReward *= 15;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(CornItems.PUMPKINGSCROWN.get()));
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Player.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    int timer = 0;

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
        this.setTarget(player);
        this.setPos(player.getEyePosition());
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer p_31488_) {
        super.stopSeenByPlayer(p_31488_);
        this.bossEvent.removePlayer(p_31488_);
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
        return super.getDeathSound();
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean p_31466_) {
        super.dropCustomDeathLoot(level, source, p_31466_);


        ItemEntity itementity = this.spawnAtLocation(level, CornItems.GREATER_SOUL.get().getDefaultInstance().copyWithCount(2 + random.nextInt(3)));
        itementity.setGlowingTag(true);
        itementity.setInvulnerable(true);

        Random ran = new Random();
        if(ran.nextInt(5) == 0) {
            itementity = this.spawnAtLocation(level, CornItems.PUMPKINGSCROWN.get());
        }

        if (itementity != null) {
            itementity.setExtendedLifetime();
        }
    }



    @Override
    public void performRangedAttack(LivingEntity p_34143_, float p_34144_) {
            Random rand = new Random();
            int val =rand.nextInt(4);
            performSpellCasting();
            performSpellCasting2();
            throwPotion( p_34143_,  p_34144_);
            if(val==1){performSpellCastingBlind();}
    }


    protected void Clone()   {
        if (this.level().isClientSide ) {
            double d0 = 0;
            double d1 = 0;
            double d2 = 0;
            float f = this.yBodyRot * ((float)Math.PI / 180F) + Mth.cos((float)this.tickCount * 0.6662F) * 0.25F;
            float f1 = Mth.cos(f);
            float f2 = Mth.sin(f);
            this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX() + (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() + (double)f2 * 0.6D, d0, d1, d2);
            this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX() - (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() - (double)f2 * 0.6D, d0, d1, d2);
        }
    }


    protected void performSpellCastingBlind() {
        if( this.getTarget() != null){
            this.getTarget().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400), this);
        }
    }

    // This is copied from Witch#performRangedAttack, but with some potion effects changed
    private void throwPotion(LivingEntity target, float p_34144_){
        Vec3 vec3 = target.getDeltaMovement();
        double d0 = target.getX() + vec3.x - this.getX();
        double d1 = target.getEyeY() - 1.1F - this.getY();
        double d2 = target.getZ() + vec3.z - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        Holder<Potion> holder = Potions.STRONG_HARMING;
        if (d3 >= 8.0 && !target.hasEffect(MobEffects.SLOWNESS)) {
            holder = Potions.STRONG_SLOWNESS;
        } else if (target.getHealth() >= 8.0F && !target.hasEffect(MobEffects.POISON)) {
            holder = Potions.STRONG_POISON;
        } else if (d3 <= 3.0 && !target.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
            holder = Potions.LONG_WEAKNESS;
        }

        if (this.level() instanceof ServerLevel serverlevel) {
            ItemStack itemstack = PotionContents.createItemStack(Items.SPLASH_POTION, holder);
            Projectile.spawnProjectileUsingShoot(ThrownSplashPotion::new, serverlevel, itemstack, this, d0, d1 + d3 * 0.2, d2, 0.75F, 8.0F);
        }

        if (!this.isSilent()) {
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.WITCH_THROW,
                            this.getSoundSource(),
                            1.0F,
                            0.8F + this.random.nextFloat() * 0.4F
                    );
        }



    }



    private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
        BlockPos blockpos = BlockPos.containing(p_32673_, p_32676_, p_32674_);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = this.level().getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(this.level(), blockpos1, Direction.UP)) {
                if (!this.level().isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = this.level().getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(this.level(), blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

        if (flag) {
            this.level().addFreshEntity(new EvokerFangs(this.level(), p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, this));
        }

    }

    protected void performSpellCasting() {
        if (this.getTarget()!=null) {
        LivingEntity livingentity = this.getTarget();
        double d0 = Math.min(livingentity.getY(), this.getY());
        double d1 = Math.max(livingentity.getY(), this.getY()) + 1.0D;
        float f = (float) Mth.atan2(livingentity.getZ() - this.getZ(), livingentity.getX() - this.getX());
        if (this.distanceToSqr(livingentity) < 9.0D) {
            for (int i = 0; i < 5; ++i) {
                float f1 = f + (float) i * (float) Math.PI * 0.4F;
                this.createSpellEntity(this.getX() + (double) Mth.cos(f1) * 1.5D, this.getZ() + (double) Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
            }
        }
    }}

    protected void performSpellCasting2() {
        if (this.getTarget()!=null) {
            LivingEntity livingentity = this.getTarget();
            double d0 = Math.min(livingentity.getY(), this.getY());
            double d1 = Math.max(livingentity.getY(), this.getY()) + 1.0D;
            float f = (float) Mth.atan2(livingentity.getZ() - this.getZ(), livingentity.getX() - this.getX());
            if (this.distanceToSqr(livingentity) < 9.0D) {
                for (int i = 0; i < 5; ++i) {
                    float f1 = f + (float) i * (float) Math.PI * 0.4F;
                    this.createSpellEntity(this.getX() + (double) Mth.cos(f1) * 1.5D, this.getZ() + (double) Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for (int k = 0; k < 8; ++k) {
                    float f2 = f + (float) k * (float) Math.PI * 2.0F / 8.0F + 1.2566371F;
                    this.createSpellEntity(this.getX() + (double) Mth.cos(f2) * 2.5D, this.getZ() + (double) Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            } else {
                for (int l = 0; l < 16; ++l) {
                    double d2 = 1.25D * (double) (l + 1);
                    int j = 1 * l;
                    this.createSpellEntity(this.getX() + (double) Mth.cos(f) * d2, this.getZ() + (double) Mth.sin(f) * d2, d0, d1, f, j);
                }
            }

        }
    }



    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }





}
