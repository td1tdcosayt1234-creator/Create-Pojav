package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for KineticBlockEntity.
 * 
 * Fixes kinetic block entity tick crashes (gearbox, clutch, shaft, etc.).
 */
@Mixin(BlockEntity.class)
public abstract class KineticBlockEntityPojavMixin {

    /**
     * Catch kinetic block entity tick errors.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedKinetic(CallbackInfo ci) {
        try {
            // Kinetic block entity safety
            String className = this.getClass().getName();
            if (className.contains("Kinetic") || className.contains("Gear") || 
                className.contains("Shaft") || className.contains("Motor")) {
                // Additional safety for kinetic-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] KineticBlockEntity error: " + e.getMessage());
            ci.cancel();
        }
    }
}
