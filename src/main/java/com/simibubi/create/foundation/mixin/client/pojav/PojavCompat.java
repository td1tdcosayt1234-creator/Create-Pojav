package com.simibubi.create.foundation.mixin.client.pojav;

import org.lwjgl.opengl.GL11;

/**
 * Utility class to detect PojavLauncher and incompatible mobile GPU environments.
 * Used by various mixins to conditionally disable features that cause crashes.
 */
public final class PojavCompat {

    private static Boolean cachedResult = null;

    private PojavCompat() {}

    /**
     * Detects if the game is running on PojavLauncher or a similar mobile translation layer.
     * Caches the result after first check.
     */
    public static boolean isIncompatibleDevice() {
        if (cachedResult != null) return cachedResult;
        
        try {
            String vendor = GL11.glGetString(GL11.GL_VENDOR);
            String renderer = GL11.glGetString(GL11.GL_RENDERER);
            
            if (vendor == null || renderer == null) {
                cachedResult = false;
                return false;
            }
            
            String v = vendor.toLowerCase();
            String r = renderer.toLowerCase();
            
            cachedResult = r.contains("gl4es") 
                || r.contains("virgl")
                || r.contains("angle") 
                || r.contains("zink")
                || r.contains("mali")
                || r.contains("adreno")
                || r.contains("powervr")
                || r.contains("swiftshader")
                || v.contains("android");
                
        } catch (Exception e) {
            cachedResult = false;
        }
        
        return cachedResult;
    }

    /**
     * Returns true if stencil buffer operations should be skipped.
     */
    public static boolean shouldSkipStencil() {
        return isIncompatibleDevice();
    }

    /**
     * Returns true if Flywheel instanced rendering should be disabled.
     * Flywheel uses advanced GL features not available on all mobile GPUs.
     */
    public static boolean shouldDisableFlywheel() {
        return isIncompatibleDevice();
    }
}
