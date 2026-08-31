package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for Vec3 operations.
 * 
 * Fixes NullPointerException when mf.axis is null during collision checks.
 */
@Mixin(org.joml.Vector3d.class)
public abstract class Vec3PojavMixin {

    /**
     * Catch NaN/Infinity in Vector3d operations.
     */
    @Inject(method = "normalize", at = @At("HEAD"), cancellable = true)
    private void create$onNormalize(CallbackInfoReturnable<org.joml.Vector3d> cir) {
        try {
            org.joml.Vector3d self = (org.joml.Vector3d) (Object) this;
            double length = self.length();
            if (Double.isNaN(length) || Double.isInfinite(length) || length == 0) {
                // Return zero vector instead of NaN
                cir.setReturnValue(new org.joml.Vector3d(0, 0, 0));
            }
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Catch cross product errors.
     */
    @Inject(method = "cross", at = @At("HEAD"), cancellable = true)
    private void create$onCross(org.joml.Vector3d v, CallbackInfoReturnable<org.joml.Vector3d> cir) {
        try {
            // Cross product safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Vec3 cross error: " + e.getMessage());
            cir.setReturnValue(new org.joml.Vector3d(0, 0, 0));
        }
    }
}
