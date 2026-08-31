package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for GlStateManager.
 * Intercepts stencil buffer state changes to prevent crashes on mobile GPUs.
 */
@Mixin(GlStateManager.class, priority = 800)
public abstract class GlStateManagerPojavMixin {

    /**
     * Intercept stencil enable/disable to skip on incompatible devices.
     */
    @Inject(method = "stencilFunc", at = @At("HEAD"), cancellable = true)
    private static void create$onStencilFunc(int func, int ref, int mask, CallbackInfo ci) {
        if (PojavCompat.shouldSkipStencil()) {
            ci.cancel();
        }
    }

    @Inject(method = "stencilMask", at = @At("HEAD"), cancellable = true)
    private static void create$onStencilMask(int mask, CallbackInfo ci) {
        if (PojavCompat.shouldSkipStencil()) {
            ci.cancel();
        }
    }

    @Inject(method = "stencilOp", at = @At("HEAD"), cancellable = true)
    private static void create$onStencilOp(int sfail, int dpfail, int dppass, CallbackInfo ci) {
        if (PojavCompat.shouldSkipStencil()) {
            ci.cancel();
        }
    }
}
