package com.corn.callofthecorn.items;

import com.corn.callofthecorn.data.CornTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class CornToolTiers {

    public static final ToolMaterial CORNMETAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 180,
            9.0F, 2.0F, 1, CornTags.Items.CORN_TOOL_MATERIALS);
    public static final ToolMaterial MAIZERITE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2500,
            8.0F, 4.0F, 1, CornTags.Items.MAIZERITE_TOOL_MATERIALS);
    public static final ToolMaterial KERNALRITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3000,
            9.0F, 5.0F, 1, CornTags.Items.MAIZERITE_TOOL_MATERIALS);

}
