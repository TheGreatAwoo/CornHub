package com.corn.callofthecorn.entities.scarecrow;

import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ScarecrowFreezeGoal<T extends Scarecrow> extends Goal {
    private final T mob;

    public ScarecrowFreezeGoal(T mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.TARGET));
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone());
    }

    public void start() {
        super.start();
        this.mob.setAggressive(false);
        this.mob.stopUsingItem();
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(true);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canUse() {
        return !this.mob.active;
    }

    public void tick() {
        mob.getMoveControl().setWantedPosition(mob.position().x, mob.position().y,mob.position().z, 0);
    }
}
