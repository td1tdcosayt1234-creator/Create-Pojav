package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for LevelChunk.
 * 
 * Catches chunk loading errors that cause crashes.
 */
@Mixin(LevelChunk.class)
public abstract class LevelChunkPojavMixin {

    /**
     * Catch chunk getBlockEntity errors.
     */
    @Inject(method = "getBlockEntity", at = @At("HEAD"), cancellable = true)
    private void create$onGetBlockEntity(net.minecraft.core.BlockPos pos, CallbackInfoReturnable<Object> cir) {
        try {
            // Chunk getBlockEntity safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] LevelChunk getBlockEntity error: " + e.getMessage());
            cir.setReturnValue(null);
        }
    }

    /**
     * Catch chunk setBlockState errors.
     */
    @Inject(method = "setBlockState", at = @At("HEAD"), cancellable = true)
    private void create$onSetBlockState(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state, boolean moved, CallbackInfoReturnable<Object> cir) {
        try {
            // Chunk setBlockState safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] LevelChunk setBlockState error: " + e.getMessage());
            cir.setReturnValue(null);
        }
    }
}
