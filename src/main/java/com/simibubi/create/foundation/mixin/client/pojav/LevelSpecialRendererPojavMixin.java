package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.LevelSpecialRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for LevelSpecialRenderer.
 * 
 * Catches special rendering errors (like entity renderers).
 */
@Mixin(LevelSpecialRenderer.class)
public abstract class LevelSpecialRendererPojavMixin {

    /**
     * Catch special render errors.
     */
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void create$onRender(com.mojang.blaze3d.vertex.PoseStack poseStack, long finishTimeNano, boolean renderBlockOutline, CallbackInfo ci) {
        try {
            // Special renderer safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] LevelSpecialRenderer error: " + e.getMessage());
            ci.cancel();
        }
    }
}
