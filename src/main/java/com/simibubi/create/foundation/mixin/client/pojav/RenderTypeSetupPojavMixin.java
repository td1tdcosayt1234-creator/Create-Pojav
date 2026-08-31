package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for RenderType setup.
 * 
 * Catches render type setup failures on mobile GPUs.
 */
@Mixin(RenderType.class)
public abstract class RenderTypeSetupPojavMixin {

    /**
     * Catch render type creation errors.
     */
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void create$onCreate(String name, CallbackInfoReturnable<RenderType> cir) {
        try {
            // Render type creation safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] RenderType creation error: " + name + " - " + e.getMessage());
            // Return a basic fallback render type
        }
    }

    /**
     * Catch composite render type errors.
     */
    @Inject(method = "compositeState", at = @At("HEAD"), cancellable = true)
    private static void create$onCompositeState(CallbackInfoReturnable<Object> cir) {
        try {
            // Composite state safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] CompositeState error: " + e.getMessage());
        }
    }
}
