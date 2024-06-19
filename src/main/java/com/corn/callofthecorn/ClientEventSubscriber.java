package com.corn.callofthecorn;

import com.corn.callofthecorn.entities.crop_watcher.CropWatcherModel;
import com.corn.callofthecorn.entities.crop_watcher.RenderCropWatcher;
import com.corn.callofthecorn.entities.crow.CrowModel;
import com.corn.callofthecorn.entities.crow.RenderCrow;
import com.corn.callofthecorn.entities.farmhand.FarmhandModel;
import com.corn.callofthecorn.entities.farmhand.RenderFarmhand;
import com.corn.callofthecorn.entities.harvest_crow.HarvestCrowModel;
import com.corn.callofthecorn.entities.harvest_crow.RenderHarvestCrow;
import com.corn.callofthecorn.entities.harvester.HarvesterModel;
import com.corn.callofthecorn.entities.harvester.RenderHarvester;
import com.corn.callofthecorn.entities.pumpking.PumpKingModel;
import com.corn.callofthecorn.entities.pumpking.RenderPumpKing;
import com.corn.callofthecorn.entities.scarecrow.RenderScarecrow;
import com.corn.callofthecorn.entities.scarecrow.ScarecrowModel;
import com.corn.callofthecorn.init.CornMobs;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventSubscriber {

    public static final ModelLayerLocation SCARECROW_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"scarecrow"),"scarecrow");
    public static final ModelLayerLocation FARMHAND_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"farmhand"),"farmhand");
    public static final ModelLayerLocation HARVESTER_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"harvester"),"harvester");
    public static final ModelLayerLocation HARVEST_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"harvest"),"harvest");
    public static final ModelLayerLocation CROPWATCHER_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"cropwatcher"),"cropwatcher");
    public static final ModelLayerLocation PUMPKING_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"pumpking"),"pumpking");
    public static final ModelLayerLocation CROW_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID,"crow"),"crow");

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(CornMobs.SCARECROW.get(), RenderScarecrow::new);
        event.registerEntityRenderer(CornMobs.FARMHAND.get(), RenderFarmhand::new);
        event.registerEntityRenderer(CornMobs.HARVESTER.get(), RenderHarvester::new);
        event.registerEntityRenderer(CornMobs.HARVEST.get(), RenderHarvestCrow::new);
        event.registerEntityRenderer(CornMobs.CROPWATCHER.get(), RenderCropWatcher::new);
        event.registerEntityRenderer(CornMobs.PUMPKING.get(), RenderPumpKing::new);
        event.registerEntityRenderer(CornMobs.CROW.get(), RenderCrow::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(SCARECROW_LAYER, ScarecrowModel::createBodyLayer);
        event.registerLayerDefinition(FARMHAND_LAYER, FarmhandModel::createBodyLayer);
        event.registerLayerDefinition(HARVESTER_LAYER, HarvesterModel::createBodyLayer);
        event.registerLayerDefinition(HARVEST_LAYER, HarvestCrowModel::createBodyLayer);
        event.registerLayerDefinition(CROPWATCHER_LAYER, CropWatcherModel::createBodyLayer);
        event.registerLayerDefinition(PUMPKING_LAYER, PumpKingModel::createBodyLayer);
        event.registerLayerDefinition(CROW_LAYER, CrowModel::createBodyLayer);
    }


}
