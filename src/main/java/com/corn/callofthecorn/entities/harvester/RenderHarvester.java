package com.corn.callofthecorn.entities.harvester;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class RenderHarvester extends AbstractSkeletonRenderer<Harvester, SkeletonRenderState> {

    private static final ResourceLocation HARVESTER_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/harvester.png");

    public RenderHarvester(EntityRendererProvider.Context p_174304_) {

        super(p_174304_, ClientEventSubscriber.HARVESTER_LAYER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);

        //super(p_174304_,new HarvesterModel<Harvester>(p_174304_.bakeLayer(ClientEventSubscriber.HARVESTER_LAYER)),1F);
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState(); // TODO implement
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonRenderState p_114482_) {
        return HARVESTER_LOCATION;
    }



//    public RenderHarvester(EntityRendererProvider.Context context, ModelLayerLocation layerLocation, ModelLayerLocation innerLayer, ModelLayerLocation outerLayer) {
//        super(context,new HarvesterModel<>(context.bakeLayer(ClientEventSubscriber.HARVESTER_LAYER)),.3F);
//        this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(context.bakeLayer(innerLayer)), new SkeletonModel(context.bakeLayer(outerLayer)), context.getModelManager()));
//    }


}
