package com.corn.callofthecorn.entities.scarecrow;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.ClientEventSubscriber;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RenderScarecrow extends MobRenderer<Scarecrow,ScarecrowModel<Scarecrow>> {

    private static final ResourceLocation Scarecrow_LOCATION = new ResourceLocation(Main.MOD_ID,"/textures/entities/scarecrow.png");

    public RenderScarecrow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new ScarecrowModel<>(p_174304_.bakeLayer(ClientEventSubscriber.SCARECROW_LAYER)),0.25F);
        this.addLayer(new HumanoidArmorLayer<>(this, new ScarecrowModel<>(p_174304_.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new ScarecrowModel(p_174304_.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), p_174304_.getModelManager()));

    }

    @Override
    public ResourceLocation getTextureLocation(Scarecrow p_114482_) {
        return Scarecrow_LOCATION;
    }
}
