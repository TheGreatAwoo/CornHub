package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Init.BlocksInit;
import com.corn.callofthecorn.Main;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CornFeatureProvider extends DatapackBuiltinEntriesProvider {

    public CornFeatureProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider,
                new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, bootstrap -> {
                            // Register configured features here
                            bootstrap.register(cfk("cornpatch"),
                                new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 4, 0,
                                        PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(2, 4), BlockStateProvider.simple(BlocksInit.CORN_SEED.get())),
                                            BlockPredicateFilter.forPredicate(
                                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                                        BlockPredicate.matchesTag(new Vec3i(0,-1,0), BlockTags.DIRT)
                                                )
                                            )
                                        )
                                    )
                                )
                            );

                        })
                        .add(Registries.PLACED_FEATURE, bootstrap -> {
                            // Register placed features here
                            HolderGetter<ConfiguredFeature<?, ?>> configured = bootstrap.lookup(Registries.CONFIGURED_FEATURE);

                            bootstrap.register(pfk("cornpatch"),
                                    new PlacedFeature(configured.getOrThrow(cfk("cornpatch")),
                                            List.of(NoiseThresholdCountPlacement.of(-0.8D,5,10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())
                                    ));
                        }),
                Set.of(Main.MOD_ID)
        );
    }

    private static ResourceKey<ConfiguredFeature<?,?>> cfk(String s) {
        return  ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Main.MOD_ID, s));
    }

    private static ResourceKey<PlacedFeature> pfk(String s) {
        return  ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Main.MOD_ID, s));
    }

}