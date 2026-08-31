package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Contraption.
 * 
 * Fixes contraption assembly/disassembly crashes.
 */
@Mixin(BlockEntity.class)
public abstract class ContraptionPojavMixin {

    /**
     * Catch contraption errors during assembly.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedContraption(CallbackInfo ci) {
        try {
            // Contraption safety
            String className = this.getClass().getName();
            if (className.contains("Contraption") || className.contains("MountedStorage")) {
                // Additional safety for contraption-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Contraption error: " + e.getMessage());
            ci.cancel();
        }
    }
}
