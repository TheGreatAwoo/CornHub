package com.corn.callofthecorn;

import com.corn.callofthecorn.data.CornTags;
import com.corn.callofthecorn.init.CornItems;
import com.corn.callofthecorn.items.armour.CalcStack;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.bus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.*;

@EventBusSubscriber(modid = Main.MOD_ID)
public class CombatEventHandler {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        if(event.getTarget() instanceof LivingEntity e) {
            CuriosApi.getCuriosInventory(event.getEntity()).ifPresent((itemHandler)-> {
                itemHandler.findFirstCurio(CornItems.CROWSFOOT.get()).ifPresent((result) -> {
                    e.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));
                });
            });
        }
    }

    private static final AttributeModifier CORN_FULL_BONUS = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "corn_full_bonus_hp"), 2, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier CORN_HALF_BONUS = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "corn_half_bonus"), .025, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier CORN_FULL_BONUS_SPEED = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "corn_full_bonus"), .05, AttributeModifier.Operation.ADD_VALUE);

    private static final AttributeModifier MAIZE_SPEED_PENALTY = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maize_speed_penalty"), -.02, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier MAIZE_HALF_BONUS_HP = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maize_half_bonus_hp"), 4, AttributeModifier.Operation.ADD_VALUE);

    private static final AttributeModifier MAIZE_FULL_BONUS = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maize_full_bonus"), 3, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier MAIZE_FULL_BONUS_HP = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maize_full_bonus_hp"), 4, AttributeModifier.Operation.ADD_VALUE);

    private static final Map<AttributeModifier, Holder<Attribute>> modTypes = Map.of(
            CORN_FULL_BONUS, Attributes.MAX_HEALTH,
            CORN_HALF_BONUS, Attributes.MOVEMENT_SPEED,
            CORN_FULL_BONUS_SPEED, Attributes.MOVEMENT_SPEED,
            MAIZE_SPEED_PENALTY, Attributes.MOVEMENT_SPEED,
            MAIZE_HALF_BONUS_HP, Attributes.MAX_HEALTH,
            MAIZE_FULL_BONUS, Attributes.ARMOR,
            MAIZE_FULL_BONUS_HP, Attributes.MAX_HEALTH);


    private static Set<AttributeModifier> shouldHave(TagKey<Item> setType, int setBonus) {
        if(setType == CornTags.Items.CORNMETAL_SET_ITEMS) {
            return switch (setBonus) {
                case 4 -> Set.of(CORN_FULL_BONUS, CORN_FULL_BONUS_SPEED);
                case 2, 3 -> Set.of(CORN_HALF_BONUS);
                default -> Set.of();
            };
        }
        if(setType == CornTags.Items.MAIZERITE_SET_ITEMS) {
            return switch (setBonus) {
                case 4    -> Set.of(MAIZE_FULL_BONUS, MAIZE_FULL_BONUS_HP, MAIZE_SPEED_PENALTY);
                case 2, 3 -> Set.of(MAIZE_SPEED_PENALTY, MAIZE_HALF_BONUS_HP);
                default   -> Set.of();
            };
        }
        return Set.of();
    }


    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if(event.getEntity() instanceof Player player) {
            Set<AttributeModifier> shouldHaveMods = new HashSet<>();

            TagKey<Item>[] setTypes = new TagKey[]{CornTags.Items.CORNMETAL_SET_ITEMS, CornTags.Items.MAIZERITE_SET_ITEMS};
            for(TagKey<Item> setType : setTypes) {
                int setBonus = CalcStack.getSetBonus(player, setType);
                shouldHaveMods.addAll(shouldHave(setType, setBonus));
            }

            for(AttributeModifier mod : modTypes.keySet()) {
                if(!shouldHaveMods.contains(mod)) {
                    player.getAttributes().getInstance(modTypes.get(mod)).removeModifier(mod);
                }
            }

            shouldHaveMods.forEach((mod) -> player.getAttributes().getInstance(modTypes.get(mod)).addOrUpdateTransientModifier(mod));
        }
    }

}
