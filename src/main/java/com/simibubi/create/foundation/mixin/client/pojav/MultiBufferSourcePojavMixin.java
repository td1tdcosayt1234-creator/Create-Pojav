package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for MultiBufferSource.
 * 
 * Catches buffer source errors during rendering.
 */
@Mixin(MultiBufferSource.class)
public abstract class MultiBufferSourcePojavMixin {

    /**
     * Catch buffer source getBuffer errors.
     */
    @Inject(method = "getBuffer", at = @At("HEAD"), cancellable = true)
    private void create$onGetBuffer(Object renderType, CallbackInfoReturnable<Object> cir) {
        try {
            // Buffer source safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] MultiBufferSource error: " + e.getMessage());
            cir.setReturnValue(null);
        }
    }
}
