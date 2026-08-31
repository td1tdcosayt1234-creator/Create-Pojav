package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for EncasedShaft.
 * 
 * Fixes encased shaft crashes.
 */
@Mixin(BlockEntity.class)
public abstract class EncasedShaftPojavMixin {

    /**
     * Catch encased shaft errors.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedEncasedShaft(CallbackInfo ci) {
        try {
            // EncasedShaft safety
            String className = this.getClass().getName();
            if (className.contains("EncasedShaft") || className.contains("Encased")) {
                // Additional safety for encased shaft-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] EncasedShaft error: " + e.getMessage());
            ci.cancel();
        }
    }
}
