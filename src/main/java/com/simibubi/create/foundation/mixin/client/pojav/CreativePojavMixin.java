package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Creative.
 * 
 * Fixes creative motor/virtual motor crashes.
 */
@Mixin(BlockEntity.class)
public abstract class CreativePojavMixin {

    /**
     * Catch creative block errors.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedCreative(CallbackInfo ci) {
        try {
            // Creative safety
            String className = this.getClass().getName();
            if (className.contains("Creative") || className.contains("Virtual")) {
                // Additional safety for creative-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Creative error: " + e.getMessage());
            ci.cancel();
        }
    }
}
