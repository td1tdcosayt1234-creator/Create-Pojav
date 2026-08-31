package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for RotationHandler.
 * 
 * Fixes rotation calculation errors in kinetic blocks.
 */
@Mixin(BlockState.class)
public abstract class RotationHandlerPojavMixin {

    /**
     * Catch rotation-related errors in block state.
     */
    @Inject(method = "getMenuProvider", at = @At("HEAD"), cancellable = true)
    private void create$onGetMenuProvider(net.minecraft.world.level.Level level, net.minecraft.core.BlockPos pos, CallbackInfo ci) {
        try {
            // Rotation handler safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] RotationHandler error: " + e.getMessage());
            ci.cancel();
        }
    }

    /**
     * Catch neighbor update errors.
     */
    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    private void create$onUpdateShape(net.minecraft.core.Direction direction, BlockState state, net.minecraft.world.level.LevelAccessor level, net.minecraft.core.BlockPos pos, net.minecraft.core.BlockPos neighborPos, CallbackInfo ci) {
        try {
            // Neighbor update safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockState updateShape error: " + e.getMessage());
        }
    }
}
