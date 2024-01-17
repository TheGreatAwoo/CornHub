package com.corn.callofthecorn.entities.harvest_crow;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class HarvestCrowModel<T extends HarvestCrow> extends QuadrupedModel<T> {



    private final ModelPart tail;
   // private final ModelPart shellTop;
    private final ModelPart belly;
    private final ModelPart neck;


    public HarvestCrowModel(ModelPart part){
        super(part, false, 1.1F, 1.5F, 2.0F, 2.0F, 24);
        this.tail = part.getChild("tail");
       // this.shellTop = part.getChild("shell_top");
        this.belly = part.getChild("belly");
        this.neck = part.getChild("neck");
    }



    public static LayerDefinition createBodyLayer() {
                                                                                                                                                                    //Back , Height , Width
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();
        def.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, -3.0F, 30, 60, 8), PartPose.offset(0.0F, .0F, 0.0F));
        def.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -2.5F, 30, 48, 15), PartPose.offset(-.5F, -3, -10.0F));

        def.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(100, 0).addBox(-0.5F, -1.0F, -0.5F, 4, 20, 4), PartPose.offsetAndRotation(2, 2, -22, 0, 0, 0.0F));
        def.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(100, 0).addBox(-0.5F, -1.0F, -0.5F, 4, 20, 4), PartPose.offsetAndRotation(20, 2, -22, -0, -0, 0.0F));

        def.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(100, 100).addBox(-0.5F, -1.0F, -0.5F, 0, 25, 65), PartPose.offsetAndRotation(25, -40, -50, -0, 0, 0.0F));
        def.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(100, 100).addBox(-0.5F, -1.0F, -0.5F, 0, 25, 65), PartPose.offsetAndRotation(1, -40, -50, 0, 0, 0.0F));

        def.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 40, 1, 40), PartPose.offset(-7, 5, -5));

        def.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 12, 24, 12), PartPose.offset(7, -50, -50));
        def.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -0.5F, 12, 10, 24), PartPose.offset(18, -56, -42 ));

        //def.addOrReplaceChild("shell_top", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5), PartPose.offset(0.0F, 20.5F, 0.0F));

        return LayerDefinition.create(modelDefinition, 64, 32);
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg, this.tail, this.belly,this.neck); //this.shellTop
    }

    @Override
    public void setupAnim(@Nonnull T Harvest, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot =  headPitch * 0.017453292F;
        this.head.yRot =3.1F+ netHeadYaw * 0.017453292F;

        this.tail.yRot = Mth.cos(limbSwing * 0.4662F) * 0.6F * limbSwingAmount;
        this.body.xRot= -15;
        this.belly.xRot= -15;
        this.tail.xRot=-.5F;
        this.rightHindLeg.xRot = 0.5235987755982988F + (Mth.cos(limbSwing * 2.0F) * 1.4F * limbSwingAmount);
        this.leftHindLeg.xRot = -0.5235987755982988F + -(Mth.cos(limbSwing * 2.0F) * 1.4F * limbSwingAmount);

        this.rightFrontLeg.zRot = -0.5235987755982988F + -(Mth.cos(limbSwing * 2.0F) * 1.4F * limbSwingAmount);
        this.leftFrontLeg.zRot = 0.5235987755982988F + (Mth.cos(limbSwing * 2.0F) * 1.4F * limbSwingAmount);
        this.rightFrontLeg.xRot=-.5F;
        this.leftFrontLeg.xRot=-.5F;

    }


}
