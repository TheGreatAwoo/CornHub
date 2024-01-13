package com.corn.callofthecorn.enums;

import com.corn.callofthecorn.Main;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;

public class PumpCrown extends CornArmourItem{
    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private EquipmentSlot CurrentSlot;
    private ArmorMaterial CurrentMaterial;

    public PumpCrown(ArmorMaterial p_40386_, Type p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_40392_, ItemStack p_40393_) {
        return false;
    }

    @Override
    public void doArmourTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,1),player);
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,1),player);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        if(slot==EquipmentSlot.LEGS){return  new ResourceLocation(Main.MOD_ID,"textures/items/cornmetal2.png").toString();}

        else return  new ResourceLocation(Main.MOD_ID,"textures/items/pumpcrown.png").toString();

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
