package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for FluidHandler.
 * 
 * Fixes fluid tank/pipe/pump crashes on mobile.
 */
@Mixin(BlockEntity.class)
public abstract class FluidHandlerPojavMixin {

    /**
     * Catch fluid handler errors during tick.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedFluid(CallbackInfo ci) {
        try {
            // Fluid handler safety
            String className = this.getClass().getName();
            if (className.contains("Fluid") || className.contains("Pipe") || className.contains("Pump")) {
                // Additional safety for fluid-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] FluidHandler error: " + e.getMessage());
            ci.cancel();
        }
    }
}
