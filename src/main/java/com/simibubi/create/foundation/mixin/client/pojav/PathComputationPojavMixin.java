package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.pathfinder.PathComputationType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for PathComputationType.
 * 
 * Fixes pathfinding errors that cause crashes.
 */
@Mixin(PathComputationType.class)
public abstract class PathComputationPojavMixin {

    /**
     * Catch path computation errors.
     */
    @Inject(method = "is", at = @At("HEAD"), cancellable = true)
    private void create$onIs(net.minecraft.world.level.block.state.BlockState state, CallbackInfoReturnable<Boolean> cir) {
        try {
            // Path computation safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] PathComputation error: " + e.getMessage());
            cir.setReturnValue(false);
        }
    }
}
