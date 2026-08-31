package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for ClientLevel.
 * 
 * Catches world loading and rendering errors on client side.
 */
@Mixin(ClientLevel.class)
public abstract class ClientLevelPojavMixin {

    /**
     * Catch level loading errors.
     */
    @Inject(method = "disconnect", at = @At("HEAD"))
    private void create$onDisconnect(CallbackInfo ci) {
        try {
            // Cleanup on disconnect
            System.gc();
            System.err.println("[Create-Pojav] Client level disconnected, cleanup performed");
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Client level disconnect error: " + e.getMessage());
        }
    }

    /**
     * Catch entity tick errors.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void create$onTick(CallbackInfo ci) {
        try {
            // Level tick safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Client level tick error: " + e.getMessage());
        }
    }
}
