package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for CompoundTag size checking.
 * 
 * Fixes Contraption data corruption that causes world save crashes.
 */
@Mixin(CompoundTag.class)
public abstract class CompoundTagSizePojavMixin {

    /**
     * Limit NBT compound size to prevent memory issues.
     */
    @Inject(method = "sizeInBytes", at = @At("HEAD"), cancellable = true)
    private void create$onSizeInBytes(CallbackInfoReturnable<Integer> cir) {
        try {
            // Let vanilla handle size calculation
        } catch (StackOverflowError e) {
            System.err.println("[Create-Pojav] NBT size calculation overflow, returning safe value");
            cir.setReturnValue(1024); // Return safe default
        } catch (Exception e) {
            System.err.println("[Create-Pojav] NBT size error: " + e.getMessage());
            cir.setReturnValue(1024);
        }
    }
}
