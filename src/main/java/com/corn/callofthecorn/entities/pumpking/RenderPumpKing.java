package com.corn.callofthecorn.entities.pumpking;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class RenderPumpKing extends MobRenderer<PumpKing, SkeletonRenderState, PumpKingModel> {

    private static final ResourceLocation PUMPKING_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/pumpking.png");

    public RenderPumpKing(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new PumpKingModel(p_174304_.bakeLayer(ClientEventSubscriber.PUMPKING_LAYER)),.5F);
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState(); // TODO implement
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonRenderState p_114482_) {
        return PUMPKING_LOCATION;
    }

}
