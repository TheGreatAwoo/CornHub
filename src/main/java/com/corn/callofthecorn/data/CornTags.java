package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosResources;

public class CornTags {
        public static class Blocks {

            private static TagKey<Block> tag(String name) {
                return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, name));
            }
        }

        public static class Items {
            public static final TagKey<Item> CORNMETAL_SET_ITEMS = tag("cornmetal_set_items");
            public static final TagKey<Item> MAIZERITE_SET_ITEMS = tag("maizerite_set_items");
            public static final TagKey<Item> KERNEL_SET_ITEMS = tag("kernel_set_items");
            public static final TagKey<Item> CORN_TOOL_MATERIALS = tag("cornmetal_tool_materials");
            public static final TagKey<Item> MAIZERITE_TOOL_MATERIALS = tag("maizerite_tool_materials");
            public static final TagKey<Item> PUMPKING_CROWN_MATERIALS = tag("pumpking_crown_materials");

            public static final TagKey<Item> RINGS = curioTag("ring");
            public static final TagKey<Item> CHARMS = curioTag("charm");
            public static final TagKey<Item> NECKLACES = curioTag("necklace");
            public static final TagKey<Item> BELTS = curioTag("belt");

            private static TagKey<Item> tag(String name) {
                return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, name));
            }
            private static TagKey<Item> curioTag(String name) {
                return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CuriosResources.MOD_ID, name));
            }
        }

}
