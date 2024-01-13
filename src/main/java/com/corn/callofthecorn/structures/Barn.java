//package com.example.callofthecorn.structures;
//
//import com.example.callofthecorn.Init.MobInit;
//import com.example.callofthecorn.Main;
//import com.google.common.collect.ImmutableList;
//import com.mojang.serialization.Codec;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Registry;
//import net.minecraft.core.RegistryAccess;
//import net.minecraft.core.Vec3i;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.Mob;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraft.world.entity.MobType;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.LevelHeightAccessor;
//import net.minecraft.world.level.NoiseColumn;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.BiomeSource;
//import net.minecraft.world.level.biome.MobSpawnSettings;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.levelgen.GenerationStep;
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraft.world.level.levelgen.WorldgenRandom;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
//import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
//import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
//import net.minecraft.world.level.levelgen.structure.StructurePiece;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
//import org.apache.logging.log4j.Level;
//
//import java.util.List;
//
//public class Barn extends StructureFeature<NoneFeatureConfiguration> {
//    public Barn(Codec<NoneFeatureConfiguration> codec) {
//        super(codec);
//    }
//
//    @Override
//    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
//        return Barn.Start::new;
//    }
//
//
//    @Override
//    public GenerationStep.Decoration step() {
//        return GenerationStep.Decoration.SURFACE_STRUCTURES;
//    }
//
//
//    private static final List<MobSpawnSettings.SpawnerData> STRUCTURE_MONSTERS = ImmutableList.of(
//            new MobSpawnSettings.SpawnerData(MobInit.SCARECROW.get(), 100, 1, 2),
//            new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 100, 1, 3)
//
//    );
//    @Override
//    public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {
//        return STRUCTURE_MONSTERS;
//    }
//
//    private static final List<MobSpawnSettings.SpawnerData> STRUCTURE_CREATURES = ImmutableList.of(
//            new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 30, 10, 15),
//            new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 100, 1, 2)
//    );
//    @Override
//    public List<MobSpawnSettings.SpawnerData> getDefaultCreatureSpawnList() {
//        return STRUCTURE_CREATURES;
//    }
//
//
//    @Override
//    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom random, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos2, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
//        BlockPos blockPos = chunkPos1.getWorldPosition();
//
//        int landHeight = chunkGenerator.getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
//
//
//        NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(blockPos.getX(), blockPos.getZ(), heightLimitView);
//
//        BlockState topBlock = columnOfBlocks.getBlockState(blockPos.above(landHeight));
//
//        return topBlock.getFluidState().isEmpty(); //landHeight > 100;
//    }
//
//    /**
//     * Handles calling up the structure's pieces class and height that structure will spawn at.
//     */
//    public static class Start extends NoiseAffectingStructureStart<NoneFeatureConfiguration> {
//        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos, int referenceIn, long seedIn) {
//            super(structureIn, chunkPos, referenceIn, seedIn);
//        }
//
//        @Override
//        public void generatePieces(RegistryAccess dynamicRegistryAccess, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biomeIn, NoneFeatureConfiguration config, LevelHeightAccessor heightLimitView) {
//            /*
//             * We pass this into addPieces to tell it where to generate the structure.
//             * If addPieces's last parameter is true, blockpos's Y value is ignored and the
//             * structure will spawn at terrain height instead. Set that parameter to false to
//             * force the structure to spawn at blockpos's Y value instead. You got options here!
//             */
//            BlockPos structureBlockPos = new BlockPos(chunkPos.getMinBlockX(), 0, chunkPos.getMinBlockZ());
//
//            /*
//             * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
//             * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
//             * Then loop through it and look for land with air above it and set blockpos's Y value to it.
//             * Make sure to set the final boolean in JigsawPlacement.addPieces to false so
//             * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
//             */
//            //NoiseColumn blockReader = chunkGenerator.getBaseColumn(blockpos.getX(), blockpos.getZ(), heightLimitView);
//
//            // All a structure has to do is call this method to turn it into a jigsaw based structure!
//            JigsawPlacement.addPieces(
//                    dynamicRegistryAccess,
//                    new JigsawConfiguration(() -> dynamicRegistryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
//                            // The path to the starting Template Pool JSON file to read.
//                            //
//                            // Note, this is "structure_tutorial:run_down_house/start_pool" which means
//                            // the game will automatically look into the following path for the template pool:
//                            // "resources/data/structure_tutorial/worldgen/template_pool/run_down_house/plot.json"
//                            // This is why your pool files must be in "data/<modid>/worldgen/template_pool/<the path to the pool here>"
//                            // because the game automatically will check in worldgen/template_pool for the pools.
//                            .get(new ResourceLocation(Main.MODID, "barn")),
//
//                            // How many pieces outward from center can a recursive jigsaw structure spawn.
//                            // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
//                            // However, I recommend you keep this a decent value like 10 so people can use datapacks to add additional pieces to your structure easily.
//                            // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
//                            10),
//                    PoolElementStructurePiece::new,
//                    chunkGenerator,
//                    structureManager,
//                    structureBlockPos, // Position of the structure. Y value is ignored if last boolean parameter is set to true.
//                    this, // The structure class to populate its pieces field with the jigsaw pieces after this method is ran.
//                    this.random,
//                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
//                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
//                    true,  // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
//                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
//                    heightLimitView);
//
//
//            // **THE FOLLOWING LINE IS OPTIONAL**
//            //
//            // Right here, you can do interesting stuff with the pieces in this.pieces such as offset the
//            // center piece by 50 blocks up for no reason, remove repeats of a piece or add a new piece so
//            // only 1 of that piece exists, etc. But you do not have access to the piece's blocks as this list
//            // holds just the piece's size and positions. Blocks will be placed much later by the game.
//            //
//            // In this case, we do `piece.offset` to raise pieces up by 1 block so that the house is not right on
//            // the surface of water or sunken into land a bit. NOTE: land added by StructureFeature.NOISE_AFFECTING_FEATURES
//            // will also be moved alongside the piece. If you do not want this land, do not add your structure to the
//            // StructureFeature.NOISE_AFFECTING_FEATURES field and now your pieces can be set on the regular terrain instead.
//            this.pieces.forEach(piece -> piece.move(0, 1, 0));
//
//            // Since by default, the start piece of a structure spawns with it's corner at centerPos
//            // and will randomly rotate around that corner, we will center the piece on centerPos instead.
//            // This is so that our structure's start piece is now centered on the water check done in isFeatureChunk.
//            // Whatever the offset done to center the start piece, that offset is applied to all other pieces
//            // so the entire structure is shifted properly to the new spot.
//            Vec3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
//            int xOffset = structureBlockPos.getX() - structureCenter.getX();
//            int zOffset = structureBlockPos.getZ() - structureCenter.getZ();
//            for(StructurePiece structurePiece : this.pieces){
//                structurePiece.move(xOffset, 0, zOffset);
//            }
//
//            // Sets the bounds of the structure once you are finished.
//            this.getBoundingBox();
//
//            // I use to debug and quickly find out if the structure is spawning or not and where it is.
//            // This is returning the coordinates of the center starting piece.
//        }
//    }
//}