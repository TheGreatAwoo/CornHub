package com.corn.callofthecorn.enums;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;

public class CreamedCorn extends MilkBucketItem {
    public CreamedCorn(Properties p_42921_) {
        super(p_42921_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_42923_, Level p_42924_, LivingEntity p_42925_) {
        //if (!p_42924_.isClientSide) p_42925_.curePotionEffects(p_42923_); // FORGE - move up so stack.shrink does not turn stack into air


        if (p_42925_ instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, p_42923_);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (p_42925_ instanceof Player && !((Player)p_42925_).getAbilities().instabuild) {
            p_42923_.shrink(1);
        }

        if (!p_42924_.isClientSide) p_42925_.addEffect((new MobEffectInstance(MobEffects.HEALTH_BOOST,1000)));
        if (!p_42924_.isClientSide) p_42925_.addEffect((new MobEffectInstance(MobEffects.REGENERATION,1000)));

        return p_42923_.isEmpty() ? new ItemStack(Items.BUCKET) : p_42923_;
    }




}
