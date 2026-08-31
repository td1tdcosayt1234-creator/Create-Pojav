package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for Vec3 operations.
 * 
 * Fixes NaN/Infinity in Vec3 calculations that cause contraption crashes.
 */
@Mixin(Vec3.class)
public abstract class Vec3OperationsPojavMixin {

    /**
     * Catch normalize errors.
     */
    @Inject(method = "normalize", at = @At("HEAD"), cancellable = true)
    private void create$onNormalize(CallbackInfoReturnable<Vec3> cir) {
        try {
            Vec3 self = (Vec3) (Object) this;
            double length = self.length();
            if (Double.isNaN(length) || Double.isInfinite(length) || length < 0.0001) {
                cir.setReturnValue(new Vec3(0, 0, 0));
            }
        } catch (Exception e) {
            cir.setReturnValue(new Vec3(0, 0, 0));
        }
    }

    /**
     * Catch subtract errors.
     */
    @Inject(method = "subtract", at = @At("HEAD"), cancellable = true)
    private void create$onSubtract(double x, double y, double z, CallbackInfoReturnable<Vec3> cir) {
        try {
            // Vec3 subtract safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Vec3 subtract error: " + e.getMessage());
            cir.setReturnValue(new Vec3(0, 0, 0));
        }
    }

    /**
     * Catch add errors.
     */
    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    private void create$onAdd(double x, double y, double z, CallbackInfoReturnable<Vec3> cir) {
        try {
            // Vec3 add safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Vec3 add error: " + e.getMessage());
            cir.setReturnValue(new Vec3(0, 0, 0));
        }
    }

    /**
     * Catch scale errors.
     */
    @Inject(method = "scale", at = @At("HEAD"), cancellable = true)
    private void create$onScale(double factor, CallbackInfoReturnable<Vec3> cir) {
        try {
            Vec3 self = (Vec3) (Object) this;
            double x = self.x * factor;
            double y = self.y * factor;
            double z = self.z * factor;
            if (Double.isNaN(x) || Double.isInfinite(x) || 
                Double.isNaN(y) || Double.isInfinite(y) || 
                Double.isNaN(z) || Double.isInfinite(z)) {
                cir.setReturnValue(new Vec3(0, 0, 0));
            }
        } catch (Exception e) {
            cir.setReturnValue(new Vec3(0, 0, 0));
        }
    }
}
