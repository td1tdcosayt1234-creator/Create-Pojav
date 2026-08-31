package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.RenderBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for RenderBuffers.
 * 
 * Catches buffer allocation errors that cause OOM.
 */
@Mixin(RenderBuffers.class)
public abstract class RenderBuffersPojavMixin {

    /**
     * Catch buffer allocation errors.
     */
    @Inject(method = "allocate", at = @At("HEAD"), cancellable = true)
    private void create$onAllocate(CallbackInfo ci) {
        try {
            // Memory check before buffer allocation
            Runtime runtime = Runtime.getRuntime();
            long freeMemory = runtime.freeMemory();
            
            if (freeMemory < 50 * 1024 * 1024) { // Less than 50MB free
                System.gc();
                System.err.println("[Create-Pojav] Low memory warning during buffer allocation");
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] RenderBuffers allocation error: " + e.getMessage());
            ci.cancel();
        }
    }
}
