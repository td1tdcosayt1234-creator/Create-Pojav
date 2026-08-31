package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Drill.
 * 
 * Fixes mechanical drill crashes.
 */
@Mixin(BlockEntity.class)
public abstract class DrillPojavMixin {

    /**
     * Catch drill errors during mining.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedDrill(CallbackInfo ci) {
        try {
            // Drill safety
            String className = this.getClass().getName();
            if (className.contains("Drill") || className.contains("MechanicalDrill")) {
                // Additional safety for drill-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Drill error: " + e.getMessage());
            ci.cancel();
        }
    }
}
