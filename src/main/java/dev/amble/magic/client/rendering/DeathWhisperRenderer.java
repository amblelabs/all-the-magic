package dev.amble.magic.client.rendering;

import dev.amble.magic.core.entities.DeathWhisperEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.AllayEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class DeathWhisperRenderer extends EntityRenderer<DeathWhisperEntity> {

    public static final Identifier TEXTURE = new Identifier("textures/entity/allay/allay.png");
    AllayEntityModel model;

    public DeathWhisperRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model = new AllayEntityModel(ctx.getPart(EntityModelLayers.ALLAY));
    }

    @Override
    public void render(DeathWhisperEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.translate(0, 1.75f, 0);
        matrices.scale(1, -1, 1);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f));
        matrices.translate(0, Math.sin(entity.age * 25f) * 0.1f, 0);
        this.model.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(this.getTexture(entity))),
                0xF000F0,
                OverlayTexture.DEFAULT_UV,
                1.0f, 1.0f, 1.0f, 0.5f);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(DeathWhisperEntity entity) {
        return TEXTURE;
    }
}
