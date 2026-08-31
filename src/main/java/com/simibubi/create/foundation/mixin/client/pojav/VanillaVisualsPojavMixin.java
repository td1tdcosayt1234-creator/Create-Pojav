package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Flywheel Visualization.
 * 
 * Prevents crashes when Flywheel visualization system tries to use
 * GPU features not available on mobile devices.
 */
@Mixin(value = dev.engine_room.flywheel.vanilla.VanillaVisuals.class, priority = 700)
public abstract class VanillaVisualsPojavMixin {

    /**
     * Intercept visualization init to skip on mobile.
     */
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void create$onInit(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            ci.cancel();
        }
    }
}
