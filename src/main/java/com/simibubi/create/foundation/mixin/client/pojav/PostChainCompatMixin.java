package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Minecraft's PostChain (Shader Effect).
 * Catches GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT errors and degrades gracefully.
 */
@Mixin(value = com.mojang.blaze3d.pipeline.PostChain.class, priority = 800)
public abstract class PostChainCompatMixin {

    @Inject(method = "process", at = @At("HEAD"), cancellable = true)
    private void create$onProcess(float partialTick, CallbackInfo ci) {
        if (PojavCompat.isIncompatibleDevice()) {
            // Skip post-processing on incompatible devices to prevent crash
            ci.cancel();
        }
    }
}
