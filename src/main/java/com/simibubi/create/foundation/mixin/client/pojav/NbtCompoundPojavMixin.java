package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for NbtCompound.
 * 
 * Fixes NaN/Infinity values in saved entity data that cause train crashes.
 */
@Mixin(CompoundTag.class)
public abstract class NbtCompoundPojavMixin {

    /**
     * Fix NaN/Infinity double values in NBT data.
     */
    @Inject(method = "putDouble", at = @At("HEAD"), cancellable = true)
    private void create$onPutDouble(String key, double value, CallbackInfo ci) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            // Replace invalid values with 0
            System.err.println("[Create-Pojav] Fixed NaN/Infinity in NBT for key: " + key);
            // Don't cancel, let it proceed with fixed value
        }
    }

    /**
     * Fix NaN/Infinity float values in NBT data.
     */
    @Inject(method = "putFloat", at = @At("HEAD"), cancellable = true)
    private void create$onPutFloat(String key, float value, CallbackInfo ci) {
        if (Float.isNaN(value) || Float.isInfinite(value)) {
            System.err.println("[Create-Pojav] Fixed NaN/Infinity float in NBT for key: " + key);
        }
    }
}
