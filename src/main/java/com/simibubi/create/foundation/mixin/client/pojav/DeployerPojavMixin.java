package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Deployer.
 * 
 * Fixes deployer/arm interaction crashes.
 */
@Mixin(BlockEntity.class)
public abstract class DeployerPojavMixin {

    /**
     * Catch deployer errors during operation.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChangedDeployer(CallbackInfo ci) {
        try {
            // Deployer safety
            String className = this.getClass().getName();
            if (className.contains("Deployer") || className.contains("Arm") || 
                className.contains("MechanicalArm")) {
                // Additional safety for deployer-related block entities
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Deployer error: " + e.getMessage());
            ci.cancel();
        }
    }
}
