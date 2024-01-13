package com.corn.callofthecorn.entities.Harvester;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.Init.ClientEventSubscriber;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RenderHarvester extends MobRenderer<Harvester,HarvesterModel<Harvester>> {

    private static final ResourceLocation HARVESTER_LOCATION = new ResourceLocation(Main.MOD_ID,"/textures/entities/harvester.png");

    public RenderHarvester(EntityRendererProvider.Context p_174304_) {

        this(p_174304_, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);

        //super(p_174304_,new HarvesterModel<Harvester>(p_174304_.bakeLayer(ClientEventSubscriber.HARVESTER_LAYER)),1F);
    }

    @Override
    public ResourceLocation getTextureLocation(Harvester p_114482_) {
        return HARVESTER_LOCATION;
    }



    public RenderHarvester(EntityRendererProvider.Context context, ModelLayerLocation layerLocation, ModelLayerLocation innerLayer, ModelLayerLocation outerLayer) {
        super(context,new HarvesterModel<>(context.bakeLayer(ClientEventSubscriber.HARVESTER_LAYER)),.3F);
        this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(context.bakeLayer(innerLayer)), new SkeletonModel(context.bakeLayer(outerLayer)), context.getModelManager()));
    }


}
