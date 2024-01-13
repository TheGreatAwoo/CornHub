package com.corn.callofthecorn;

import com.corn.callofthecorn.Init.*;
//import com.example.callofthecorn.enums.BiomeGen;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
//import net.minecraft.world.level.levelgen.StructureSettings;
//import net.minecraft.world.level.levelgen.feature.StructureFeature;
//import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.scores.Team;
import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.world.BiomeLoadingEvent;
//import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Collection;

@Mod(Main.MODID)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "callofthecorn";
    public static final String MOD_ID = MODID;


    public static final Team CTeam = new Team() {
        @Override
        public String getName() {
            return "CornTeam";
        }

        @Override
        public MutableComponent getFormattedName(Component p_83538_) {
            return null;
        }

        @Override
        public boolean canSeeFriendlyInvisibles() {
            return false;
        }

        @Override
        public boolean isAllowFriendlyFire() {
            return false;
        }

        @Override
        public Visibility getNameTagVisibility() {
            return null;
        }

        @Override
        public ChatFormatting getColor() {
            return null;
        }

        @Override
        public Collection<String> getPlayers() {
            return null;
        }

        @Override
        public Visibility getDeathMessageVisibility() {
            return null;
        }

        @Override
        public CollisionRule getCollisionRule() {
            return null;
        }
    };


    public Main() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        MobInit.ENTITY_TYPES.register(bus);
        bus.addListener(MobInit::registerAttributes);
       // BiomesInit.BIOMES.register(bus);
        ItemInit.ITEMS.register(bus);
        BlocksInit.BLOCKS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        //AttributesInit.ATTRIBUTES.register(bus);
       // STStructures.DEFERRED_REGISTRY_STRUCTURE.register(bus);

        bus.addListener(EventSubscriber::commonSetup);

        bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(EventPriority.HIGH,this::setup);

        // For events that happen after initialization. This is probably going to be use a lot.
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
       // forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        // The comments for BiomeLoadingEvent and StructureSpawnListGatherEvent says to do HIGH for additions.
        //forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
    }

    public void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {

        //    STStructures.setupStructures();
        //    STConfiguredStructures.registerConfiguredStructures();
        });

//        BiomeGen.generateBiomes();
//        BiomesInit.registerFeatures();

    }

   // public void biomeModification(final BiomeLoadingEvent event) {


//        if(event.getCategory()==BiomesInit.CORNBIOME2.get().getBiomeCategory()){}
 //       event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_PLOT2);
//        event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_PLOT);
//        event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_BARN);
//        event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_HOUSE);
//        event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_FIXED);

   // }

    private static Method GETCODEC_METHOD;
    //public void addDimensionalSpacing(final WorldEvent.Load event) {
     //   if(event.getWorld() instanceof ServerLevel){
     //       ServerLevel serverWorld = (ServerLevel)event.getWorld();

            /*
             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
             * They will handle your structure spacing for your if you add to BuiltinRegistries.NOISE_GENERATOR_SETTINGS in your structure's registration.
             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
             */
         //   try {
       //         if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
        //        ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
       //         if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
       //     }
       //     catch(Exception e){
       //         Main.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
      //      }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
       //     if(serverWorld.getChunkSource().getGenerator() instanceof FlatLevelSource &&
       //         serverWorld.dimension().equals(Level.OVERWORLD)){
       //         return;
       //     }

            /*
             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
             *
             * NOTE: if you add per-dimension spacing configs, you can't use putIfAbsent as BuiltinRegistries.NOISE_GENERATOR_SETTINGS in FMLCommonSetupEvent
             * already added your default structure spacing to some dimensions. You would need to override the spacing with .put(...)
             * And if you want to do dimension blacklisting, you need to remove the spacing entry entirely from the map below to prevent generation safely.
             */
          //  Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
         //   tempMap.putIfAbsent(STStructures.PLOT.get(), StructureSettings.DEFAULTS.get(STStructures.PLOT.get()));
       //     serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
      //  }
  // }
}
