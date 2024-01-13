//package com.example.callofthecorn;
//
//import com.example.callofthecorn.structures.*;
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableMap;
//import net.minecraft.data.BuiltinRegistries;
//import net.minecraft.world.level.levelgen.StructureSettings;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
//import net.minecraftforge.fmllegacy.RegistryObject;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import org.lwjgl.system.CallbackI;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class STStructures {
//
//
//    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Main.MODID);
//
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PLOT = DEFERRED_REGISTRY_STRUCTURE.register("plot", () -> (new Plot(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> BARN = DEFERRED_REGISTRY_STRUCTURE.register("barn", () -> (new Barn(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("house", () -> (new House(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> FIELD = DEFERRED_REGISTRY_STRUCTURE.register("field", () -> (new Field(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TOWER = DEFERRED_REGISTRY_STRUCTURE.register("tower", () -> (new Tower(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> FIELD2 = DEFERRED_REGISTRY_STRUCTURE.register("field2", () -> (new Field2(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PLOT2 = DEFERRED_REGISTRY_STRUCTURE.register("plot2", () -> (new Plot2(NoneFeatureConfiguration.CODEC)));
//    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANOR = DEFERRED_REGISTRY_STRUCTURE.register("manor", () -> (new Manor(NoneFeatureConfiguration.CODEC)));
//
//
//    public static void setupStructures() {
//
//
//        setupMapSpacingAndLand( FIELD.get(),
//                new StructureFeatureConfiguration(20/* average distance apart in chunks between spawn attempts */,
//                        10 /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        423423526 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//        setupMapSpacingAndLand( FIELD2.get(),
//                new StructureFeatureConfiguration(20/* average distance apart in chunks between spawn attempts */,
//                        10 /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        423423526 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//
//        setupMapSpacingAndLand(
//        PLOT.get(),
//                new StructureFeatureConfiguration(20/* average distance apart in chunks between spawn attempts */,
//                        10/* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        1234567890 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//        setupMapSpacingAndLand(
//                PLOT2.get(),
//                new StructureFeatureConfiguration(40/* average distance apart in chunks between spawn attempts */,
//                        20/* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        1233123120 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//
////         Add more structures here and so on
//
//        setupMapSpacingAndLand( BARN.get(),
//                new StructureFeatureConfiguration(80/* average distance apart in chunks between spawn attempts */,
//                        40/* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        324242342 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//
//
//        setupMapSpacingAndLand( HOUSE.get(),
//                new StructureFeatureConfiguration(60/* average distance apart in chunks between spawn attempts */,
//                        30/* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        534536634 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//        setupMapSpacingAndLand( TOWER.get(),
//                new StructureFeatureConfiguration(80/* average distance apart in chunks between spawn attempts */,
//                        40/* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        545850634 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//        setupMapSpacingAndLand( MANOR.get(),
//                new StructureFeatureConfiguration(250/* average distance apart in chunks between spawn attempts */,
//                        100/* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//                        545850634 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//                true);
//
//    }
//
//    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(
//            F structure,
//            StructureFeatureConfiguration StructureFeatureConfiguration,
//            boolean transformSurroundingLand)
//    {
//
//        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
//
//        if(transformSurroundingLand){
//            StructureFeature.NOISE_AFFECTING_FEATURES =
//                    ImmutableList.<StructureFeature<?>>builder()
//                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
//                            .add(structure)
//                            .build();
//        }
//
//
//        StructureSettings.DEFAULTS =
//                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
//                        .putAll(StructureSettings.DEFAULTS)
//                        .put(structure, StructureFeatureConfiguration)
//                        .build();
//
//
//
//        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
//            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();
//
//            if(structureMap instanceof ImmutableMap){
//                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
//                tempMap.put(structure, StructureFeatureConfiguration);
//                settings.getValue().structureSettings().structureConfig = tempMap;
//            }
//            else{
//                structureMap.put(structure, StructureFeatureConfiguration);
//            }
//        });
//    }
//}
