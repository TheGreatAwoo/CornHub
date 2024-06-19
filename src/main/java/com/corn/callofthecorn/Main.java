package com.corn.callofthecorn;

import com.corn.callofthecorn.data.*;
import com.corn.callofthecorn.init.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(Main.MOD_ID)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "callofthecorn";

    public Main(IEventBus bus) {
        CornMobs.ENTITY_TYPES.register(bus);
        bus.addListener(CornMobs::registerAttributes);
        CornItems.ITEMS.register(bus);
        CornBlocks.BLOCKS.register(bus);
        CornEnchantments.ENCHANTMENTS.register(bus);
        CornCreativeTabs.TABS.register(bus);

        bus.addListener(this::gatherData);
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
