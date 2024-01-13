package com.corn.callofthecorn;

import com.corn.callofthecorn.init.CornBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = Main.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventSubscriber {

    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS,
                itemRegisterHelper -> {
                    CornBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(
                            block -> {
                                final Item.Properties properties = new Item.Properties();
                                final BlockItem blockItem = new BlockItem(block, properties);
                                itemRegisterHelper.register(ForgeRegistries.BLOCKS.getKey(block), blockItem);
                            }
                    );

                }
        );
    }
}
