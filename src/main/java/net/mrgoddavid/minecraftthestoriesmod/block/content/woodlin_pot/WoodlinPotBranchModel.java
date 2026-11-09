package net.mrgoddavid.minecraftthestoriesmod.block.content.woodlin_pot;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

/**
 * @author Mr. GodDavid
 * @since 11/8/2026
 */
public class WoodlinPotBranchModel extends Model<WoodlinPotBlockRenderState> {

    private final ModelPart model;
    private final ModelPart base;

    public WoodlinPotBranchModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        this.model = root.getChild("model");
        this.base = this.model.getChild("base");
    }

    public static LayerDefinition createTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition model = partdefinition.addOrReplaceChild("model", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
        model.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, 2.0F, 6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(9, 7).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
}