package com.corn.callofthecorn.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class HarvesterScytheItem extends HoeItem {
    public HarvesterScytheItem(ToolMaterial p_41336_, float dmg, float speed, Properties p_41339_) {
        super(p_41336_, dmg, speed, p_41339_);
    }

    LivingEntity lockOnTarget = null;

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity enemy, LivingEntity user) {
        lockOnTarget = enemy;
        Random ran = new Random();
        int effect = ran.nextInt(3);
        if(effect==0) enemy.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,80));
        if(effect==1) enemy.addEffect(new MobEffectInstance(MobEffects.SLOWNESS,80));
        if(effect==2) enemy.addEffect(new MobEffectInstance(MobEffects.NAUSEA,80));

        super.hurtEnemy(stack, enemy, user);
    }


    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if(lockOnTarget !=null &&  ctx.getPlayer() != null && lockOnTarget.isAlive()) {
            performSpellCasting2(ctx.getPlayer());
            ctx.getPlayer().getCooldowns().addCooldown(ctx.getItemInHand(), 50);
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(lockOnTarget !=null && lockOnTarget.isAlive()) {
            performSpellCasting2(player);
            player.getCooldowns().addCooldown(player.getItemInHand(hand), 50);
        }
        return InteractionResult.PASS;
    }


    protected void performSpellCasting2(LivingEntity user) {
        double d0 = Math.min(lockOnTarget.getY(), user.getY());
        double d1 = Math.max(lockOnTarget.getY(), user.getY()) + 1.0D;
        float f = (float)Mth.atan2(lockOnTarget.getZ() - user.getZ(), lockOnTarget.getX() - user.getX());
        if (user.distanceToSqr(lockOnTarget) < 9.0D) {
            for(int i = 0; i < 5; ++i) {
                float f1 = f + (float)i * (float)Math.PI * 0.4F;
                this.createSpellEntity(user, user.getX() + (double)Mth.cos(f1) * 1.5D, user.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
            }

            for(int k = 0; k < 8; ++k) {
                float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
                this.createSpellEntity(user, user.getX() + (double)Mth.cos(f2) * 2.5D, user.getZ() + (double)Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
            }
        } else {
            for(int l = 0; l < 16; ++l) {
                double d2 = 1.25D * (double)(l + 1);
                int j = 1 * l;
                this.createSpellEntity(user, user.getX() + (double)Mth.cos(f) * d2, user.getZ() + (double)Mth.sin(f) * d2, d0, d1, f, j);
            }
        }

    }

    private void createSpellEntity(LivingEntity user, double x, double z, double p_32675_, double y, float p_32677_, int p_32678_) {
        BlockPos blockpos = BlockPos.containing(x, y, z);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = user.level().getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(user.level(), blockpos1, Direction.UP)) {
                if (!user.level().isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = user.level().getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(user.level(), blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

        if (flag) {
            user.level().addFreshEntity(new EvokerFangs(user.level(), x, (double)blockpos.getY() + d0, z, p_32677_, p_32678_, user));
        }

    }

}
