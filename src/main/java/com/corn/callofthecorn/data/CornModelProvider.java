package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.init.CornBlocks;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class CornModelProvider extends ModelProvider {

    final ExtendedModelTemplate CUTOUT_CROSS = ModelTemplates.CROSS.extend().renderType("minecraft:cutout").build();

    public CornModelProvider(PackOutput output) {
        super(output, Main.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for(DeferredHolder<Item, ? extends Item> regOb : CornItems.ITEMS.getEntries()) {
            Item item = regOb.get();
            if(item instanceof BlockItem) continue;
            if(CornItems.HANDHELD_ITEMS.contains(regOb)) {
                itemModels.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM); // rod?
            } else if(CornItems.BOW_MODEL_ITEMS.contains(regOb)) {
                ItemModel.Unbaked bow = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "", ModelTemplates.BOW));
                ItemModel.Unbaked pullingBow0 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "1", ModelTemplates.BOW));
                ItemModel.Unbaked pullingBow1 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "2", ModelTemplates.BOW));
                ItemModel.Unbaked pullingBow2 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "3", ModelTemplates.BOW));
                itemModels.itemModelOutput.accept(
                        regOb.get(),
                        // Conditional model for item
                        ItemModelUtils.conditional(
                                // Checks if item is being used
                                ItemModelUtils.isUsingItem(),
                                // When true, select model based on use duration
                                ItemModelUtils.rangeSelect(
                                        new UseDuration(false),
                                        // Scalar to apply to the thresholds
                                        0.05F,
                                        pullingBow0,
                                        // Threshold when 0.65
                                        ItemModelUtils.override(pullingBow1, 0.65F),
                                        // Threshold when 0.9
                                        ItemModelUtils.override(pullingBow2, 0.9F)
                                ),
                                // When false, use the base bow model
                                bow
                        )
                );
            } else {
                itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
            }
        }

        // Any block that looks different in the inventory goes in here
        List<Block> differentItemTexture = new ArrayList<>();

        differentItemTexture.add(CornBlocks.CORN_SEED.get());

        MultiVariant multivariant = plainVariant(blockModels.createSuffixedVariant(CornBlocks.CORN_SEED.get(), "", CUTOUT_CROSS, TextureMapping::cross));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(CornBlocks.CORN_SEED.get(), multivariant));

        blockModels.registerSimpleFlatItemModel(CornBlocks.CORN_SEED.get());

        CornBlocks.BLOCKS.getEntries().stream().map(Supplier::get).forEach(block -> {
            if (differentItemTexture.contains(block)) {
//                blockModels.registerSimpleFlatItemModel(block);
            } else {
                //simpleBlockItem(block, models().getExistingFile(modLoc("block/" + name)));
            }
        });
    }
}