package com.corn.callofthecorn.entities.CropWatcher;

import com.corn.callofthecorn.Init.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RenderCropWatcher extends HumanoidMobRenderer<CropWatcher, CropWatcherModel<CropWatcher>> {

    private static final ResourceLocation CROPWATCHER_LOCATION = new ResourceLocation(Main.MODID,"/textures/entities/cropwatcher.png");

    public RenderCropWatcher(EntityRendererProvider.Context p_174304_) {
        this(p_174304_, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);

        //super(p_174304_,new CropWatcherModel<>(p_174304_.bakeLayer(ClientEventSubscriber.CROPWATCHER_LAYER)),.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(CropWatcher p_114482_) {
        return CROPWATCHER_LOCATION;
    }


    public RenderCropWatcher(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_,new CropWatcherModel<>(p_174382_.bakeLayer(ClientEventSubscriber.CROPWATCHER_LAYER)),.3F);
        this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(p_174382_.bakeLayer(p_174384_)), new SkeletonModel(p_174382_.bakeLayer(p_174385_))));
    }


}


