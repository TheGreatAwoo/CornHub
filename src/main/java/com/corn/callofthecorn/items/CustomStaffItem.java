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

public class CustomStaffItem extends BowItem {

    private int explosionPower;
    private boolean boss;

    private int MaxDura;
    private int CurrentDura;

    private Item Itemused;

    public CustomStaffItem(Properties p_40660_, int explosionPower, boolean boss) {
        super(p_40660_);
        this.explosionPower = explosionPower;
        this.boss=boss;

        this.MaxDura=100;
        this.CurrentDura=100;
//        this.getDurabilityForDisplay(new ItemStack(this));
//        this.showDurabilityBar(new ItemStack(this));

    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return MaxDura-CurrentDura;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return MaxDura;
    }


//    @Override
//    public int getItemStackLimit(ItemStack stack) {
//        return 1;
//    }


    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return  ((p_43015_) -> {

            if(boss==false){
                Itemused= CornItems.LESSERSOUL.get();
            return p_43015_.is(CornItems.CORNSOUL.get());
                }
            Itemused= CornItems.LESSERSOUL.get();
            return p_43015_.is(CornItems.LESSERSOUL.get());

        });


    }

    @Override
    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {


        if (p_40669_ instanceof Player) {
            Player player = (Player) p_40669_;

            Level level = p_40668_;
            Vec3 vec3 = p_40669_.getViewVector(1.0F);
            double d2 = p_40669_.getX() + vec3.x * 4.0D;
            double d3 = p_40669_.getY(0.5D) + 0.5D;
            double d4 = p_40669_.getZ() + vec3.z * 4.0D;



            boolean flag = player.getAbilities().instabuild;
            ItemStack itemstack = player.getProjectile(p_40667_);

            int i = this.getUseDuration(p_40667_) - p_40670_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_40667_, p_40668_, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Itemused);
                }


                if (CurrentDura >= explosionPower * 1 && boss == false || boss == true && CurrentDura >= 4) {

                    if (boss == false) {


                        LargeFireball largefireball = new LargeFireball(level, p_40669_, d2, d3, d4, this.explosionPower);
                        largefireball.setPos(p_40669_.getX() + vec3.x * 4.0D, p_40669_.getY(0.5D) + 0.5D, p_40669_.getZ() + vec3.z * 4.0D);

                        largefireball.yPower = vec3.y;
                        largefireball.zPower = vec3.z;
                        largefireball.xPower = vec3.x;
                        level.addFreshEntity(largefireball);
                        player.awardStat(Stats.ITEM_USED.get(this));
//                    CurrentDura-=1*explosionPower;
//                        this.setDamage((new ItemStack(this)), explosionPower);
                        player.getCooldowns().addCooldown(this,100);

                    } else {


                        LightingBall largefireball = new LightingBall(level, p_40669_, d2, d3, d4, this.explosionPower);
                        largefireball.setPos(p_40669_.getX() + vec3.x * 4.0D, p_40669_.getY(0.5D) + 0.5D, p_40669_.getZ() + vec3.z * 4.0D);

                        largefireball.yPower = vec3.y;
                        largefireball.zPower = vec3.z;
                        largefireball.xPower = vec3.x;
                        level.addFreshEntity(largefireball);
                        player.awardStat(Stats.ITEM_USED.get(this));
//                    CurrentDura-=4;
//                        this.setDamage((new ItemStack(this)), explosionPower);
                        player.getCooldowns().addCooldown(this,100);

                    }
                } else {
                    player.getInventory().removeItem((new ItemStack(this)));

                }

                itemstack.shrink(1);
                if (itemstack.isEmpty()) {
                    player.getInventory().removeItem(itemstack);
                }



//                this.getDurabilityForDisplay(new ItemStack(this));


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

//
//    @Override
//    public Predicate<ItemStack> getSupportedHeldProjectiles() {
//        return super.getSupportedHeldProjectiles();
//    }
//
//    @Override
//    public int getDefaultProjectileRange() {
//        return 30;
//    }
//
//    @Override
//    public Predicate<ItemStack> getAllSupportedProjectiles() {
//        return  ((p_43015_) -> {
//            return p_43015_.is(Items.GUNPOWDER);
//        });

//
//    }


}

