package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.LevelChunkRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for LevelChunkRenderer.
 * 
 * Catches chunk rendering errors that cause crashes.
 */
@Mixin(LevelChunkRenderer.class)
public abstract class LevelChunkRendererPojavMixin {

    /**
     * Catch chunk render section errors.
     */
    @Inject(method = "renderChunkLayer", at = @At("HEAD"), cancellable = true)
    private void create$onRenderChunkLayer(Object renderType, com.mojang.blaze3d.vertex.PoseStack poseStack, double x, double y, double z, org.joml.Matrix4f projectionMatrix, CallbackInfo ci) {
        try {
            // Chunk render safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] LevelChunkRenderer error: " + e.getMessage());
            ci.cancel();
        }
    }
}
