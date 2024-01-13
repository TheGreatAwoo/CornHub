//package com.example.callofthecorn.enums;
//
//import net.minecraftforge.common.BiomeManager;
//import com.example.callofthecorn.Init.BiomesInit;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraftforge.common.BiomeDictionary;
//import net.minecraftforge.common.BiomeDictionary.Type;
//import static net.minecraftforge.common.BiomeDictionary.Type.*;
//
//public class BiomeGen {
//
//    public static void generateBiomes() {
//        addBiome(BiomesInit.CORNBIOME, BiomeManager.BiomeType.WARM, 20, HOT, DEAD, DRY);
//    }
//
//    private static void addBiome(ResourceKey biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
//        ResourceKey<Biome> key = biome;
//
//        BiomeDictionary.addTypes(key, types);
//        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
//    }
//
//
//
//}
