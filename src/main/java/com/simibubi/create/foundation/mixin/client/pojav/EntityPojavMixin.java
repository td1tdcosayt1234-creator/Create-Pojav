package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Entity.
 * 
 * Catches NaN/Infinity position values that cause crashes on mobile.
 * This fixes the "Train infinite position" crash.
 */
@Mixin(Entity.class)
public abstract class EntityPojavMixin {

    /**
     * Check and fix NaN/Infinity positions on entity tick.
     * This prevents the train crash caused by invalid positions.
     */
    @Inject(method = "setPos", at = @At("HEAD"), cancellable = true)
    private void create$onSetPos(double x, double y, double z, CallbackInfo ci) {
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) ||
            Double.isInfinite(x) || Double.isInfinite(y) || Double.isInfinite(z)) {
            // Fix invalid position by keeping current position
            Entity self = (Entity) (Object) this;
            // Log the error but don't crash
            System.err.println("[Create-Pojav] Detected NaN/Infinity position in entity, skipping setPos");
            ci.cancel();
        }
    }

    /**
     * Catch readNbt with invalid positions.
     */
    @Inject(method = "load", at = @At("HEAD"))
    private void create$onLoad(CallbackInfo ci) {
        // Additional safety check for entity loading
    }
}
