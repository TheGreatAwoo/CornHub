package com.corn.callofthecorn.entities.harvester;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.BowItem;

import java.util.EnumSet;

public class HarvesterAttackGoal <T extends Harvester> extends Goal {
    private final T mob;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
    private static final int ATTACK_TIME_AFTER_SIGHT_LOSS = 60; // Keep firing at target for 3s after LoS is lost

    public <M extends Monster & RangedAttackMob> HarvesterAttackGoal(M mob, double speedMod, int attackIntervalMin, float range){
        this((T) mob, speedMod, attackIntervalMin, range);
    }

    public HarvesterAttackGoal(T mob, double speedMod, int attackIntervalMin, float range) {
        this.mob = mob;
        this.speedModifier = speedMod;
        this.attackIntervalMin = attackIntervalMin;
        this.attackRadiusSqr = range * range;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setMinAttackInterval(int interval) {
        this.attackIntervalMin = interval;
    }

    public boolean canUse() {
        return this.mob.getTarget() != null;
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone());
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.stopUsingItem();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            double dSq = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean canSee = this.mob.getSensing().hasLineOfSight(target);
            boolean isSeeing = this.seeTime > 0;
            if (canSee != isSeeing) {
                this.seeTime = 0;
            }

            if (canSee) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (dSq <= (double)this.attackRadiusSqr && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.mob.getNavigation().moveTo(target, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (dSq > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (dSq < (double)(this.attackRadiusSqr * 0.5F)) {
                    this.strafingBackwards = true;
                }

                if(this.mob.isNoGravity()) {
                    flyMove();
                } else {
                    this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                }
                this.mob.lookAt(target, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }

            if (--this.attackTime <= 0 && this.seeTime >= -ATTACK_TIME_AFTER_SIGHT_LOSS) {
                this.mob.castSpell(target, canSee);
                this.attackTime = this.attackIntervalMin;
            }

        } else if (mob.isNoGravity()) {
            mob.fallDistance = 0;
            mob.setDeltaMovement(0,-0.2f,0);
        }
    }

    private void flyMove() {
        float fwdAmount = this.strafingBackwards ? -0.1F : 0.1F;
        float rightAmount = this.strafingClockwise ? 0.1F : -0.1F;

        float sin = Mth.sin(this.mob.getYRot() * ((float)Math.PI / 180F));
        float cos = Mth.cos(this.mob.getYRot() * ((float)Math.PI / 180F));
        float x = fwdAmount * cos - rightAmount * sin;
        float z = rightAmount * cos + fwdAmount * sin;
        float y = 0;
        if(mob.getY() < mob.getTarget().getY() + 3.5) {
            y = 0.12f;
        } else if(mob.getY() > mob.getTarget().getY() + 12) {
            y = -0.12f;
        }
        mob.fallDistance = 0;
        mob.setDeltaMovement(x,y,z);

    }
}
