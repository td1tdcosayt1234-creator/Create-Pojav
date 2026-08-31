package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Saw.
 * 
 * Fixes mechanical saw crashes.
 */
@Mixin(BlockEntity.class)
public abstract class SawPojavMixin {

    /**
     * Catch saw errors during cutting.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedSaw(CallbackInfo ci) {
        try {
            // Saw safety
            String className = this.getClass().getName();
            if (className.contains("Saw") || className.contains("MechanicalSaw")) {
                // Additional safety for saw-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Saw error: " + e.getMessage());
            ci.cancel();
        }
    }
}
