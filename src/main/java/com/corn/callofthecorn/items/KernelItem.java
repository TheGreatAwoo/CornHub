package com.corn.callofthecorn.items;

import com.corn.callofthecorn.items.armour.CalcStack;
import com.google.common.collect.Multimap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class KernelItem extends AxeItem {
    public KernelItem(Tier p_40521_, float p_40522_, float p_40523_, Properties p_40524_) {

        super(p_40521_, p_40522_, p_40523_, p_40524_);
    }

    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    boolean toddle = false; // TODO this is shared across all kernels, so may lead to odd behaviour
    int Damage = 4;
    Player player;

    @Override
    public InteractionResult useOn(UseOnContext p_40529_) {

        Level level = p_40529_.getLevel();
        BlockPos blockpos = p_40529_.getClickedPos();
        player = p_40529_.getPlayer();
        BlockState blockstate = level.getBlockState(blockpos);
        ItemStack itemstack = p_40529_.getItemInHand();
        Optional<BlockState> optional3 = Optional.empty();


        if (!player.isCrouching()) {
            Enchant();
        }


        if (player.isCrouching() || toddle) {
            toddle = !toddle;
            //player.setInvisible(toddle);
            double rot = player.getYRot();
            rot = rot / 180 * Math.PI;
            player.knockback(5, Math.sin(rot), -Math.cos(rot));

        }


        if (optional3.isPresent()) {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
            }

            level.setBlock(blockpos, optional3.get(), 11);
            if (player != null) {
                itemstack.hurtAndBreak(1, player, (p_150686_) -> {
                    p_150686_.broadcastBreakEvent(p_40529_.getHand());
                });
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public void Enchant() {

        if (player != null) {

            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(player.level());
            Vec3i Pos = new Vec3i(player.blockPosition().getX(), player.blockPosition().getY() + 3, player.blockPosition().getZ());
            lightningbolt.moveTo(Vec3.atBottomCenterOf(Pos));
            player.level().addFreshEntity(lightningbolt);

            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
            CalcStack calc = new CalcStack();
            int bonus = calc.CalcCornMetal(player);


            if ((calc.CalcCornMetal(player) - calc.CalcKernal(player)) > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
            }
            if ((calc.CalcCornMetal(player) - calc.CalcKernal(player)) > 1) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 300));
            }
            if ((calc.CalcCornMetal(player) - calc.CalcKernal(player)) > 2) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300));
            }
            if ((calc.CalcCornMetal(player) - calc.CalcKernal(player)) > 3) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300));
            }

            if ((calc.CalcMaize(player) - calc.CalcKernal(player)) > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300));
            }
            if ((calc.CalcMaize(player) - calc.CalcKernal(player)) > 1) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300));
            }
            if ((calc.CalcMaize(player) - calc.CalcKernal(player)) > 2) {
                player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 300));
            }
            if ((calc.CalcMaize(player) - calc.CalcKernal(player)) > 3) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300));
            }

            if ((calc.CalcKernal(player)) > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1));
            }
            if ((calc.CalcKernal(player)) > 1) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1));
            }
            if ((calc.CalcKernal(player)) > 2) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1));
            }
            if ((calc.CalcKernal(player)) > 3) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1));
            }

            player.getCooldowns().addCooldown(this, 150);
        }
    }


    @Override
    public boolean hurtEnemy(ItemStack p_40994_, LivingEntity p_40995_, LivingEntity p_40996_) {
        p_40994_.hurtAndBreak(2, p_40996_, (p_41007_) -> {
            p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            //LightningBolt hol = new LightningBolt();
            //p_40995_.thunderHit(hol,);
        });
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
