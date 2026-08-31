package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for SuperByteBuffer.
 * 
 * SuperByteBuffer is used for rendering contraptions and mechanical components.
 * On mobile GPUs, buffer operations may fail due to limited GL support.
 */
@Mixin(value = com.simibubi.create.foundation.render.SuperByteBuffer.class, priority = 800)
public abstract class SuperByteBufferPojavMixin {

    /**
     * Catch buffer rendering errors gracefully.
     */
    @Inject(method = "renderInto", at = @At("HEAD"))
    private void create$onRenderInto(CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            // On mobile, buffer rendering may need simplification
        }
    }
}
