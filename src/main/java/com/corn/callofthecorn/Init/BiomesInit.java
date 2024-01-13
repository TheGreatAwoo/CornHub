package com.corn.callofthecorn.Init;

import com.corn.callofthecorn.generation.features.ModConfigFeatures;
import com.corn.callofthecorn.generation.features.ModPlacedFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;


public class BiomesInit {

	public static ConfiguredFeature<?,?> CORN_PATCH;
	//public static RegistryObject<IvyMossyLogFeature> IVY_MOSS_LOG = FEATURES.register("ivy_moss_log", () -> new IvyMossyLogFeature(NoneFeatureConfiguration.CODEC));
	public static void registerFeatures() {

		ModConfigFeatures.registerConfiguredFeatures();
		ModPlacedFeatures.cornRegister();


	}

}