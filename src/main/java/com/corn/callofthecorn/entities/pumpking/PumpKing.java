package com.corn.callofthecorn.entities.pumpking;

import com.corn.callofthecorn.init.CornItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
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

    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(CornItems.PUMPKINGSCROWN.get()));
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public int getExperienceReward (){        return (super.getExperienceReward()*15);

    }

    public LivingEntity target = null;
    int timer = 0;

    @Override
    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        this.bossEvent.addPlayer(p_31483_);
        target=(LivingEntity)p_31483_;
        this.moveTo(target.getX(),target.getY(),target.getZ());
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
    protected void dropCustomDeathLoot(DamageSource p_31464_, int p_31465_, boolean p_31466_) {
        super.dropCustomDeathLoot(p_31464_, p_31465_, p_31466_);


        ItemEntity itementity = this.spawnAtLocation(CornItems.GREATERSOUL.get());
        itementity = this.spawnAtLocation(CornItems.GREATERSOUL.get());
        itementity = this.spawnAtLocation(CornItems.GREATERSOUL.get());

        Random ran = new Random();
        if(ran.nextInt(5)==0){ itementity = this.spawnAtLocation(CornItems.PUMPKINGSCROWN.get());}



        if (itementity != null) {
            itementity.setExtendedLifetime();
        }}



    @Override
    public void performRangedAttack(LivingEntity p_34143_, float p_34144_) {
            Random rand = new Random();
            int val =rand.nextInt(100);
            performSpellCasting();

            if(val<25){performSpellCasting2();}
        if(val>25&&val<75){throwPotion( p_34143_,  p_34144_);}
        if(val>75&&val<90){performSpellCastingBlind();}
        if(val>90){Clone() ;}
}


protected void Clone()   {
    if (this.level().isClientSide ) {
        double d0 = 0;
        double d1 = 0;
        double d2 = 0;
        float f = this.yBodyRot * ((float)Math.PI / 180F) + Mth.cos((float)this.tickCount * 0.6662F) * 0.25F;
        float f1 = Mth.cos(f);
        float f2 = Mth.sin(f);
        this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() + (double)f2 * 0.6D, d0, d1, d2);
        this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() - (double)f1 * 0.6D, this.getY() + 1.8D, this.getZ() - (double)f2 * 0.6D, d0, d1, d2);
    }

    }


    protected void performSpellCastingBlind() {
        if( this.getTarget()!=null){
        this.getTarget().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400), this);}
    }
    private void throwPotion(LivingEntity p_34143_, float p_34144_){


        if (!this.isDrinkingPotion()) {
            Vec3 vec3 = p_34143_.getDeltaMovement();
            double d0 = p_34143_.getX() + vec3.x - this.getX();
            double d1 = p_34143_.getEyeY() - (double) 1.1F - this.getY();
            double d2 = p_34143_.getZ() + vec3.z - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            Potion potion = Potions.STRONG_HARMING;
            if (p_34143_ instanceof Raider) {
                if (p_34143_.getHealth() <= 4.0F) {
                    potion = Potions.HEALING;
                } else {
                    potion = Potions.STRONG_REGENERATION;
                }

                this.setTarget((LivingEntity) null);
            } else if (d3 >= 8.0D && !p_34143_.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                potion = Potions.STRONG_SLOWNESS;
            } else if (p_34143_.getHealth() >= 8.0F && !p_34143_.hasEffect(MobEffects.POISON)) {
                potion = Potions.STRONG_POISON;
            } else if (d3 <= 3.0D && !p_34143_.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                potion = Potions.LONG_WEAKNESS;
            }

            ThrownPotion thrownpotion = new ThrownPotion(this.level(), this);
            thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
            thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
            thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level().addFreshEntity(thrownpotion);
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
