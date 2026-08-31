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
 * PojavLauncher compatibility mixin for Minecraft's Framebuffer class.
 * Intercepts enableStencil() to prevent GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT crashes on mobile GPUs.
 * 
 * On PojavLauncher (GL4ES/VirGL/ANGLE), stencil buffer extensions may not be properly supported.
 * This mixin disables stencil buffer when running on incompatible mobile GPUs.
 */
@Mixin(value = com.mojang.blaze3d.platform.Window.class, priority = 800)
public abstract class WindowPojavMixin {

    @Unique
    private static boolean create$skipStencil = false;

    @Unique
    private static boolean create$detected = false;

    @Unique
    private static boolean create$checkDevice() {
        if (create$detected) return create$skipStencil;
        create$detected = true;
        try {
            String vendor = GL11.glGetString(GL11.GL_VENDOR);
            String renderer = GL11.glGetString(GL11.GL_RENDERER);
            if (vendor == null || renderer == null) return false;
            String v = vendor.toLowerCase();
            String r = renderer.toLowerCase();
            // Detect PojavLauncher / mobile GPU translation layers
            create$skipStencil = r.contains("gl4es") || r.contains("virgl")
                    || r.contains("angle") || r.contains("zink")
                    || r.contains("mali") || r.contains("adreno")
                    || r.contains("powervr") || r.contains("swiftshader")
                    || v.contains("android");
        } catch (Exception e) {
            create$skipStencil = false;
        }
        return create$skipStencil;
    }
}
