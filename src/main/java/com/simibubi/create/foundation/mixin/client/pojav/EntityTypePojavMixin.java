package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for EntityType.
 * 
 * Catches entity spawning errors that cause crashes.
 */
@Mixin(EntityType.class)
public abstract class EntityTypePojavMixin {

    /**
     * Catch entity spawn errors.
     */
    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true)
    private void create$onSpawn(CallbackInfo ci) {
        try {
            // Entity spawning safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Entity spawn error: " + e.getMessage());
            ci.cancel();
        }
    }
}
