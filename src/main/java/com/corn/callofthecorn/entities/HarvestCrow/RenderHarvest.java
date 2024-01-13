package com.corn.callofthecorn.entities.HarvestCrow;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.Init.ClientEventSubscriber;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderHarvest extends MobRenderer<Harvest,HarvestModel<Harvest>> {

    private static final ResourceLocation HARVEST_LOCATION = new ResourceLocation(Main.MOD_ID,"/textures/entities/harvest.png");

    public RenderHarvest(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new HarvestModel<Harvest>(p_174304_.bakeLayer(ClientEventSubscriber.HARVEST_LAYER)),1F);
    }

    @Override
    public ResourceLocation getTextureLocation(Harvest p_114482_) {
        return HARVEST_LOCATION;
    }

}
