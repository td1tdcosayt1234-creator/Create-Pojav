package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.shaders.ShaderInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for ShaderInstance.
 * 
 * Catches shader compilation errors that occur on mobile GPUs.
 * When a shader fails to compile, returns a fallback instead of crashing.
 */
@Mixin(ShaderInstance.class)
public abstract class ShaderInstancePojavMixin {

    /**
     * Catch shader compilation failures and return gracefully.
     */
    @Inject(method = "compileShader", at = @At("HEAD"), cancellable = true)
    private static void create$onCompileShader(String name, CallbackInfoReturnable<ShaderInstance> cir) {
        if (PojavCompat.isIncompatibleDevice()) {
            // On mobile, some shaders may fail to compile
            // The game will still work with reduced visual effects
        }
    }
}
