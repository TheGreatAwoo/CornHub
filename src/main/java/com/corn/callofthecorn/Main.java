package com.corn.callofthecorn;

import com.corn.callofthecorn.data.*;
import com.corn.callofthecorn.init.*;
import net.minecraft.data.loot.LootTableProvider;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

@Mod(Main.MOD_ID)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "callofthecorn";

    public Main(IEventBus bus, ModContainer modContainer) {
        CornMobs.ENTITY_TYPES.register(bus);
        bus.addListener(CornMobs::registerAttributes);
        CornItems.ITEMS.register(bus);
        CornBlocks.BLOCKS.register(bus);
        CornCreativeTabs.TABS.register(bus);

        bus.addListener(this::gatherData);
    }

    public void gatherData(GatherDataEvent.Client event) {
        event.createProvider(CornModelProvider::new);
        event.createProvider(CornFeatureProvider::new);
        event.createProvider(CornRecipeProvider.Runner::new);
        event.createProvider(CornEquipmentInfoProvider::new);
        event.createBlockAndItemTags(CornBlockTagProvider::new, CornItemTagProvider::new);
        event.createProvider((output, lookupProvider) -> new LootTableProvider(output, Set.of(),
                List.of(),
                lookupProvider));
    }

}
