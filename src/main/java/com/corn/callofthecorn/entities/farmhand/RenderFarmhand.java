package com.corn.callofthecorn.entities.farmhand;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.ClientEventSubscriber;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RenderFarmhand extends MobRenderer<Farmhand,FarmhandModel<Farmhand>> {

    private static final ResourceLocation Farmhand_LOCATION = new ResourceLocation(Main.MODID,"/textures/entities/farmhand.png");

    public RenderFarmhand(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new FarmhandModel<>(p_174304_.bakeLayer(ClientEventSubscriber.FARMHAND_LAYER)),0.25F);
        this.addLayer(new HumanoidArmorLayer<>(this, new FarmhandModel<>(p_174304_.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new FarmhandModel(p_174304_.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), p_174304_.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(Farmhand p_114482_)  {
        return Farmhand_LOCATION;
    }


}


