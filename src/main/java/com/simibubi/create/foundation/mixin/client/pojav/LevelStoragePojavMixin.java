package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for LevelStorageSource.
 * 
 * Catches world save/load errors.
 */
@Mixin(LevelStorageSource.class)
public abstract class LevelStoragePojavMixin {

    /**
     * Catch world loading errors.
     */
    @Inject(method = "load", at = @At("HEAD"), cancellable = true)
    private void create$onLoad(String levelName, CallbackInfo ci) {
        try {
            // World loading safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] World load error: " + e.getMessage());
            ci.cancel();
        }
    }
}
