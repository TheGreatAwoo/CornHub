package com.corn.callofthecorn.generation.features;

import com.corn.callofthecorn.Init.BlocksInit;
import com.corn.callofthecorn.Main;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;

public class ModConfigFeatures {
    public static ConfiguredFeature<?, ?> CORNPATCH;
    public static void registerConfiguredFeatures() {
        CORNPATCH = new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 4, 0,
                PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(2, 4),
                                BlockStateProvider.simple(BlocksInit.CORN_SEED.get())), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        //BlockStateProvider.simple(Blocks.DIRT)), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.matchesTag(new Vec3i(0,-1,0), BlockTags.DIRT)

                                )))
                )

        );
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Main.MOD_ID, "cornpatch_feature"), CORNPATCH);

    }
}
