package com.corn.callofthecorn.Init;

import com.corn.callofthecorn.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CornCreativeTabs  {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CORN_TAB = TABS.register("corn",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group." + Main.MOD_ID + ".corn"))
                    .icon(() -> new ItemStack(Items.CARVED_PUMPKIN))
                    .displayItems((parameters, output) -> {
                        BlocksInit.BLOCKS.getEntries().forEach(reg -> output.accept(reg.get()));
                        ItemInit.ITEMS.getEntries().forEach(reg -> output.accept(reg.get()));
                    })
                    .build());

}
