package com.corn.callofthecorn;

import com.corn.callofthecorn.init.CornBlocks;
import com.corn.callofthecorn.init.CornItems;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import top.theillusivec4.curios.api.*;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Main.MOD_ID)
public class EventSubscriber {

    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        event.register(Registries.ITEM,
                itemRegisterHelper -> {
                    CornBlocks.BLOCKS.getEntries().stream().map(Supplier::get).forEach(
                            block -> {
                                ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
                                itemRegisterHelper.register(key, new BlockItem(block, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, key))));
                            }
                    );

                }
        );
    }

    @SubscribeEvent
    public static void registerCapabilities(final RegisterCapabilitiesEvent evt) {
        evt.registerItem(
                CuriosCapability.ITEM,
                (stack, context) -> new ICurio() {
                    @Override
                    public ItemStack getStack() {
                        return stack;
                    }

                    @Override
                    public CurioAttributeModifiers getDefaultCurioAttributeModifiers() {
                        CurioAttributeModifiers.Builder builder = CurioAttributeModifiers.builder();
                        builder.addModifier(Attributes.LUCK, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "crowsfoot_unlucky"),
                                -1, AttributeModifier.Operation.ADD_VALUE));
                        return builder.build();
                    }
                },
                CornItems.CROWSFOOT.get());
    }
}
