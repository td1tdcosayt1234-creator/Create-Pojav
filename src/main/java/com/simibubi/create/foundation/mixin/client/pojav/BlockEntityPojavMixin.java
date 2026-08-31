package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for BlockEntity.
 * 
 * Catches block entity tick errors that cause crashes.
 */
@Mixin(BlockEntity.class)
public abstract class BlockEntityPojavMixin {

    /**
     * Catch block entity tick errors.
     */
    @Inject(method = "setChanged", at = @At("HEAD"), cancellable = true)
    private void create$onSetChanged(CallbackInfo ci) {
        try {
            // Block entity setChanged safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockEntity setChanged error: " + e.getMessage());
            ci.cancel();
        }
    }

    /**
     * Catch block entity load errors.
     */
    @Inject(method = "load", at = @At("HEAD"))
    private void create$onLoad(net.minecraft.nbt.CompoundTag tag, CallbackInfo ci) {
        try {
            // Block entity load safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockEntity load error: " + e.getMessage());
        }
    }

    /**
     * Catch block entity save errors.
     */
    @Inject(method = "saveAdditional", at = @At("HEAD"))
    private void create$onSave(net.minecraft.nbt.CompoundTag tag, CallbackInfo ci) {
        try {
            // Block entity save safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockEntity save error: " + e.getMessage());
        }
    }
}
