package com.corn.callofthecorn.entities.harvest_crow;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.ClientEventSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderHarvestCrow extends MobRenderer<HarvestCrow, HarvestCrowModel<HarvestCrow>> {

    private static final ResourceLocation HARVEST_LOCATION = new ResourceLocation(Main.MOD_ID,"/textures/entities/harvest.png");

    public RenderHarvestCrow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new HarvestCrowModel<HarvestCrow>(p_174304_.bakeLayer(ClientEventSubscriber.HARVEST_LAYER)),1F);
    }

    @Override
    public ResourceLocation getTextureLocation(HarvestCrow p_114482_) {
        return HARVEST_LOCATION;
    }

}
