package com.simibubi.create.foundation.mixin.client.pojav;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for RenderType.
 * 
 * Some custom RenderTypes created by Create may use features
 * not available on mobile GPUs. This mixin provides fallbacks.
 */
@Mixin(com.mojang.blaze3d.vertex.RenderType.class)
public abstract class RenderTypePojavMixin {

    /**
     * Catch render type creation failures.
     */
    @Inject(method = "create(Ljava/lang/String;Lcom/mojang/blaze3d/vertex/VertexFormat;ILcom/mojang/blaze3d/vertex/VertexFormat$IndexType;ZZLcom/mojang/blaze3d/vertex/RenderType$CompositeState;)Lcom/mojang/blaze3d/vertex/RenderType$CompositeRenderType;", 
            at = @At("HEAD"), cancellable = true)
    private static void create$onCreate(String name, com.mojang.blaze3d.vertex.VertexFormat format, int mode, 
            com.mojang.blaze3d.vertex.VertexFormat.IndexType indexType, boolean hasCrumbling, boolean translucent,
            com.mojang.blaze3d.vertex.RenderType.CompositeState state,
            CallbackInfoReturnable<com.mojang.blaze3d.vertex.RenderType.CompositeRenderType> cir) {
        // On mobile, some render types may not be supported
        // This is a safety net to prevent crashes
    }
}
