package com.corn.callofthecorn;

import com.corn.callofthecorn.data.*;
import com.corn.callofthecorn.init.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.scores.Team;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Mod(Main.MODID)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "callofthecorn";
    public static final String MOD_ID = MODID; // bro


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
        CornMobs.ENTITY_TYPES.register(bus);
        bus.addListener(CornMobs::registerAttributes);
        CornItems.ITEMS.register(bus);
        CornBlocks.BLOCKS.register(bus);
        CornEnchantments.ENCHANTMENTS.register(bus);
        CornCreativeTabs.TABS.register(bus);

        bus.addListener(this::gatherData);

        MinecraftForge.EVENT_BUS.register(this);

        // For events that happen after initialization. This is probably going to be use a lot.
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

    }

    public void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        PackOutput out = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new CornItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new CornBlockStateProvider(generator, helper));

        CornBlockTagProvider blocktags = new CornBlockTagProvider(generator, provider, helper);
        generator.addProvider(event.includeServer(), blocktags);
        generator.addProvider(event.includeServer(), new CornItemTagProvider(generator, provider, blocktags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new CornRecipeProvider(out, provider));
//        generator.addProvider(event.includeServer(), new CornLootTableProvider(out));
        generator.addProvider(event.includeServer(), new CornFeatureProvider(out, provider));

    }

}
