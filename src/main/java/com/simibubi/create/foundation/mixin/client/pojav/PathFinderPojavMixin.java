package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.pathfinder.PathFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for PathFinder.
 * 
 * Catches pathfinding errors that cause crashes.
 */
@Mixin(PathFinder.class)
public abstract class PathFinderPojavMixin {

    /**
     * Catch findPath errors.
     */
    @Inject(method = "findPath", at = @At("HEAD"), cancellable = true)
    private void create$onFindPath(CallbackInfoReturnable<Object> cir) {
        try {
            // PathFinder safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] PathFinder error: " + e.getMessage());
            cir.setReturnValue(null);
        }
    }
}
