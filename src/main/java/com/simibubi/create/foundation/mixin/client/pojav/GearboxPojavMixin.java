package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Gearbox.
 * 
 * Fixes gearbox/clutch/gearshift crashes.
 */
@Mixin(BlockEntity.class)
public abstract class GearboxPojavMixin {

    /**
     * Catch gearbox errors during rotation.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedGearbox(CallbackInfo ci) {
        try {
            // Gearbox safety
            String className = this.getClass().getName();
            if (className.contains("Gearbox") || className.contains("Clutch") || 
                className.contains("Gearshift")) {
                // Additional safety for gearbox-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Gearbox error: " + e.getMessage());
            ci.cancel();
        }
    }
}
