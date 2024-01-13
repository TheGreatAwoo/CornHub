package com.corn.callofthecorn.entities.farmhand;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class FarmhandModel<T extends Farmhand> extends SkeletonModel<T> {

    public FarmhandModel(ModelPart part){
        super(part);
    }

    public  static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16). addBox(-1.0F, -2.0F, -1.0F, 4.0F, 24.0F, 4.0F),             PartPose.offset(-10.0F, 4.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).  mirror().addBox(-1.0F, -2.0F, -1.0F, 4.0F, 24.0F, 4.0F),    PartPose.offset(10.0F, 4.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).  addBox(-1.0F, 0.0F, -1.0F, 4.0F, 12, 4.0F),              PartPose.offset(-3F, 24.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).   mirror().addBox(-1.0F, 0.0F, -1.0F, 4.0F, 12, 4.0F),       PartPose.offset(3.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }




}
