package com.corn.callofthecorn.items;

import com.corn.callofthecorn.data.CornTags;
import com.corn.callofthecorn.items.armour.CalcStack;
import com.google.common.collect.Multimap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class KernelItem extends AxeItem {
    public KernelItem(ToolMaterial p_40521_, float dmg, float speed, Properties p_40524_) {
        super(p_40521_, dmg, speed, p_40524_);
    }

    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    boolean toggle = false; // TODO this is shared across all kernels, so may lead to odd behaviour
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
            enchant(level, itemstack);
        }


        if (player.isCrouching() || toggle) {
            toggle = !toggle;
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
                itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    public void enchant(Level level, ItemStack stack) {

        if (player != null && level instanceof ServerLevel) {

            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, player.blockPosition(), EntitySpawnReason.TRIGGERED);
            Vec3i Pos = new Vec3i(player.blockPosition().getX(), player.blockPosition().getY() + 3, player.blockPosition().getZ());
            lightningbolt.setPos(Vec3.atBottomCenterOf(Pos));
            player.level().addFreshEntity(lightningbolt);

            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
            int c = CalcStack.getSetBonus(player, CornTags.Items.CORNMETAL_SET_ITEMS);
            int m = CalcStack.getSetBonus(player, CornTags.Items.MAIZERITE_SET_ITEMS);
            int k = CalcStack.getSetBonus(player, CornTags.Items.KERNEL_SET_ITEMS);


            if (c > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
            }
            if (c > 1) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 300));
            }
            if (c > 2) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300));
            }
            if (c > 3) {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 300));
            }

            if (m > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300));
            }
            if (m > 1) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300));
            }
            if (m > 2) {
                player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 300));
            }
            if (m > 3) {
                player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 300));
            }

            if (k > 0) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, k));
            }

            player.getCooldowns().addCooldown(stack, 150);
        }
    }
}
