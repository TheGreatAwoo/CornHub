package com.corn.callofthecorn.entities;

import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LightingBall extends LargeFireball {

    public LightingBall(Level p_181151_, LivingEntity p_181152_, double p_181153_, double p_181154_, double p_181155_, int p_181156_) {
        super(p_181151_, p_181152_, p_181153_, p_181154_, p_181155_, p_181156_);
    }


    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        for(int dx = 0;dx < 3; dx++){
            for(int dz = 0;dz < 3; dz++){
                LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                Vec3i pos = new Vec3i(this.blockPosition().getX()-1+dx, this.blockPosition().getY(), this.blockPosition().getZ()-1+dz);
                lightningbolt.moveTo(Vec3.atBottomCenterOf(pos));
                this.level().addFreshEntity(lightningbolt);
            }
        }
    }


    @Override
    public void tick() {
        LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
        lightningbolt.moveTo(Vec3.atBottomCenterOf(this.blockPosition()));
        this.level().addFreshEntity(lightningbolt);
        super.tick();
    }
}

