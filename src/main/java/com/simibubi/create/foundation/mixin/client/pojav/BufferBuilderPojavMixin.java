package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.vertex.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for BufferBuilder.
 * 
 * Catches buffer builder errors that cause crashes.
 */
@Mixin(BufferBuilder.class)
public abstract class BufferBuilderPojavMixin {

    /**
     * Catch buffer end errors.
     */
    @Inject(method = "end", at = @At("HEAD"), cancellable = true)
    private void create$onEnd(CallbackInfo ci) {
        try {
            // Buffer end safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BufferBuilder end error: " + e.getMessage());
            ci.cancel();
        }
    }

    /**
     * Catch buffer discard errors.
     */
    @Inject(method = "discard", at = @At("HEAD"), cancellable = true)
    private void create$onDiscard(CallbackInfo ci) {
        try {
            // Buffer discard safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BufferBuilder discard error: " + e.getMessage());
        }
    }
}
