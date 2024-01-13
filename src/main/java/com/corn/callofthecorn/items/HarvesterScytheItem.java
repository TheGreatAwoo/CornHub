package com.corn.callofthecorn.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.Random;

public class HarvesterScytheItem extends HoeItem {
    public HarvesterScytheItem(Tier p_41336_, int p_41337_, float p_41338_, Properties p_41339_) {
        super(p_41336_, p_41337_, p_41338_, p_41339_);
    }

    LivingEntity livingentity = null;
    LivingEntity player = null;

    @Override
    public boolean hurtEnemy(ItemStack p_40994_, LivingEntity p_40995_, LivingEntity p_40996_) {

        player=p_40996_;
        livingentity=p_40995_;
            Random ran = new Random();
            int hold = ran.nextInt(3);
            if(hold==1)p_40995_.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,80));
            if(hold==2)p_40995_.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,80));
            if(hold==3)p_40995_.addEffect(new MobEffectInstance(MobEffects.CONFUSION,80));

            return super.hurtEnemy(p_40994_, p_40995_, p_40996_);}


    @Override
    public InteractionResult useOn(UseOnContext p_41341_) {
       if(livingentity!=null&&player!=null)performSpellCasting2();
        p_41341_.getPlayer().getCooldowns().addCooldown(this,50);
        return InteractionResult.PASS;
    }


    protected void performSpellCasting2() {

        double d0 = Math.min(livingentity.getY(), player.getY());
        double d1 = Math.max(livingentity.getY(), player.getY()) + 1.0D;
        float f = (float)Mth.atan2(livingentity.getZ() - player.getZ(), livingentity.getX() - player.getX());
        if (player.distanceToSqr(livingentity) < 9.0D) {
            for(int i = 0; i < 5; ++i) {
                float f1 = f + (float)i * (float)Math.PI * 0.4F;
                this.createSpellEntity(player.getX() + (double)Mth.cos(f1) * 1.5D, player.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
            }

            for(int k = 0; k < 8; ++k) {
                float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
                this.createSpellEntity(player.getX() + (double)Mth.cos(f2) * 2.5D, player.getZ() + (double)Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
            }
        } else {
            for(int l = 0; l < 16; ++l) {
                double d2 = 1.25D * (double)(l + 1);
                int j = 1 * l;
                this.createSpellEntity(player.getX() + (double)Mth.cos(f) * d2, player.getZ() + (double)Mth.sin(f) * d2, d0, d1, f, j);
            }
        }

    }




    private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
        BlockPos blockpos = BlockPos.containing(p_32673_, p_32676_, p_32674_);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = player.level().getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(player.level(), blockpos1, Direction.UP)) {
                if (!player.level().isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = player.level().getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(player.level(), blockpos);
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
            player.level().addFreshEntity(new EvokerFangs(player.level(), p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, player));
        }

    }

}
