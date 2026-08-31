package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Minecraft's RenderTarget (Framebuffer).
 * 
 * The GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT error occurs when:
 * 1. Create mod's UIRenderHelper calls enableStencil() 
 * 2. Mobile GPUs (via GL4ES/VirGL/ANGLE) don't properly support stencil buffer attachments
 * 3. The framebuffer becomes incomplete and throws an exception
 * 
 * This mixin intercepts the enableStencil call and either:
 * - Skips stencil entirely on incompatible devices
 * - Falls back to a simpler rendering approach
 */
@Mixin(value = com.mojang.blaze3d.pipeline.RenderTarget.class, priority = 750)
public abstract class RenderTargetPojavMixin {

    @Unique
    private boolean create$pojavCompatMode = false;

    /**
     * Intercept create() to detect and handle framebuffer creation on mobile GPUs.
     */
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private void create$onCreate(int width, int height, boolean useDepth, CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            this.create$pojavCompatMode = true;
        }
    }

    /**
     * Intercept enableStencil to skip it on incompatible devices.
     * This is the main fix for GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT.
     */
    @Inject(method = "enableStencil", at = @At("HEAD"), cancellable = true)
    private void create$onEnableStencil(CallbackInfo ci) {
        if (PojavCompat.shouldSkipStencil()) {
            // Silently skip stencil buffer on mobile GPUs
            // This prevents the GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT crash
            ci.cancel();
        }
    }

    /**
     * Intercept setStencilEnabled to prevent crashes.
     */
    @Inject(method = "setStencilEnabled", at = @At("HEAD"), cancellable = true)
    private void create$onSetStencilEnabled(boolean enabled, CallbackInfo ci) {
        if (enabled && PojavCompat.shouldSkipStencil()) {
            ci.cancel();
        }
    }
}
