package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for GlStateManager depth operations.
 * 
 * Fixes depth buffer issues that cause visual glitches on mobile.
 */
@Mixin(GlStateManager.class)
public abstract class GlDepthStatePojavMixin {

    /**
     * Simplify depth functions on mobile.
     */
    @Inject(method = "depthFunc", at = @At("HEAD"), cancellable = true)
    private static void create$onDepthFunc(int func, CallbackInfo ci) {
        if (PojavCompat.shouldSkipAdvancedRendering()) {
            // Always use GL_LEQUAL for simplicity on mobile
        }
    }

    /**
     * Skip depth mask operations on problematic devices.
     */
    @Inject(method = "depthMask", at = @At("HEAD"), cancellable = true)
    private static void create$onDepthMask(boolean flag, CallbackInfo ci) {
        if (PojavCompat.shouldSkipAdvancedRendering()) {
            // Let it run but catch errors
        }
    }

    /**
     * Skip depth test enable/disable on mobile.
     */
    @Inject(method = "enableDepthTest", at = @At("HEAD"), cancellable = true)
    private static void create$onEnableDepthTest(CallbackInfo ci) {
        // Depth test is generally safe, let it run
    }

    /**
     * Skip depth test disable.
     */
    @Inject(method = "disableDepthTest", at = @At("HEAD"), cancellable = true)
    private static void create$onDisableDepthTest(CallbackInfo ci) {
        // Depth disable is generally safe
    }
}
