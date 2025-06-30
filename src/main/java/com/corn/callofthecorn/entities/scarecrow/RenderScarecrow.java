package com.corn.callofthecorn.entities.scarecrow;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class RenderScarecrow extends AbstractSkeletonRenderer<Scarecrow, SkeletonRenderState> {

    private static final ResourceLocation Scarecrow_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/scarecrow.png");

    public RenderScarecrow(EntityRendererProvider.Context ctx) {
//        super(p_174304_,new ScarecrowModel(p_174304_.bakeLayer(ClientEventSubscriber.SCARECROW_LAYER)),0.25F);
//        this.addLayer(new HumanoidArmorLayer<>(this, new ScarecrowModel(p_174304_.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new ScarecrowModel(p_174304_.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), p_174304_.getModelManager()));
        super(ctx, ClientEventSubscriber.SCARECROW_LAYER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState(); // TODO implement
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonRenderState p_114482_) {
        return Scarecrow_LOCATION;
    }
}
