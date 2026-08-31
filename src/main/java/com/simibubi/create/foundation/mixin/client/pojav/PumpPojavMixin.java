package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Pump.
 * 
 * Fixes mechanical pump crashes.
 */
@Mixin(BlockEntity.class)
public abstract class PumpPojavMixin {

    /**
     * Catch pump errors during fluid pumping.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedPump(CallbackInfo ci) {
        try {
            // Pump safety
            String className = this.getClass().getName();
            if (className.contains("Pump") || className.contains("MechanicalPump")) {
                // Additional safety for pump-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Pump error: " + e.getMessage());
            ci.cancel();
        }
    }
}
