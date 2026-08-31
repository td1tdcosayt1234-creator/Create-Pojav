package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Flywheel backend.
 * 
 * Disables Flywheel's instanced rendering on mobile GPUs.
 * Flywheel uses advanced OpenGL compute shaders not available on mobile.
 * 
 * On mobile, Create falls back to vanilla entity/block rendering.
 * This prevents crashes from Flywheel's advanced GPU features.
 */
@Mixin(value = dev.engine_room.flywheel.backend.FlywheelBackend.class, priority = 700)
public abstract class FlywheelBackendMixin {

    /**
     * Intercept backend initialization to disable on incompatible devices.
     */
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void create$onInit(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            // Disable Flywheel backend on mobile devices
            // Create will fall back to vanilla rendering
            ci.cancel();
        }
    }

    /**
     * Intercept backend reload to maintain disabled state.
     */
    @Inject(method = "reload", at = @At("HEAD"), cancellable = true)
    private void create$onReload(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            ci.cancel();
        }
    }
}
