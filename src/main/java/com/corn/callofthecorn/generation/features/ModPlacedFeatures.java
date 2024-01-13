package com.corn.callofthecorn.generation.features;

import com.corn.callofthecorn.Main;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public  static PlacedFeature CORN_FEATURE;

    public static void cornRegister(){
        CORN_FEATURE = register("cornpatch",ModConfigFeatures.CORNPATCH, NoiseThresholdCountPlacement.of(-0.8D,5,10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    public static PlacedFeature register(String regName, ConfiguredFeature<?,?> feature, PlacementModifier... mods) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Main.MOD_ID, regName), new PlacedFeature(Holder.direct(feature), List.of(mods)));
    }

    public static PlacedFeature register(String regName, ConfiguredFeature<?,?> feature, List<PlacementModifier> modList) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Main.MOD_ID, regName), new PlacedFeature(Holder.direct(feature), modList));
    }

}
