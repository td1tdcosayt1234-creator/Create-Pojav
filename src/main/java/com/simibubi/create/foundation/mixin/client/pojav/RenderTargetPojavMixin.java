package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher fix for GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT crash.
 *
 * Create calls RenderTarget.enableStencil() during Ponder UI init.
 * On mobile GPUs (GL4ES/Krypton/GLES) this fails and crashes the game.
 * This mixin skips stencil buffer operations on incompatible devices.
 */
@Mixin(value = com.mojang.blaze3d.pipeline.RenderTarget.class, priority = 750)
public abstract class RenderTargetPojavMixin {

    @Inject(method = "enableStencil", at = @At("HEAD"), cancellable = true)
    private void create$skipStencilOnMobile(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            ci.cancel();
        }
    }

    @Inject(method = "setStencilEnabled", at = @At("HEAD"), cancellable = true)
    private void create$skipSetStencilOnMobile(boolean enabled, CallbackInfo ci) {
        if (enabled && PojavCompat.isIncompatibleDevice()) {
            ci.cancel();
        }
    }
}
