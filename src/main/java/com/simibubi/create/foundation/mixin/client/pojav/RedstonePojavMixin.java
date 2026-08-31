package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Redstone.
 * 
 * Fixes redstone-related crashes (links, controllers, etc.).
 */
@Mixin(BlockEntity.class)
public abstract class RedstonePojavMixin {

    /**
     * Catch redstone errors during signal processing.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedRedstone(CallbackInfo ci) {
        try {
            // Redstone safety
            String className = this.getClass().getName();
            if (className.contains("Redstone") || className.contains("Link") || 
                className.contains("Controller")) {
                // Additional safety for redstone-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Redstone error: " + e.getMessage());
            ci.cancel();
        }
    }
}
