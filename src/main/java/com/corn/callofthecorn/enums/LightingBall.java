package com.corn.callofthecorn.enums;

import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LightingBall extends LargeFireball {

    int explosionPower=0;

    public LightingBall(Level p_181151_, LivingEntity p_181152_, double p_181153_, double p_181154_, double p_181155_, int p_181156_) {
        super(p_181151_, p_181152_, p_181153_, p_181154_, p_181155_, p_181156_);
    }


    @Override
    protected void onHit(HitResult p_37218_) {
        super.onHit(p_37218_);
        if (!this.level.isClientSide) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
            this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, flag ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE);


            for(int X=0;X<3;X++){
            for(int Y=0;Y<3;Y++){

            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level);
            Vec3i Pos = new Vec3i(this.blockPosition().getX()-1+X,this.blockPosition().getY(),this.blockPosition().getZ()-1+Y);
            lightningbolt.moveTo(Vec3.atBottomCenterOf(Pos));
//            lightningbolt.setCause(p_34169_ instanceof ServerPlayer ? (ServerPlayer)p_34169_ : null);
            this.level.addFreshEntity(lightningbolt);}}



            this.discard();
        }

    }


    @Override
    public void tick() {
            Entity entity = this.getOwner();
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level);
            lightningbolt.moveTo(Vec3.atBottomCenterOf(this.blockPosition()));
//            lightningbolt.setCause(p_34169_ instanceof ServerPlayer ? (ServerPlayer)p_34169_ : null);
        this.level.addFreshEntity(lightningbolt);
            if (this.level.isClientSide || (entity == null || !entity.isRemoved()) && this.level.hasChunkAt(this.blockPosition())) {
                super.tick();
                if (this.shouldBurn()) {
                    this.setSecondsOnFire(1);
                }

                HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
                if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                    this.onHit(hitresult);
                }

                this.checkInsideBlocks();
                Vec3 vec3 = this.getDeltaMovement();
                double d0 = this.getX() + vec3.x;
                double d1 = this.getY() + vec3.y;
                double d2 = this.getZ() + vec3.z;
                ProjectileUtil.rotateTowardsMovement(this, 0.2F);
                float f = this.getInertia();
                if (this.isInWater()) {
                    for(int i = 0; i < 4; ++i) {
                        float f1 = 0.25F;
                        this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25D, d1 - vec3.y * 0.25D, d2 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
                    }

                    f = 0.8F;
                }

                this.setDeltaMovement(vec3.add(this.xPower, this.yPower, this.zPower).scale((double)f));
                this.level.addParticle(this.getTrailParticle(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
                this.setPos(d0, d1, d2);
            } else {
                this.discard();
            }
        }






    }

