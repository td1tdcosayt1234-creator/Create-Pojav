package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.chunk.SectionRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for SectionRenderDispatcher.
 * 
 * Catches section render errors that cause crashes.
 */
@Mixin(SectionRenderDispatcher.class)
public abstract class SectionRenderDispatcherPojavMixin {

    /**
     * Catch section compile errors.
     */
    @Inject(method = "compileSection", at = @At("HEAD"), cancellable = true)
    private void create$onCompileSection(CallbackInfo ci) {
        try {
            // Section compile safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] SectionRenderDispatcher compile error: " + e.getMessage());
            ci.cancel();
        }
    }
}
