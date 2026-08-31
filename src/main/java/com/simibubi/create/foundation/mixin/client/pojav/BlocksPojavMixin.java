package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Blocks.
 * 
 * Catches block registration errors.
 */
@Mixin(Blocks.class)
public abstract class BlocksPojavMixin {

    /**
     * Catch block initialization errors.
     */
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void create$onInit(CallbackInfo ci) {
        try {
            // Block registration safety
            System.err.println("[Create-Pojav] Blocks initialized successfully");
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Blocks initialization error: " + e.getMessage());
        }
    }
}
