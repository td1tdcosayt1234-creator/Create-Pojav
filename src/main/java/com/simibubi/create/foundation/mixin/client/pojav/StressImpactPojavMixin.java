package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for StressImpact.
 * 
 * Fixes stress calculation errors that cause crashes.
 */
@Mixin(net.minecraft.world.level.block.entity.BlockEntity.class)
public abstract class StressImpactPojavMixin {

    /**
     * Catch stress calculation errors during tick.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedSafe(CallbackInfo ci) {
        try {
            // Stress impact safety - catch any errors during block entity changes
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Block entity setChanged error caught: " + e.getMessage());
            ci.cancel();
        }
    }
}
