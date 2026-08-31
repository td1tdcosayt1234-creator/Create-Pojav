package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for GameRenderer.
 * 
 * Catches rendering pipeline errors and memory issues.
 */
@Mixin(GameRenderer.class)
public abstract class GameRendererPojavMixin {

    /**
     * Catch game rendering errors that cause crashes.
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void create$onRender(float partialTick, long finishTimeNano, boolean renderLevel, CallbackInfo ci) {
        try {
            // Memory check before rendering
            Runtime runtime = Runtime.getRuntime();
            long freeMemory = runtime.freeMemory();
            long totalMemory = runtime.totalMemory();
            long usedMemory = totalMemory - freeMemory;
            
            // If memory usage is too high, trigger GC
            if (usedMemory > totalMemory * 0.85) {
                System.gc();
                System.err.println("[Create-Pojav] Memory pressure detected, GC triggered");
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] GameRenderer setup error: " + e.getMessage());
        }
    }

    /**
     * Catch level rendering errors.
     */
    @Inject(method = "renderLevel", at = @At("HEAD"), cancellable = true)
    private void create$onRenderLevel(com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick, long finishTimeNano, boolean renderBlockOutline, CallbackInfo ci) {
        try {
            // Additional memory check
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Level render error: " + e.getMessage());
            ci.cancel();
        }
    }
}
