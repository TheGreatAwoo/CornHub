package com.corn.callofthecorn.init;

import com.corn.callofthecorn.FleetfootEnchantment;
import com.corn.callofthecorn.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CornEnchantments {


    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT, Main.MOD_ID);
    public static final Supplier<Enchantment> FLEETFOOT = ENCHANTMENTS.register("fleetfoot",() -> new FleetfootEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET,new EquipmentSlot[]{EquipmentSlot.FEET}));

}
