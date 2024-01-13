package com.corn.callofthecorn.Init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.enums.CornTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
//@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod.EventBusSubscriber(modid = Main.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventSubscriber {

    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BiomesInit.registerFeatures();
        });
    }


    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS,
                itemRegisterHelper -> {
                    BlocksInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(
                            block -> {
                                final Item.Properties properties = new Item.Properties().tab(CornTab.TAB_Corn);
                                final BlockItem blockItem = new BlockItem(block, properties);
                                itemRegisterHelper.register(ForgeRegistries.BLOCKS.getKey(block), blockItem);
                            }
                    );

                }
        );
    }
}
