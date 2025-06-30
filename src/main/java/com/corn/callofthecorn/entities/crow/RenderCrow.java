package com.corn.callofthecorn.entities.crow;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Parrot;

public class RenderCrow extends MobRenderer<Crow, ParrotRenderState, ParrotModel> {

    private static final ResourceLocation CROW_LOCATION = ResourceLocation.fromNamespaceAndPath(Main.MOD_ID,"textures/entities/crow.png");

    public RenderCrow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new CrowModel(p_174304_.bakeLayer(ClientEventSubscriber.CROW_LAYER)),0.25F);

    }

    @Override
    public ParrotRenderState createRenderState() {
        return new ParrotRenderState();
    }

    @Override
    public void extractRenderState(Crow crow, ParrotRenderState renderState, float flap) {
        super.extractRenderState(crow, renderState, flap);
        float f = Mth.lerp(flap, crow.oFlap, crow.flap);
        float f1 = Mth.lerp(flap, crow.oFlapSpeed, crow.flapSpeed);
        renderState.flapAngle = (Mth.sin(f) + 1.0F) * f1;
        renderState.pose = ParrotModel.getPose(crow);
    }

    @Override
    public ResourceLocation getTextureLocation(ParrotRenderState crow) {
        return CROW_LOCATION;
    }

//    @Override
//    public float getBob(Parrot crow, float param) {
//        float f = Mth.lerp(param, crow.oFlap, crow.flap);
//        float f1 = Mth.lerp(param, crow.oFlapSpeed, crow.flapSpeed);
//        return (Mth.sin(f) + 1.0F) * f1;
//    }
}
