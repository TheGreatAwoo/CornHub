package com.corn.callofthecorn;

import com.corn.callofthecorn.init.CornBlocks;
import com.corn.callofthecorn.init.CornItems;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Main.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventSubscriber {

    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        event.register(Registries.ITEM,
                itemRegisterHelper -> {
                    CornBlocks.BLOCKS.getEntries().stream().map(Supplier::get).forEach(
                            block -> {
                                final Item.Properties properties = new Item.Properties();
                                final BlockItem blockItem = new BlockItem(block, properties);
                                itemRegisterHelper.register(BuiltInRegistries.BLOCK.getKey(block), blockItem);
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
                    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext,
                                                                                        UUID uuid) {
                        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
                        modifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "Luck", -1, AttributeModifier.Operation.ADDITION));
                        return modifiers;
                    }
                },
                CornItems.CROWSFOOT.get());
    }
}
