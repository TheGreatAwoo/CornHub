package com.corn.callofthecorn.enums;

import com.corn.callofthecorn.Init.ItemInit;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum CustomTier implements Tier {

    CORNMETAL(2, 1000, 9F, 2.0F, 1, () -> {
        return Ingredient.of(ItemInit.CORNMETALBAR.get());
    }),
    MAIZERITE(4, 2500, 8.0F, 4.0F, 1, () -> {
        return Ingredient.of(ItemInit.MAIZERITE.get());
    }),

    KERNALRITE(4, 3000, 9.0F, 5.0F, 1, () -> {
        return Ingredient.of(ItemInit.MAIZERITE.get());
    }),
    CUSTOM(1, 3000, 7.0F, 1.0F, 1, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    });


    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;


    private CustomTier(int level, int use, float speed, float dam, int enchant, Supplier<Ingredient> p_43337_) {
        this.level = level;
        this.uses = use;
        this.speed = speed;
        this.damage = dam;
        this.enchantmentValue = enchant;
        this.repairIngredient = new LazyLoadedValue<>(p_43337_);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

//    @javax.annotation.Nullable public net.minecraft.tags.Tag<net.minecraft.world.level.block.Block> getTag() {
//        return switch (this){
//            case CORNMETAL -> BlockTags.NEEDS_IRON_TOOL;
//            case MAIZERITE -> BlockTags.NEEDS_DIAMOND_TOOL;
//            case KERNALRITE -> BlockTags.NEEDS_DIAMOND_TOOL;
//            case CUSTOM -> BlockTags.NEEDS_IRON_TOOL;
//
//        };
//         }
}
