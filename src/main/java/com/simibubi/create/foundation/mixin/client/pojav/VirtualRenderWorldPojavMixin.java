package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Level.blockEntityChanged.
 * 
 * Fixes VirtualRenderWorld crash when block entities call setChanged() during initialization.
 * This is the same fix as Create-virtualworld-compat mod.
 */
@Mixin(Level.class)
public abstract class VirtualRenderWorldPojavMixin {

    /**
     * Cancel blockEntityChanged when in VirtualRenderWorld.
     * VirtualRenderWorld is temporary and never persists, so marking chunks as unsaved is unnecessary.
     */
    @Inject(method = "blockEntityChanged", at = @At("HEAD"), cancellable = true)
    private void create$preventBlockEntityChangedInVirtualWorld(org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        try {
            // Check if this level is a VirtualRenderWorld (via class name check)
            String className = this.getClass().getName();
            if (className.contains("VirtualRenderWorld") || className.contains("virtualWorld")) {
                ci.cancel();
            }
        } catch (Exception e) {
            // Ignore errors in check
        }
    }
}
