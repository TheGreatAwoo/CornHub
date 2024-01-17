package com.corn.callofthecorn.items.armour;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.data.CornTags;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

public class CornMetalArmourItem extends CornArmourItem {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    private final EquipmentSlot intendedSlot;
    AttributeModifier FullBonus = new AttributeModifier(UUID.fromString("9F3D476D-C118-4544-8365-64846904B48F"), "Armor modifier", 2, AttributeModifier.Operation.ADDITION);
    AttributeModifier legAt = new AttributeModifier(UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), "Armor modifier", 5, AttributeModifier.Operation.ADDITION);
    AttributeModifier headAt = new AttributeModifier(UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Armor modifier", 2, AttributeModifier.Operation.ADDITION);
    AttributeModifier feetAt = new AttributeModifier(UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), "Armor modifier", 2, AttributeModifier.Operation.ADDITION);
    AttributeModifier chestAt = new AttributeModifier(UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), "Armor modifier", 6, AttributeModifier.Operation.ADDITION);
    AttributeModifier HalfBonus = new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "Armor modifier", .025, AttributeModifier.Operation.ADDITION);
    AttributeModifier FullBonusSpeed = new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68072635"), "Armor modifier", .05, AttributeModifier.Operation.ADDITION);

    public CornMetalArmourItem(ArmorMaterial material, Type armourType, Properties properties) {
        super(material, armourType, properties);
        intendedSlot = armourType.getSlot();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (intendedSlot == EquipmentSlot.LEGS) {
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), "Armor modifier", 5, AttributeModifier.Operation.ADDITION));
        }
        if (intendedSlot == EquipmentSlot.HEAD) {
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Armor modifier", 2, AttributeModifier.Operation.ADDITION));
        }
        if (intendedSlot == EquipmentSlot.CHEST) {
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), "Armor modifier", 6, AttributeModifier.Operation.ADDITION));
        }
        if (intendedSlot == EquipmentSlot.FEET) {
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), "Armor modifier", 2, AttributeModifier.Operation.ADDITION));
        }


        this.defaultModifiers = builder.build();

    }

    @Override
    public void doArmourTick(ItemStack stack, Level world, Player player) {
        boolean appliedBonus = false;
        int Setbonus = 0;
        CompoundTag tag = stack.getOrCreateTag();
        ListTag l = tag.getList("AttributeModifiers", 10);
        l.clear();
        stack.setTag(tag);

        Setbonus = CalcStack.getSetBonus(player, CornTags.Items.CORNMETAL_SET_ITEMS);


        if (!appliedBonus) {
            if (Setbonus >= 2 && player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.HEAD).addAttributeModifier(Attributes.MOVEMENT_SPEED, HalfBonus, EquipmentSlot.HEAD);
                appliedBonus = true;
            }

            if (Setbonus == 4 && player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.HEAD).addAttributeModifier(Attributes.MAX_HEALTH, FullBonus, EquipmentSlot.HEAD);
                player.getItemBySlot(EquipmentSlot.HEAD).addAttributeModifier(Attributes.MOVEMENT_SPEED, FullBonusSpeed, EquipmentSlot.HEAD);
                appliedBonus = true;
            }
        }

        if (!appliedBonus) {
            if (Setbonus >= 2 && player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.FEET).addAttributeModifier(Attributes.MOVEMENT_SPEED, HalfBonus, EquipmentSlot.FEET);
                appliedBonus = true;
            }

            if (Setbonus == 4 && player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.FEET).addAttributeModifier(Attributes.MAX_HEALTH, FullBonus, EquipmentSlot.FEET);
                player.getItemBySlot(EquipmentSlot.FEET).addAttributeModifier(Attributes.MOVEMENT_SPEED, FullBonusSpeed, EquipmentSlot.FEET);
                appliedBonus = true;
            }
        }

        if (!appliedBonus) {
            if (Setbonus >= 2 && player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.CHEST).addAttributeModifier(Attributes.MOVEMENT_SPEED, HalfBonus, EquipmentSlot.CHEST);
                appliedBonus = true;
            }

            if (Setbonus == 4 && player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.CHEST).addAttributeModifier(Attributes.MAX_HEALTH, FullBonus, EquipmentSlot.CHEST);
                player.getItemBySlot(EquipmentSlot.CHEST).addAttributeModifier(Attributes.MOVEMENT_SPEED, FullBonusSpeed, EquipmentSlot.CHEST);
                appliedBonus = true;
            }
        }

        if (!appliedBonus) {
            if (Setbonus >= 2 && player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.LEGS).addAttributeModifier(Attributes.MOVEMENT_SPEED, HalfBonus, EquipmentSlot.LEGS);
                appliedBonus = true;
            }

            if (Setbonus == 4 && player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CornMetalArmourItem) {
                player.getItemBySlot(EquipmentSlot.LEGS).addAttributeModifier(Attributes.MAX_HEALTH, FullBonus, EquipmentSlot.LEGS);
                player.getItemBySlot(EquipmentSlot.LEGS).addAttributeModifier(Attributes.MOVEMENT_SPEED, FullBonusSpeed, EquipmentSlot.LEGS);
                appliedBonus = true;
            }
        }


        if (intendedSlot == EquipmentSlot.LEGS) {
            player.getItemBySlot(EquipmentSlot.LEGS).addAttributeModifier(Attributes.ARMOR, legAt, EquipmentSlot.LEGS);
        }

        if (intendedSlot == EquipmentSlot.HEAD) {
            player.getItemBySlot(EquipmentSlot.HEAD).addAttributeModifier(Attributes.ARMOR, headAt, EquipmentSlot.HEAD);
        }
        if (intendedSlot == EquipmentSlot.CHEST) {
            player.getItemBySlot(EquipmentSlot.CHEST).addAttributeModifier(Attributes.ARMOR, chestAt, EquipmentSlot.CHEST);
        }
        if (intendedSlot == EquipmentSlot.FEET) {
            player.getItemBySlot(EquipmentSlot.FEET).addAttributeModifier(Attributes.ARMOR, feetAt, EquipmentSlot.FEET);
        }

    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == intendedSlot ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        if (slot == EquipmentSlot.LEGS) {
            return new ResourceLocation(Main.MOD_ID, "textures/item/cornmetal2.png").toString();
        } else return new ResourceLocation(Main.MOD_ID, "textures/item/cornmetal.png").toString();

    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }


}
