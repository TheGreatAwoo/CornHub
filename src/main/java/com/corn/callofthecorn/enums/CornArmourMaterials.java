package com.corn.callofthecorn.enums;

import com.corn.callofthecorn.Init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public enum CornArmourMaterials implements ArmorMaterial {

    CORNMETAL("cornmetal", 8, new int[]{2, 5, 6, 2}, 1, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.of(ItemInit.CORNMETALBAR.get());
    }),
    MAIZERITE("maizerite", 25, new int[]{3, 6, 8, 3}, 1, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> {
        return Ingredient.of(ItemInit.MAIZERITE.get());
    }),
    PUMPKIN("pumpkin", 500, new int[]{3, 6, 8, 1}, 1, SoundEvents.ARMOR_EQUIP_CHAIN, 3.0F, 0.0F, () -> {
        return Ingredient.of(Blocks.PUMPKIN);
    });

    private static final int[] BASE_DURABILITY_BY_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    public final int durabilityMultiplier;
    private final int[] slotProtections;
    public final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    CornArmourMaterials(String name, int durabilityMult, int[] protections, int enchantability, SoundEvent sount, float toughness, float knockbackResistance, Supplier<Ingredient> repairItem) {
        this.name = name;
        this.durabilityMultiplier = durabilityMult;
        this.slotProtections = protections;
        this.enchantmentValue = enchantability;
        this.sound = sount;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairItem);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY_BY_SLOT[type.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.slotProtections[type.getSlot().getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}