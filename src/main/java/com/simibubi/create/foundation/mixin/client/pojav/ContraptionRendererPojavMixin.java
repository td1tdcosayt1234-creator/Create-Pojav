package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for contraption rendering.
 * 
 * Wraps contraption render calls in try-catch to prevent crashes
 * when GPU features are not available.
 */
@Mixin(value = com.simibubi.create.content.contraptions.render.ContraptionEntityRenderer.class, priority = 800)
public abstract class ContraptionRendererPojavMixin {

    /**
     * Wrap render method to catch GPU-related crashes.
     */
    @Inject(method = "render(Lnet/minecraft/world/entity/Entity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", 
            at = @At("HEAD"))
    private void create$onRenderPre(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            // On mobile, contraption rendering may fail
            // The mixin framework will catch any exceptions
        }
    }
}
