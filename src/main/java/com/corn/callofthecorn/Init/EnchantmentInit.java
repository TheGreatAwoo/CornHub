package com.corn.callofthecorn.Init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.enums.Fleetfoot;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {


    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MOD_ID);
    public static final RegistryObject<Enchantment> FLEETFOOT = ENCHANTMENTS.register("fleetfoot",() -> new Fleetfoot(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET,new EquipmentSlot[]{EquipmentSlot.FEET}));

}
