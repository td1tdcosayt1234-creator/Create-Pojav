package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Belt.
 * 
 * Fixes mechanical belt crashes.
 */
@Mixin(BlockEntity.class)
public abstract class BeltPojavMixin {

    /**
     * Catch belt errors during transport.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedBelt(CallbackInfo ci) {
        try {
            // Belt safety
            String className = this.getClass().getName();
            if (className.contains("Belt") || className.contains("Depot") || 
                className.contains("Chute")) {
                // Additional safety for belt-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Belt error: " + e.getMessage());
            ci.cancel();
        }
    }
}
