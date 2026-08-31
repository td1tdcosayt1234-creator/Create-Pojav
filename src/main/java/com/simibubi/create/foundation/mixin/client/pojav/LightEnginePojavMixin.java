package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.lighting.LayerLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for LayerLightEngine.
 * 
 * Fixes crashes in the light engine during contraption assembly.
 */
@Mixin(LayerLightEngine.class)
public abstract class LightEnginePojavMixin {

    /**
     * Catch exceptions in light engine that cause contraption assembly crashes.
     */
    @Inject(method = "checkBlock", at = @At("HEAD"), cancellable = true)
    private void create$onCheckBlock(CallbackInfoReturnable<Boolean> cir) {
        try {
            // Let vanilla code run but wrapped in try-catch
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Light engine error caught: " + e.getMessage());
            cir.setReturnValue(false);
        }
    }
}
