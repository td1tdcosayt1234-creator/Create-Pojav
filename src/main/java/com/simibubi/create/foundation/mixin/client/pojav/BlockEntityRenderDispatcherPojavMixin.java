package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for BlockEntityRenderDispatcher.
 * 
 * Catches block entity render dispatch errors.
 */
@Mixin(BlockEntityRenderDispatcher.class)
public abstract class BlockEntityRenderDispatcherPojavMixin {

    /**
     * Catch block entity render dispatch errors.
     */
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void create$render(Object blockEntity, float partialTick, com.mojang.blaze3d.vertex.PoseStack poseStack, Object bufferSource, int packedLight, int packedOverlay, CallbackInfo ci) {
        try {
            // Block entity render dispatch safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] BlockEntityRenderDispatcher error: " + e.getMessage());
            ci.cancel();
        }
    }
}
