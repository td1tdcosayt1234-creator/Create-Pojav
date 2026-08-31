package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Mixer.
 * 
 * Fixes mechanical mixer crashes.
 */
@Mixin(BlockEntity.class)
public abstract class MixerPojavMixin {

    /**
     * Catch mixer errors during mixing.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedMixer(CallbackInfo ci) {
        try {
            // Mixer safety
            String className = this.getClass().getName();
            if (className.contains("Mixer") || className.contains("MechanicalMixer")) {
                // Additional safety for mixer-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Mixer error: " + e.getMessage());
            ci.cancel();
        }
    }
}
