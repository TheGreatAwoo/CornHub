package com.corn.callofthecorn.entities.farmhand;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class RenderFarmhand extends AbstractSkeletonRenderer<Farmhand, SkeletonRenderState> {

    private static final ResourceLocation Farmhand_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/farmhand.png");

    public RenderFarmhand(EntityRendererProvider.Context ctx) {
        super(ctx, ClientEventSubscriber.FARMHAND_LAYER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);

//        super(p_174304_,new FarmhandModel(p_174304_.bakeLayer(ClientEventSubscriber.FARMHAND_LAYER)),0.25F);
//        this.addLayer(new HumanoidArmorLayer<>(this, new FarmhandModel(p_174304_.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new FarmhandModel(p_174304_.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), p_174304_.getModelManager()));

    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonRenderState p_114482_)  {
        return Farmhand_LOCATION;
    }


}


