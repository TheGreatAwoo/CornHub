package com.corn.callofthecorn.entities.harvest_crow;

import com.corn.callofthecorn.init.CornItems;

import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

public class HarvestCrow extends WitherBoss {

    public static int MAX_HP = 500;
    public static int AttackDamage = 20;
    public LivingEntity target;
    public double distance;
    private final int explosionPower = 1;
    public boolean flying;
    public int tickcount = 0;
    private final int[] nextHeadUpdate = new int[2];
    private final int[] idleHeadUpdates = new int[2];
    private int destroyBlocksTick;
    private final float[] xRotHeads = new float[2];
    private final float[] yRotHeads = new float[2];
    private final float[] xRotOHeads = new float[2];
    private final float[] yRotOHeads = new float[2];
    double XSTART;
    double YSTART;
    double ZSTART;


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


    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (p_31504_) -> {
        return p_31504_.getMobType() != MobType.UNDEAD && p_31504_.attackable();
    };

    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(LIVING_ENTITY_SELECTOR);


    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);


    public HarvestCrow(EntityType<? extends HarvestCrow> type, Level level) {
        super(type, level);
        this.getNavigation().setCanFloat(false);
    }


//    @Nullable
//    @Override
//    public Team getTeam() {
//        return Main.CTeam;
//    }

    @Override
    public int getExperienceReward() {
        return (super.getExperienceReward() * 15);

    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_31464_, int p_31465_, boolean p_31466_) {
        super.dropCustomDeathLoot(p_31464_, p_31465_, p_31466_);

        int r = random.nextInt(3);
        Item item = switch (r) {
            case 0 -> CornItems.KERNAL.get();
            case 1 -> CornItems.CROWSTAFF.get();
            default -> CornItems.HARVESTSTAFF.get();
        };
        ItemEntity itementity = this.spawnAtLocation(item);
        if (itementity != null) {
            itementity.setExtendedLifetime();
            itementity.setGlowingTag(true);
            itementity.setInvulnerable(true);
        }

    }

    @Override
    public void tick() {
        super.tick();
        this.getNavigation().setCanFloat(flying);

        if (tickcount == 300) {
            flying = true;
            //this.getNavigation().setCanFloat(true);
            XSTART = this.getX();
            YSTART = this.getY();
            ZSTART = this.getZ();
            LocationBoss(XSTART, ZSTART);


        }

        tickcount++;
        level().setRainLevel(100);
        if (this.getHealth() < 10) {
            level().setRainLevel(0);
        }
        if (this.getHealth() < this.getMaxHealth() / 2) {

            int xran = random.nextInt(200);
            int zran = random.nextInt(200);
            int yran = random.nextInt(100);
            double d0 = this.getHeadX(1);
            double d1 = this.getHeadY(2);
            double d2 = this.getHeadZ(3);
            double d3 = 1 - d0;
            double d4 = 2 - d1;
            double d5 = 3 - d2;
            WitherSkull witherskull = new WitherSkull(this.level(), this, d3, d4, d5);
            witherskull.setOwner(this);
            witherskull.setDangerous(true);
            witherskull.setPosRaw(d0 + xran - 100, d1 + 50 + yran, d2 + zran - 100);
            witherskull.xOld = witherskull.position().x;
            witherskull.zOld = witherskull.position().z;
            witherskull.yOld = witherskull.position().y + 1;
            witherskull.xPower = 0;
            witherskull.zPower = 0;
            witherskull.yPower = -1;
            this.level().addFreshEntity(witherskull);


        }
        if (this.getHealth() < this.getMaxHealth() / 4) {
            double d1 = 4.0D;
            Vec3 vec3 = this.getViewVector(1.0F);
            double d2 = (this.getX() + vec3.x * 4.0D);
            double d3 = this.getY(0.5D);
            double d4 = this.getZ() + vec3.z * 4.0D;
            int xran = random.nextInt(200);
            int zran = random.nextInt(200);

            LargeFireball largefireball = new LargeFireball(level(), this, d2, d3, d4, this.explosionPower);
            largefireball.setPos(this.getX() + xran - 100, this.getY() + 100, largefireball.getZ() + zran - 100);
            largefireball.xPower = 0;
            largefireball.zPower = 0;
            largefireball.yPower = -1;
            level().addFreshEntity(largefireball);
        }
    }

    @Override
    public void makeInvulnerable() {
        super.makeInvulnerable();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        target = p_31483_;
        flying = false;
        setGlowingTag(true);
    }


    private double getHeadX(int p_31515_) {
        if (p_31515_ <= 0) {
            return this.getX();
        } else {
            float f = (this.yBodyRot + (float) (180 * (p_31515_ - 1))) * ((float) Math.PI / 180F);
            float f1 = Mth.cos(f);
            return this.getX() + (double) f1 * 1.3D;
        }
    }


    private double getHeadY(int p_31517_) {
        return p_31517_ <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
    }

    private double getHeadZ(int p_31519_) {
        if (p_31519_ <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float) (180 * (p_31519_ - 1))) * ((float) Math.PI / 180F);
            float f1 = Mth.sin(f);
            return this.getZ() + (double) f1 * 1.3D;
        }
    }


    public void performRangedAttack2() {

        double d0 = this.getHeadX(1) - 30;
        double d1 = this.getHeadY(2);
        double d2 = this.getHeadZ(3) - 30;
        double d3 = 1 - d0;
        double d4 = 2 - d1;
        double d5 = 3 - d2;
        boolean action = false;
        int ran = new Random().nextInt(30);
        int mobLevel = 4;


        //Spawn
        if (ran < 5 && !flying) {
            Spawn(mobLevel);
            action = true;
        }

//TNTRUN
        if (ran < 10 && ran > 5) {
            TNTRUN(d0, d1, d2, d3, d4, d5, mobLevel);
            action = true;
        }

//SkyBlast
        if (ran > 25 && this.getHealth() * 3 < this.getMaxHealth() * 2) {
            SkyBlast(d0, d1, d2, d3, d4, d5);
            action = true;
        }

//FireBall
        if (ran < 16 && flying) {
            Fireball(d0, d1, d2, d3, d4, d5);
            action = true;
        }

//Attack
        if (!action) {
            CrowAttack(d0, d1, d2, d3, d4, d5);
        }


    }


    private void Spawn(int mobLevel) {
        System.out.println("Summon");
        for (int X = 0; X < mobLevel; X++) {
            EntityType<?> entitytype = CornMobs.SCARECROW.get();
            ItemStack itemstack = new ItemStack(Items.ZOMBIE_VILLAGER_SPAWN_EGG);
            BlockPos blockpos1;
            BlockPos blockpos = this.blockPosition();
            Direction direction = this.getDirection();
            blockpos1 = blockpos.relative(direction);
            entitytype.spawn((ServerLevel) level(), itemstack, target.level().getNearestPlayer(this, 0),
                    blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1)
                            && direction == Direction.UP);
        }
    }


    private void CrowAttack(double d0, double d1, double d2, double d3, double d4, double d5) {
        System.out.println("Attack");
        WitherSkull witherskull = new WitherSkull(this.level(), this, d3, d4, d5);
        witherskull.setOwner(this);
        witherskull.setDangerous(true);
        witherskull.setPosRaw(d0, d1, d2);
        this.level().addFreshEntity(witherskull);
    }


    private void Fireball(double d0, double d1, double d2, double d3, double d4, double d5) {

        System.out.println("Fireball");
        for (int x = 0; x < 10; x++) {

            d1 = 4.0D;
            Vec3 vec3 = this.getViewVector(1.0F);
            d2 = this.getX() + vec3.x * 4.0D;
            d3 = this.getY(0.5D);
            double d42 = this.getZ() + vec3.z * 4.0D;
            LargeFireball largefireball = new LargeFireball(level(), this, d2, d3, d42, this.explosionPower);
            largefireball.setPos(this.getX() + vec3.x * 4.0D, this.getY(0.5D) + 0.5D, largefireball.getZ() + vec3.z * 4.0D);
            largefireball.yPower = -10;
            double offset = new Random().nextInt(10) - 5;
            offset = offset / 10;
            largefireball.zPower = offset;
            offset = new Random().nextInt(10) - 5;
            offset = offset / 10;
            largefireball.xPower = offset;
            level().addFreshEntity(largefireball);
            tick();

        }
    }


    public void SkyBlast(double d0, double d1, double d2, double d3, double d4, double d5) {
        System.out.println("Skyblast");
        for (int x = 0; x < 20; x++) {
            int xran = random.nextInt(20);
            int zran = random.nextInt(20);

            LargeFireball largefireball = new LargeFireball(level(), this, d2, d3, d4, this.explosionPower);
            largefireball.setPos(this.getX() + xran - 10, this.getY() + 30, largefireball.getZ() + zran - 10);

            largefireball.xPower = 0;
            largefireball.zPower = 0;
            largefireball.yPower = -.5;
            level().addFreshEntity(largefireball);
        }
    }

    public void TNTRUN(double d0, double d1, double d2, double d3, double d4, double d5, int mobLevel) {
        System.out.println("TnT");
        for (int X = 0; X < mobLevel; X++) {
            PrimedTnt TnT = new PrimedTnt(this.level(), d3, d4, d5, null);
            this.level().addFreshEntity(TnT);
        }
    }

    @Override
    public void performRangedAttack(LivingEntity p_31468_, float p_31469_) {
        super.performRangedAttack(p_31468_, p_31469_);
    }

    @Override
    protected void customServerAiStep() {
        if (this.getInvulnerableTicks() > 0) {
            int k1 = this.getInvulnerableTicks() - 1;
            this.bossEvent.setProgress(1.0F - (float) k1 / 220.0F);
            if (k1 <= 0) {
                this.level().explode(this, this.getX(), this.getEyeY(), this.getZ(), 7.0F, false, Level.ExplosionInteraction.MOB);
                if (!this.isSilent()) {
                    this.level().globalLevelEvent(1023, this.blockPosition(), 0);
                }
            }

            this.setInvulnerableTicks(k1);
            if (this.tickCount % 10 == 0) {

            }

        } else {

            for (int i = 1; i < 3; ++i) {
                if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
                    this.nextHeadUpdate[i - 1] = this.tickCount + 10 + this.random.nextInt(10);
                    if (this.level().getDifficulty() == Difficulty.NORMAL || this.level().getDifficulty() == Difficulty.HARD) {
                        int i3 = i - 1;
                        int j3 = this.idleHeadUpdates[i - 1];
                        this.idleHeadUpdates[i3] = this.idleHeadUpdates[i - 1] + 1;
                        if (j3 > 15) {
                            float f = 10.0F;
                            float f1 = 5.0F;
                            double d0 = Mth.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
                            double d1 = Mth.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
                            double d2 = Mth.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);
                            this.performRangedAttack2();
                            this.idleHeadUpdates[i - 1] = 0;
                        }
                    }

                    int l1 = this.getAlternativeTarget(i);
                    if (l1 > 0) {
                        LivingEntity livingentity = (LivingEntity) this.level().getEntity(l1);
                        if (livingentity != null && this.canAttack(livingentity) && !(this.distanceToSqr(livingentity) > 900.0D) && this.hasLineOfSight(livingentity)) {
                            this.performRangedAttack2();
                            this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
                            this.idleHeadUpdates[i - 1] = 0;
                        } else {
                            this.setAlternativeTarget(i, 0);
                        }
                    } else {
                        List<LivingEntity> list = this.level().getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));
                        if (!list.isEmpty()) {
                            LivingEntity livingentity1 = list.get(this.random.nextInt(list.size()));
                            this.setAlternativeTarget(i, livingentity1.getId());
                        }
                    }
                }
            }

            if (this.getTarget() != null) {
                this.setAlternativeTarget(0, this.getTarget().getId());
            } else {
                this.setAlternativeTarget(0, 0);
            }

            if (this.destroyBlocksTick > 0) {
                --this.destroyBlocksTick;
                if (this.destroyBlocksTick == 0 && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this)) {
                    int j1 = Mth.floor(this.getY());
                    int i2 = Mth.floor(this.getX());
                    int j2 = Mth.floor(this.getZ());
                    boolean flag = false;

                    for (int j = -1; j <= 1; ++j) {
                        for (int k2 = -1; k2 <= 1; ++k2) {
                            for (int k = 0; k <= 3; ++k) {
                                int l2 = i2 + j;
                                int l = j1 + k;
                                int i1 = j2 + k2;
                                BlockPos blockpos = new BlockPos(l2, l, i1);
                                BlockState blockstate = this.level().getBlockState(blockpos);
                                if (blockstate.canEntityDestroy(this.level(), blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                                    flag = this.level().destroyBlock(blockpos, true, this) || flag;
                                }
                            }
                        }
                    }

                    if (flag) {
                        this.level().levelEvent(null, 1022, this.blockPosition(), 0);
                    }
                }
            }


            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        }

        super.customServerAiStep();

    }

    double[] Xlocations = new double[9];
    double[] Zlocations = new double[9];

    public int count = 0;

    public void LocationBoss(double x, double z) {
        int distance = 40;
        Xlocations[0] = x - distance;
        Xlocations[1] = x;
        Xlocations[2] = x + distance;
        Xlocations[3] = x + distance;
        Xlocations[4] = x + distance;
        Xlocations[5] = x;
        Xlocations[6] = x - distance;
        Xlocations[7] = x - distance;
        Xlocations[8] = x;

        Zlocations[0] = z + distance;
        Zlocations[1] = z + distance;
        Zlocations[2] = z + distance;
        Zlocations[3] = z;
        Zlocations[4] = z - distance;
        Zlocations[5] = z - distance;
        Zlocations[6] = z - distance;
        Zlocations[7] = z;
        Zlocations[8] = z;
    }

    int Error = 3;

    @Override
    public void aiStep() {
        Vec3 vec3 = null;
        if (!flying) {

            vec3 = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
            vec3.add(0, -100, 0);
            if (!this.level().isClientSide && this.getAlternativeTarget(0) > 0) {
                Entity entity = this.level().getEntity(this.getAlternativeTarget(0));
                if (entity != null) {
                    double d0 = vec3.y;
                    if (this.getY() < entity.getY() || !this.isPowered() && this.getY() < entity.getY() + 5.0D) {
                        d0 = Math.max(0.0D, d0);
                        d0 = d0 + (0.3D - d0 * (double) 0.6F);
                    }

                    vec3 = new Vec3(vec3.x, -5, vec3.z);
                    Vec3 vec31 = new Vec3(entity.getX() - this.getX(), -5, entity.getZ() - this.getZ());
                    if (vec31.horizontalDistanceSqr() > 9.0D) {
                        Vec3 vec32 = vec31.normalize();
                        vec3 = vec3.add(vec32.x * 0.3D - vec3.x * 0.6D, -5, vec32.z * 0.3D - vec3.z * 0.6D);
                    }
                }
            }


        } else {
            double Ydif;
            if (this.getY() < YSTART + 20) {
                Ydif = .5F;
            } else Ydif = -.5;

            if (count < 9) {

                int ran = new Random().nextInt(100);
                if (ran < 10 && flying) {
                    PrimedTnt TnT = new PrimedTnt(this.level(), this.getX(), this.getY(), this.getZ(), null);
                    this.level().addFreshEntity(TnT);


                }

//         if(Math.sqrt(this.getZ()*this.getZ()+this.getX()*this.getX())>20+Math.sqrt(this.XSTART*this.XSTART+this.ZSTART*this.ZSTART)) {
                double diffX = Xlocations[count] - this.getX();
                double diffZ = Zlocations[count] - this.getZ();
                vec3 = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
                vec3 = vec3.add(0.01 * (diffX), Ydif, 0.01 * (diffZ));
//        }
//        else  vec3 = this.getDeltaMovement().add(1D,Ydif,1D);

                if (this.getX() < Xlocations[count] + Error && this.getX() > Xlocations[count] - Error && this.getZ() > Zlocations[count] - Error && this.getZ() < Zlocations[count] + Error) {
                    count++;
                }

            } else {
                tickcount = -1700;
                flying = false;
                this.getNavigation().setCanFloat(false);
                count = 0;
                vec3 = this.getDeltaMovement().multiply(0.1D, 0.1D, 0.1D);
                vec3.add(0, -200, 0);
            }
        }


        this.setDeltaMovement(vec3);

        if (vec3.horizontalDistanceSqr() > 0.05D) {
            this.setYRot((float) Mth.atan2(vec3.z, vec3.x) * (180F / (float) Math.PI) - 90.0F);
        }

        for (int i = 0; i < 2; ++i) {
            this.yRotOHeads[i] = this.yRotHeads[i];
            this.xRotOHeads[i] = this.xRotHeads[i];
        }

        for (int j = 0; j < 2; ++j) {
            int k = this.getAlternativeTarget(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = this.level().getEntity(k);
            }

            if (entity1 != null) {
                double d9 = this.getHeadX(j + 1);
                double d1 = this.getHeadY(j + 1);
                double d3 = this.getHeadZ(j + 1);
                double d4 = entity1.getX() - d9;
                double d5 = entity1.getEyeY() - d1;
                double d6 = entity1.getZ() - d3;
                double d7 = Math.sqrt(d4 * d4 + d6 * d6);
                float f = (float) (Mth.atan2(d6, d4) * (double) (180F / (float) Math.PI)) - 90.0F;
                float f1 = (float) (-(Mth.atan2(d5, d7) * (double) (180F / (float) Math.PI)));
//                this.xRotHeads[j] = this.rotlerp(this.xRotHeads[j], f1, 40.0F);
//                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], f, 10.0F);
//            }
//            else {
//                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], this.yBodyRot, 10.0F);
            }

        }

        boolean flag = this.isPowered();

        for (int l = 0; l < 3; ++l) {
            double d8 = this.getHeadX(l);
            double d10 = this.getHeadY(l);
            double d2 = this.getHeadZ(l);
            this.level().addParticle(ParticleTypes.SMOKE, d8 + this.random.nextGaussian() * (double) 0.3F, d10 + this.random.nextGaussian() * (double) 0.3F, d2 + this.random.nextGaussian() * (double) 0.3F, 0.0D, 0.0D, 0.0D);
            if (flag && this.level().random.nextInt(4) == 0) {
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, d8 + this.random.nextGaussian() * (double) 0.3F, d10 + this.random.nextGaussian() * (double) 0.3F, d2 + this.random.nextGaussian() * (double) 0.3F, 0.7F, 0.7F, 0.5D);
            }
        }

        if (this.getInvulnerableTicks() > 0) {
            for (int i1 = 0; i1 < 3; ++i1) {
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + this.random.nextGaussian(), this.getY() + (double) (this.random.nextFloat() * 3.3F), this.getZ() + this.random.nextGaussian(), 0.7F, 0.7F, 0.9F);
            }
        }


        super.aiStep();

    }


}




