package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class CornEquipmentInfoProvider implements DataProvider {

    private final PackOutput.PathProvider path;

    public CornEquipmentInfoProvider(PackOutput output) {
        this.path = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private void add(BiConsumer<ResourceLocation, EquipmentClientInfo> registrar) {
        registrar.accept(
                // Must match Equippable#assetId
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "cornmetal"),
                EquipmentClientInfo.builder()
                        .addLayers(
                                EquipmentClientInfo.LayerType.HUMANOID, new EquipmentClientInfo.Layer(
                                        // Points to assets/<namespace>/textures/entity/equipment/humanoid/<path>.png
                                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "cornmetal"),
                                        Optional.empty(), false
                                )
                        )
                        .addLayers(
                                EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS, new EquipmentClientInfo.Layer(
                                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "cornmetal"),
                                        Optional.empty(), false
                                )
                        )
                        .build()
        );
        registrar.accept(
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maizerite"),
                EquipmentClientInfo.builder()
                        .addLayers(
                                EquipmentClientInfo.LayerType.HUMANOID, new EquipmentClientInfo.Layer(
                                        // Points to assets/<namespace>/textures/entity/equipment/humanoid/<path>.png
                                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maizerite"),
                                        Optional.empty(), false
                                )
                        )
                        .addLayers(
                                EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS, new EquipmentClientInfo.Layer(
                                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "maizerite"),
                                        Optional.empty(), false
                                )
                        )
                        .build()
        );
        registrar.accept(
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "pumpkin"),
                EquipmentClientInfo.builder()
                        .addLayers(
                                EquipmentClientInfo.LayerType.HUMANOID, new EquipmentClientInfo.Layer(
                                        // Points to assets/<namespace>/textures/entity/equipment/humanoid/<path>.png
                                        ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "pumpkin"),
                                        Optional.empty(), false
                                )
                        )
                        .build()
        );
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceLocation, EquipmentClientInfo> map = new HashMap<>();
        this.add((name, info) -> {
            if (map.putIfAbsent(name, info) != null) {
                throw new IllegalStateException("Tried to register equipment client info twice for id: " + name);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.path, map);
    }

    @Override
    public String getName() {
        return "Equipment Client Infos: " + Main.MOD_ID;
    }
}