package com.corn.callofthecorn.entities.crop_watcher;

import com.corn.callofthecorn.Main;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class RenderCropWatcher extends AbstractSkeletonRenderer<CropWatcher, SkeletonRenderState> {

    private static final ResourceLocation CROPWATCHER_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/cropwatcher.png");

    public RenderCropWatcher(EntityRendererProvider.Context ctx) {
        super(ctx, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState(); // TODO implement
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonRenderState p_368654_) {
        return CROPWATCHER_LOCATION;
    }

//    public RenderCropWatcher(EntityRendererProvider.Context ctx, ModelLayerLocation layer1, ModelLayerLocation layer2, ModelLayerLocation layer3) {
//        super(ctx, new CropWatcherModel(ctx.bakeLayer(ClientEventSubscriber.CROPWATCHER_LAYER)),.3F);
//        this.addLayer(new HumanoidArmorLayer<>(
//                this,
//                new SkeletonModel(ctx.bakeLayer(layer2)),
//                new SkeletonModel(ctx.bakeLayer(layer3)),
//                ctx.getModelManager())
//        );
//    }


}


