package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for GL11 operations.
 * 
 * Catches GL_OUT_OF_MEMORY and other GL errors.
 */
@Mixin(GlStateManager.class)
public abstract class GlErrorPojavMixin {

    /**
     * Check GL errors after each operation.
     */
    @Inject(method = "_texParameter", at = @At("RETURN"))
    private static void create$afterTexParameter(CallbackInfo ci) {
        checkGlError("texParameter");
    }

    /**
     * Check GL error helper.
     */
    private static void checkGlError(String operation) {
        try {
            int error = GL11.glGetError();
            if (error != GL11.GL_NO_ERROR) {
                String errorString;
                switch (error) {
                    case GL11.GL_OUT_OF_MEMORY:
                        errorString = "GL_OUT_OF_MEMORY";
                        System.err.println("[Create-Pojav] GPU out of memory during: " + operation);
                        System.gc();
                        break;
                    case GL11.GL_INVALID_ENUM:
                        errorString = "GL_INVALID_ENUM";
                        break;
                    case GL11.GL_INVALID_VALUE:
                        errorString = "GL_INVALID_VALUE";
                        break;
                    case GL11.GL_INVALID_OPERATION:
                        errorString = "GL_INVALID_OPERATION";
                        System.err.println("[Create-Pojav] Invalid GL operation during: " + operation);
                        break;
                    case GL11.GL_INVALID_FRAMEBUFFER_OPERATION:
                        errorString = "GL_INVALID_FRAMEBUFFER_OPERATION";
                        System.err.println("[Create-Pojav] Invalid framebuffer operation: " + operation);
                        break;
                    default:
                        errorString = "Unknown GL error: " + error;
                }
                if (error != GL11.GL_INVALID_ENUM && error != GL11.GL_INVALID_VALUE) {
                    System.err.println("[Create-Pojav] GL Error (" + errorString + ") in: " + operation);
                }
            }
        } catch (Exception e) {
            // Ignore error checking errors
        }
    }
}
