package com.corn.callofthecorn.items;

import com.corn.callofthecorn.entities.LightingBall;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class CustomStaffItem extends BowItem {

    private final int explosionPower;
    private final boolean lightning;
    private final Supplier<Item> fuel;

    public CustomStaffItem(Properties properties, int explosionPower, boolean lightning, Supplier<Item> fuel) {
        super(properties);
        this.explosionPower = explosionPower;
        this.lightning = lightning;
        this.fuel = fuel;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        if(lightning) {
            return 50;
        }
        return 200 / explosionPower;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return  (itemStack) -> itemStack.is(fuel.get());
    }

    @Override
    public void releaseUsing(ItemStack staffStack, Level level, LivingEntity user, int duration) {
        if (user instanceof Player) {
            Player player = (Player) user;

            Vec3 vec3 = user.getViewVector(1.0F);
            double d2 = user.getX() + vec3.x * 4.0D;
            double d3 = user.getY(0.5D) + 0.5D;
            double d4 = user.getZ() + vec3.z * 4.0D;

            boolean creative = player.getAbilities().instabuild;
            ItemStack projectileStack = player.getProjectile(staffStack);

            int i = this.getUseDuration(staffStack) - duration;
            i = net.neoforged.neoforge.event.EventHooks.onArrowLoose(staffStack, level, player, i, !projectileStack.isEmpty() || creative);
            if (i < 0) return;

            if (!projectileStack.isEmpty() || creative) {
                if (projectileStack.isEmpty()) {
                    projectileStack = new ItemStack(fuel.get());
                }
                LargeFireball projectileEntity;
                if (!lightning) {
                    projectileEntity = new LargeFireball(level, user, d2, d3, d4, this.explosionPower);
                } else {
                    projectileEntity = new LightingBall(level, user, d2, d3, d4, this.explosionPower);
                }
                projectileEntity.setPos(user.getX() + vec3.x * 4.0D, user.getY(0.5D) + 0.5D, user.getZ() + vec3.z * 4.0D);

                projectileEntity.yPower = vec3.y;
                projectileEntity.zPower = vec3.z;
                projectileEntity.xPower = vec3.x;
                level.addFreshEntity(projectileEntity);
                player.awardStat(Stats.ITEM_USED.get(this));
                player.getCooldowns().addCooldown(this,100);
                staffStack.hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(player.getUsedItemHand());
                });

                projectileStack.shrink(1);
                if (projectileStack.isEmpty()) {
                    player.getInventory().removeItem(projectileStack);
                }
            }
        }
    }


    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

}

