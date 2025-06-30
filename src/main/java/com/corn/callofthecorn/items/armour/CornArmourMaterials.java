package com.corn.callofthecorn.items.armour;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.data.CornTags;
import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class CornArmourMaterials {

    public static final ArmorMaterial CORNMETAL = new ArmorMaterial(
            8, makeDefence(2, 4, 6, 3, 5),
            1, SoundEvents.ARMOR_EQUIP_CHAIN, 0f, 0f, // ench, sound, toughness, knockback
            CornTags.Items.CORN_TOOL_MATERIALS, // repair items - corn bar
            // The relative location of the EquipmentModel JSON at assets/<namespace>/models/equipment/<path>.json
            createId("cornmetal")
        );

    public static final ArmorMaterial MAIZERITE = new ArmorMaterial(
            25, makeDefence(3, 5, 8, 4, 11),
            1, SoundEvents.ARMOR_EQUIP_CHAIN, 2f, 0f, // ench, sound, toughness, knockback
            CornTags.Items.MAIZERITE_TOOL_MATERIALS, // repair items - maizerite
            // The relative location of the EquipmentModel JSON at assets/<namespace>/models/equipment/<path>.json
            createId("maizerite")
    );

    public static final ArmorMaterial PUMPKIN = new ArmorMaterial(
            500, makeDefence(3, 5, 8, 1, 5),
            1, SoundEvents.ARMOR_EQUIP_CHAIN, 3f, 0f, // ench, sound, toughness, knockback
            CornTags.Items.PUMPKING_CROWN_MATERIALS, // repair items - pumpkin block
            // The relative location of the EquipmentModel JSON at assets/<namespace>/models/equipment/<path>.json
            createId("pumpkin")
    );

    private static Map<ArmorType, Integer> makeDefence(int b, int l, int c, int h, int o) {
        return Maps.newEnumMap(
                Map.of(
                        ArmorType.BOOTS, b,
                        ArmorType.LEGGINGS, l,
                        ArmorType.CHESTPLATE, c,
                        ArmorType.HELMET, h,
                        ArmorType.BODY, o
                )
        );
    }

    private static ResourceKey<EquipmentAsset> createId(String p_386630_) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, p_386630_));
    }

}