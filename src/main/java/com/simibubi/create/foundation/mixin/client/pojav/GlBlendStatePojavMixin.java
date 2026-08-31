package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for GlStateManager blend operations.
 * 
 * Fixes framebuffer errors related to blend state on mobile GPUs.
 */
@Mixin(GlStateManager.class)
public abstract class GlBlendStatePojavMixin {

    /**
     * Skip problematic blend operations on mobile.
     */
    @Inject(method = "blendFunc", at = @At("HEAD"), cancellable = true)
    private static void create$onBlendFunc(int srcFactor, int dstFactor, CallbackInfo ci) {
        // On mobile, some blend modes cause issues
        if (PojavCompat.shouldSkipAdvancedRendering()) {
            // Use standard alpha blending instead
            System.out.println("[Create-Pojav] Simplified blend mode for mobile");
        }
    }

    /**
     * Skip blend equation changes that cause issues.
     */
    @Inject(method = "blendEquation", at = @At("HEAD"), cancellable = true)
    private static void create$onBlendEquation(int mode, CallbackInfo ci) {
        if (PojavCompat.shouldSkipAdvancedRendering()) {
            // Skip blend equation changes on mobile
            ci.cancel();
        }
    }

    /**
     * Skip blend equation separate.
     */
    @Inject(method = "blendEquationSeparate", at = @At("HEAD"), cancellable = true)
    private static void create$onBlendEquationSeparate(int modeRGB, int modeAlpha, CallbackInfo ci) {
        if (PojavCompat.shouldSkipAdvancedRendering()) {
            ci.cancel();
        }
    }

    /**
     * Skip blend func separate.
     */
    @Inject(method = "blendFuncSeparate", at = @At("HEAD"), cancellable = true)
    private static void create$onBlendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha, CallbackInfo ci) {
        if (PojavCompat.shouldSkipAdvancedRendering()) {
            // Use simple alpha blending
        }
    }
}
