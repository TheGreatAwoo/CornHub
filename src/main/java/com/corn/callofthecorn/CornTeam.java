package com.corn.callofthecorn;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.scores.Team;

import java.util.Collection;

public class CornTeam extends Team {
    @Override
    public String getName() {
        return "CornTeam";
    }

    @Override
    public MutableComponent getFormattedName(Component p_83538_) {
        return null;
    }

    @Override
    public boolean canSeeFriendlyInvisibles() {
        return false;
    }

    @Override
    public boolean isAllowFriendlyFire() {
        return false;
    }

    @Override
    public Visibility getNameTagVisibility() {
        return null;
    }

    @Override
    public ChatFormatting getColor() {
        return null;
    }

    @Override
    public Collection<String> getPlayers() {
        return null;
    }

    @Override
    public Visibility getDeathMessageVisibility() {
        return null;
    }

    @Override
    public CollisionRule getCollisionRule() {
        return null;
    }
}
