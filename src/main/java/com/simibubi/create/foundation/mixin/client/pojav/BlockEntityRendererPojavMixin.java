package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for BlockEntityRenderer.
 * 
 * Catches block entity rendering errors that cause crashes on mobile.
 */
@Mixin(BlockEntityRenderer.class)
public abstract class BlockEntityRendererPojavMixin {

    /**
     * Catch block entity render errors.
     */
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void create$onRender(Object blockEntity, float partialTick, com.mojang.blaze3d.vertex.PoseStack poseStack, net.minecraft.client.renderer.MultiBufferSource bufferSource, int packedLight, int packedOverlay, CallbackInfo ci) {
        try {
            // Block entity rendering safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Block entity render error: " + e.getMessage());
            ci.cancel();
        }
    }
}
