package com.corn.callofthecorn.entities.farmhand;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class FarmhandModel extends SkeletonModel<SkeletonRenderState> {

    public FarmhandModel(ModelPart part){
        super(part);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = meshdefinition.getRoot();
        PartDefinition headPart = root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F)
                        .texOffs(24, 0).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offset(0.0F, -4.0F, 0.0F)
        );
        PartDefinition hat = headPart.addOrReplaceChild(
                "hat", CubeListBuilder.create().texOffs(32, 0)
                        .addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F,
                                new CubeDeformation(0.5F)), PartPose.ZERO
        );
        hat.addOrReplaceChild(
                "hat_rim",
                CubeListBuilder.create().texOffs(64, 23).addBox(-11.0F, -11.0F, -6.0F, 22.0F, 22.0F, 1.0F),
                PartPose.rotation((float) (-Math.PI / 2), 0.0F, 0.0F)
        );
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 18)
                        .addBox(-3.0F, 2.0F, -2.0F, 4.0F, 24.0F, 4.0F),
                PartPose.offset(-7.0F, -4.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 18).mirror()
                        .addBox(-1.0F, 2.0F, -2.0F, 4.0F, 24.0F, 4.0F),
                PartPose.offset(7.0F, -4.0F, 0.0F));
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 18).addBox(-6.0F, 0.0F, -2.0F, 12.0F, 14.0F, 4.0F),
                PartPose.offset(0.0F, -2.0F, 0.0F)
        );
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 48)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12, 4.0F),
                PartPose.offset(-3F, 12.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 48).mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12, 4.0F),
                PartPose.offset(3.0F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }




}
