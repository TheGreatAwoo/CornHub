package com.corn.callofthecorn.entities.crow;

import com.corn.callofthecorn.ClientEventSubscriber;
import com.corn.callofthecorn.Main;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Parrot;

public class RenderCrow extends MobRenderer<Parrot, ParrotModel>{

    private static final ResourceLocation Crow_LOCATION = new ResourceLocation(Main.MOD_ID,"/textures/entities/crow.png");

    public RenderCrow(EntityRendererProvider.Context p_174304_) {
        super(p_174304_,new CrowModel<Crow>(p_174304_.bakeLayer(ClientEventSubscriber.CROW_LAYER)),0.25F);

    }

    @Override
    public ResourceLocation getTextureLocation(Parrot crow) {
        return Crow_LOCATION;
    }

    @Override
    public float getBob(Parrot crow, float param) {
        float f = Mth.lerp(param, crow.oFlap, crow.flap);
        float f1 = Mth.lerp(param, crow.oFlapSpeed, crow.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }

}
