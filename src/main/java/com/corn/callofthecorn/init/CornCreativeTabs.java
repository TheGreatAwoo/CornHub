package com.corn.callofthecorn.init;

import com.corn.callofthecorn.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CornCreativeTabs  {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, Main.MOD_ID);

    public static final Supplier<CreativeModeTab> CORN_TAB = TABS.register("corn",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group." + Main.MOD_ID + ".corn"))
                    .icon(() -> new ItemStack(Items.CARVED_PUMPKIN))
                    .displayItems((parameters, output) -> {
                        CornBlocks.BLOCKS.getEntries().forEach(reg -> output.accept(reg.get()));
                        CornItems.ITEMS.getEntries().forEach(reg -> output.accept(reg.get()));
                    })
                    .build());

}
