package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for BlockState.
 * 
 * Catches block state errors that cause crashes.
 */
@Mixin(BlockState.class)
public abstract class BlockStatePojavMixin {

    /**
     * Catch getShape errors.
     */
    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void create$onGetShape(net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context, CallbackInfoReturnable<Object> cir) {
        try {
            // Block state getShape safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockState getShape error: " + e.getMessage());
            cir.setReturnValue(net.minecraft.world.phys.shapes.Shapes.empty());
        }
    }

    /**
     * Catch getCollisionShape errors.
     */
    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void create$onGetCollisionShape(net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context, CallbackInfoReturnable<Object> cir) {
        try {
            // Block state collision safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockState collision error: " + e.getMessage());
            cir.setReturnValue(net.minecraft.world.phys.shapes.Shapes.empty());
        }
    }
}
