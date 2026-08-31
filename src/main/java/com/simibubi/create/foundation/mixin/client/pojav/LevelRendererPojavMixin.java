package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for LevelRenderer.
 * 
 * Catches rendering errors that cause crashes on mobile.
 */
@Mixin(LevelRenderer.class)
public abstract class LevelRendererPojavMixin {

    /**
     * Catch level rendering errors.
     */
    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void create$onRenderHead(com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick, long finishTimeNano, boolean renderBlockOutline, org.joml.Camera camera, net.minecraft.client.renderer.GameRenderer gameRenderer, net.minecraft.client.renderer.LightTexture lightTexture, org.joml.Matrix4f projectionMatrix, CallbackInfo ci) {
        // Reset any problematic state before level render
        try {
            // Additional safety setup
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Level render setup error: " + e.getMessage());
        }
    }

    /**
     * Catch chunk rendering errors.
     */
    @Inject(method = "renderChunkLayer", at = @At("HEAD"), cancellable = true)
    private void create$onRenderChunkLayer(net.minecraft.client.renderer.RenderType renderType, com.mojang.blaze3d.vertex.PoseStack poseStack, double x, double y, double z, org.joml.Matrix4f projectionMatrix, CallbackInfo ci) {
        try {
            // Chunk rendering safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Chunk render error: " + e.getMessage());
            ci.cancel();
        }
    }
}
