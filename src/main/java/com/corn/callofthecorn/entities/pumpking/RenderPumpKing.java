package com.corn.callofthecorn.entities.pumpking;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderPumpKing extends MobRenderer<PumpKing, PumpKingModel<PumpKing>> {

    private static final ResourceLocation PUMPKING_LOCATION = new ResourceLocation(Main.MODID,"/textures/entities/pumpking.png");

    public RenderPumpKing(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new PumpKingModel<>(p_174304_.bakeLayer(ClientEventSubscriber.PUMPKING_LAYER)),.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PumpKing p_114482_) {
        return PUMPKING_LOCATION;
    }


}
