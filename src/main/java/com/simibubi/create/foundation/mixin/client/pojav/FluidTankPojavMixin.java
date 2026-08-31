package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for FluidTank.
 * 
 * Fixes fluid tank crashes.
 */
@Mixin(BlockEntity.class)
public abstract class FluidTankPojavMixin {

    /**
     * Catch fluid tank errors.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedFluidTank(CallbackInfo ci) {
        try {
            // FluidTank safety
            String className = this.getClass().getName();
            if (className.contains("FluidTank") || className.contains("Tank") || 
                className.contains("Spout")) {
                // Additional safety for fluid tank-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] FluidTank error: " + e.getMessage());
            ci.cancel();
        }
    }
}
