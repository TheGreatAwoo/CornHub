//package com.example.callofthecorn;
//
//import net.minecraft.core.Registry;
//import net.minecraft.data.BuiltinRegistries;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//
//public class STConfiguredStructures {
//
//      public static ConfiguredStructureFeature<?, ?> CONFIGURED_PLOT = STStructures.PLOT.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_CORNFIELD = STStructures.FIELD.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_HOUSE = STStructures.HOUSE.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_BARN = STStructures.BARN.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_TOWER = STStructures.TOWER.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_CORNFIELD2 = STStructures.FIELD2.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_PLOT2 = STStructures.PLOT2.get().configured(NoneFeatureConfiguration.INSTANCE);
//    public static ConfiguredStructureFeature<?, ?> CONFIGURED_MANOR = STStructures.MANOR.get().configured(NoneFeatureConfiguration.INSTANCE);
//
//
//
//    public static void registerConfiguredStructures() {
//        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_plot"), CONFIGURED_PLOT);
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_barn"), CONFIGURED_BARN);
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_house"), CONFIGURED_HOUSE);
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_cornfield"), CONFIGURED_CORNFIELD);
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_tower"), CONFIGURED_TOWER);
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_cornfield2"), CONFIGURED_CORNFIELD2);
//        Registry.register(registry, new ResourceLocation(Main.MODID, "configured_manor"), CONFIGURED_MANOR);
//    }
//}
