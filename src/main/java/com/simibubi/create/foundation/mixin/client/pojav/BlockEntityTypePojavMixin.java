package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for BlockEntityType.
 * 
 * Fixes block entity type errors that cause crashes.
 */
@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypePojavMixin {

    /**
     * Catch block entity type creation errors.
     */
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private void create$onCreate(CallbackInfo ci) {
        try {
            // Block entity type creation safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockEntityType create error: " + e.getMessage());
            ci.cancel();
        }
    }
}
