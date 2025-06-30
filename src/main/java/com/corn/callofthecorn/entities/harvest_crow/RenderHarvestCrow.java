package com.corn.callofthecorn.entities.harvest_crow;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.entities.crow.Crow;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RenderHarvestCrow extends MobRenderer<HarvestCrow, ParrotRenderState, HarvestCrowModel<HarvestCrow>> {

    private static final ResourceLocation HARVEST_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/harvest.png");

    public RenderHarvestCrow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new HarvestCrowModel<HarvestCrow>(p_174304_.bakeLayer(ClientEventSubscriber.HARVEST_LAYER)),1F);
    }

    @Override
    public ParrotRenderState createRenderState() {
        return new ParrotRenderState();
    }

    @Override
    public void extractRenderState(HarvestCrow crow, ParrotRenderState renderState, float flap) {
        super.extractRenderState(crow, renderState, flap);
        float f = Mth.lerp(flap, crow.oFlap, crow.flap);
        float f1 = Mth.lerp(flap, crow.oFlapSpeed, crow.flapSpeed);
        renderState.flapAngle = (Mth.sin(f) + 1.0F) * f1;
        renderState.pose = ParrotModel.Pose.FLYING;
    }

    @Override
    public ResourceLocation getTextureLocation(ParrotRenderState p_114482_) {
        return HARVEST_LOCATION;
    }

}
