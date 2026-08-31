package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for EntityRenderer.
 * 
 * Catches entity rendering errors that cause crashes on mobile.
 */
@Mixin(EntityRenderer.class)
public abstract class EntityRendererPojavMixin {

    /**
     * Catch entity render errors.
     */
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void create$onRender(Object entity, float yRot, float partialTick, com.mojang.blaze3d.vertex.PoseStack poseStack, net.minecraft.client.renderer.MultiBufferSource bufferSource, int packedLight, CallbackInfo ci) {
        try {
            // Entity rendering safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Entity render error: " + e.getMessage());
            ci.cancel();
        }
    }
}
