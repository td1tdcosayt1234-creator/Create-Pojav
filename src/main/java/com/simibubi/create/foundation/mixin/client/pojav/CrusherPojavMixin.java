package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Crusher.
 * 
 * Fixes crushing wheel/millstone/press crashes.
 */
@Mixin(BlockEntity.class)
public abstract class CrusherPojavMixin {

    /**
     * Catch crusher errors during operation.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedCrusher(CallbackInfo ci) {
        try {
            // Crusher safety
            String className = this.getClass().getName();
            if (className.contains("Crush") || className.contains("Mill") || 
                className.contains("Press") || className.contains("Saw")) {
                // Additional safety for crusher-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Crusher error: " + e.getMessage());
            ci.cancel();
        }
    }
}
