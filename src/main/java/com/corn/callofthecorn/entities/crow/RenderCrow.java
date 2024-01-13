package com.corn.callofthecorn.entities.crow;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class RenderCrow extends MobRenderer{

    private static final ResourceLocation Crow_LOCATION = new ResourceLocation(Main.MOD_ID,"/textures/entities/crow.png");

    public RenderCrow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new CrowModel<Crow>(p_174304_.bakeLayer(ClientEventSubscriber.CROW_LAYER)),0.25F);

    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_114482_) {
        return Crow_LOCATION;
    }

    //@Override
    //public ResourceLocation getTextureLocation(Crow p_114482_) {return Crow_LOCATION;}
}
