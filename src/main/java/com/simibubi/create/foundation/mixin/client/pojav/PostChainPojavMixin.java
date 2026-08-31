package com.simibubi.create.foundation.mixin.client.pojav;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.PostChain;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * PojavLauncher compatibility mixin.
 * Intercepts framebuffer operations to prevent GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT crashes.
 */
@Mixin(PostChain.class)
public abstract class PostChainPojavMixin {

    @Unique
    private static boolean create$pojavDetected = false;

    @Unique
    private static boolean create$pojavCheckDone = false;

    @Unique
    private static boolean create$isPojavDevice() {
        if (create$pojavCheckDone) {
            return create$pojavDetected;
        }
        create$pojavCheckDone = true;
        try {
            String renderer = GL11.glGetString(GL11.GL_RENDERER);
            if (renderer == null) return false;
            String r = renderer.toLowerCase();
            create$pojavDetected = r.contains("gl4es") || r.contains("virgl") 
                    || r.contains("angle") || r.contains("zink")
                    || r.contains("mali") || r.contains("adreno")
                    || r.contains("powervr") || r.contains("swiftshader");
        } catch (Exception e) {
            create$pojavDetected = false;
        }
        return create$pojavDetected;
    }
}
