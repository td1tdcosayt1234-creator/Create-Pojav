package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.OutputFrameException;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin for Framebuffer.
 * Fixes GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT errors on mobile GPUs.
 */
@Mixin(value = net.minecraft.client.renderer.PostChain.class, priority = 900)
public class PostChainMixin {

    /**
     * Detect if running on PojavLauncher (mobile OpenGL ES via translation layer)
     */
    private static boolean isPojavLauncher() {
        try {
            String vendor = GL11.glGetString(GL11.GL_VENDOR);
            String renderer = GL11.glGetString(GL11.GL_RENDERER);
            if (vendor == null || renderer == null) return false;
            
            // PojavLauncher uses GL4ES which has specific renderer strings
            String lowerVendor = vendor.toLowerCase();
            String lowerRenderer = renderer.toLowerCase();
            
            return lowerRenderer.contains("gl4es") 
                || lowerRenderer.contains("virgl")
                || lowerRenderer.contains("angle")
                || lowerRenderer.contains("zink")
                || lowerRenderer.contains("swiftshader")
                || lowerVendor.contains("android")
                || lowerRenderer.contains("mali")
                || lowerRenderer.contains("adreno")
                || lowerRenderer.contains("powervr");
        } catch (Exception e) {
            return false;
        }
    }
}
