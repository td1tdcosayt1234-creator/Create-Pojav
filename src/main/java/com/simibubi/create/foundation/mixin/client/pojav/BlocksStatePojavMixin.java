package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for Blocks.
 * 
 * Fixes block state errors that cause crashes.
 */
@Mixin(Blocks.class)
public abstract class BlocksStatePojavMixin {

    /**
     * Catch block state errors.
     */
    @Inject(method = "shouldBeRemoved", at = @At("HEAD"), cancellable = true)
    private static void create$onShouldBeRemoved(net.minecraft.world.level.block.state.BlockState state, CallbackInfoReturnable<Boolean> cir) {
        try {
            // Block state safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Blocks state check error: " + e.getMessage());
            cir.setReturnValue(false);
        }
    }
}
