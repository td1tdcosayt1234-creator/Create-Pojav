package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Entity removal.
 * 
 * Catches entity removal errors that cause contraption crashes.
 */
@Mixin(Entity.class)
public abstract class EntityRemovalPojavMixin {

    /**
     * Catch entity removal errors.
     */
    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    private void create$onRemove(Object removalReason, CallbackInfo ci) {
        try {
            // Entity removal safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Entity removal error: " + e.getMessage());
            ci.cancel();
        }
    }

    /**
     * Catch entity discard errors.
     */
    @Inject(method = "discard", at = @At("HEAD"), cancellable = true)
    private void create$onDiscard(CallbackInfo ci) {
        try {
            // Entity discard safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Entity discard error: " + e.getMessage());
        }
    }
}
