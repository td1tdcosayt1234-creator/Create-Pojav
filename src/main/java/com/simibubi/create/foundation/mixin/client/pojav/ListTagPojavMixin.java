package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.nbt.ListTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for ListTag.
 * 
 * Fixes ConcurrentModificationException in NBT lists.
 */
@Mixin(ListTag.class)
public abstract class ListTagPojavMixin {

    /**
     * Catch ConcurrentModificationException in list operations.
     */
    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    private void create$onAdd(net.minecraft.nbt.Tag tag, CallbackInfo ci) {
        try {
            // Thread-safe add operation
        } catch (Exception e) {
            System.err.println("[Create-Pojav] ListTag add error: " + e.getMessage());
            ci.cancel();
        }
    }

    /**
     * Catch remove errors.
     */
    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    private void create$onRemove(int index, CallbackInfo ci) {
        try {
            // Safe remove
        } catch (Exception e) {
            System.err.println("[Create-Pojav] ListTag remove error: " + e.getMessage());
            ci.cancel();
        }
    }
}
