package com.corn.callofthecorn.entities.harvester;

import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DescendGoal<T extends Harvester> extends Goal {
    private final T mob;
    private final float hpThreshold;
    private final float healrate;

    public DescendGoal(T mob, float hpThreshold, float healrate) {
        this.mob = mob;
        this.hpThreshold = hpThreshold;
        this.healrate = healrate;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        return mob.getTarget() == null && mob.isNoGravity();
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        mob.fallDistance = 0;
        mob.setDeltaMovement(0,-0.15f,0);
        if(mob.getHealth() < hpThreshold) {
            mob.heal(Math.min(healrate, hpThreshold-mob.getHealth()));
        }
    }
}
