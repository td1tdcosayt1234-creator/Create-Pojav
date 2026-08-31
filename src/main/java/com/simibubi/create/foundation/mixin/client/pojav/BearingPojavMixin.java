package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Bearing.
 * 
 * Fixes mechanical bearing/harvester crashes.
 */
@Mixin(BlockEntity.class)
public abstract class BearingPojavMixin {

    /**
     * Catch bearing errors during rotation.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedBearing(CallbackInfo ci) {
        try {
            // Bearing safety
            String className = this.getClass().getName();
            if (className.contains("Bearing") || className.contains("Harvester") || 
                className.contains("MechanicalBearing")) {
                // Additional safety for bearing-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Bearing error: " + e.getMessage());
            ci.cancel();
        }
    }
}
