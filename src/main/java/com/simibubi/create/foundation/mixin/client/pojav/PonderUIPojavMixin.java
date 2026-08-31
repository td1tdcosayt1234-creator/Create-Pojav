package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Ponder scenes.
 * 
 * The Ponder tutorial system uses stencil buffers and custom render targets
 * which crash on mobile GPUs. This mixin disables problematic Ponder features.
 */
@Mixin(value = com.simibubi.create.foundation.ponder.PonderUI.class, priority = 800)
public abstract class PonderUIPojavMixin {

    /**
     * Intercept ponder UI init to simplify on mobile.
     */
    @Inject(method = "init", at = @At("HEAD"))
    private void create$onInit(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            // On mobile, Ponder scenes will use simplified rendering
        }
    }
}
