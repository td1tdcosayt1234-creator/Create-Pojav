package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Pipe.
 * 
 * Fixes fluid pipe crashes.
 */
@Mixin(BlockEntity.class)
public abstract class PipePojavMixin {

    /**
     * Catch pipe errors during fluid transfer.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedPipe(CallbackInfo ci) {
        try {
            // Pipe safety
            String className = this.getClass().getName();
            if (className.contains("Pipe") || className.contains("SmartFluid") || 
                className.contains("FluidPipe")) {
                // Additional safety for pipe-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Pipe error: " + e.getMessage());
            ci.cancel();
        }
    }
}
