package com.corn.callofthecorn.items;

import com.corn.callofthecorn.entities.LightingBall;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
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
    public boolean releaseUsing(ItemStack staffStack, Level level, LivingEntity user, int duration) {
        if (user instanceof Player) {
            Player player = (Player) user;

            Vec3 dir = user.getViewVector(1.0F);
            Vec3 v = dir.scale(3);

            boolean creative = player.getAbilities().instabuild;
            ItemStack projectileStack = player.getProjectile(staffStack);

            int i = this.getUseDuration(staffStack, user) - duration;
            i = net.neoforged.neoforge.event.EventHooks.onArrowLoose(staffStack, level, player, i, !projectileStack.isEmpty() || creative);
            if (i < 0) return false;

            if (!projectileStack.isEmpty() || creative) {
                if (projectileStack.isEmpty()) {
                    projectileStack = new ItemStack(fuel.get());
                }
                LargeFireball projectileEntity;
                if (lightning) {
                    projectileEntity = new LightingBall(level, user, v, this.explosionPower);
                } else {
                    projectileEntity = new LargeFireball(level, user, v, this.explosionPower);
                }
                projectileEntity.setPos(user.getX() + dir.x * 4.0D, user.getY(0.5D) + 0.5D, user.getZ() + dir.z * 4.0D);

                level.addFreshEntity(projectileEntity);
                player.awardStat(Stats.ITEM_USED.get(this));
                player.getCooldowns().addCooldown(staffStack,100);
                staffStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));

                projectileStack.shrink(1);
                if (projectileStack.isEmpty()) {
                    player.getInventory().removeItem(projectileStack);
                }
                return true;
            }
        }
        return false;
    }

}

