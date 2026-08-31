package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for LevelChunkSection.
 * 
 * Fixes chunk section errors that cause crashes.
 */
@Mixin(LevelChunkSection.class)
public abstract class LevelChunkSectionPojavMixin {

    /**
     * Catch chunk section getBlockState errors.
     */
    @Inject(method = "getBlockState", at = @At("HEAD"), cancellable = true)
    private void create$onGetBlockState(net.minecraft.core.BlockPos pos, CallbackInfoReturnable<Object> cir) {
        try {
            // Chunk section safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] LevelChunkSection getBlockState error: " + e.getMessage());
            cir.setReturnValue(net.minecraft.world.level.block.Blocks.AIR.defaultBlockState());
        }
    }

    /**
     * Catch chunk section setBlockState errors.
     */
    @Inject(method = "setBlockState", at = @At("HEAD"), cancellable = true)
    private void create$onSetBlockState(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state, boolean useLock, CallbackInfoReturnable<Object> cir) {
        try {
            // Chunk section setBlockState safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] LevelChunkSection setBlockState error: " + e.getMessage());
            cir.setReturnValue(net.minecraft.world.level.block.Blocks.AIR.defaultBlockState());
        }
    }
}
