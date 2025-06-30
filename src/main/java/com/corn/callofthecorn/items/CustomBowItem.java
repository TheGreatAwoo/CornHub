package com.corn.callofthecorn.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CustomBowItem extends BowItem {
    
    int damage;
    boolean fire;
    int drawTicks;

    public CustomBowItem(Properties properties, int damage, int drawTicks, boolean fire) {
        super(properties);
        this.damage = damage;
        this.fire = fire;
        this.drawTicks = drawTicks;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 300;
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
        arrow.setBaseDamage(damage);
        if(fire) {
            arrow.igniteForTicks(100);
        }
        return arrow;
    }

    // duplicated to replace getPowerForTime because it's static
    @Override
    public boolean releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        if (!(p_40669_ instanceof Player player)) {
            return false;
        } else {
            ItemStack itemstack = player.getProjectile(p_40667_);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                int i = this.getUseDuration(p_40667_, p_40669_) - p_40670_;
                i = net.neoforged.neoforge.event.EventHooks.onArrowLoose(p_40667_, p_40668_, player, i, !itemstack.isEmpty());
                if (i < 0) return false;
                float f = getCustomPowerForTime(i); // only modification
                if (f < 0.1) {
                    return false;
                } else {
                    List<ItemStack> list = draw(p_40667_, itemstack, player);
                    if (p_40668_ instanceof ServerLevel serverlevel && !list.isEmpty()) {
                        this.shoot(serverlevel, player, player.getUsedItemHand(), p_40667_, list, f * 3.0F, 1.0F, f == 1.0F, null);
                    }

                    p_40668_.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (p_40668_.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return true;
                }
            }
        }
    }


    private float getCustomPowerForTime(int ticks) {
        float f = ticks / (float) drawTicks;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

}
