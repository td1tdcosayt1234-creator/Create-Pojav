package com.simibubi.create.foundation.mixin.client.pojav;

/**
 * PojavLauncher Crash Prevention Configuration.
 * 
 * This class manages all compatibility settings for running Create on PojavLauncher.
 * 
 * Features disabled on mobile:
 * 1. Stencil buffer (prevents GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT)
 * 2. Flywheel instanced rendering (not supported on mobile GPUs)
 * 3. Advanced shaders (simplified rendering)
 * 4. Some visual effects (reduced quality)
 * 
 * System properties:
 * -Dcreate.pojavcompat=true/false (enable/disable all fixes)
 * -Dcreate.disableflywheel=true/false (force disable Flywheel)
 * -Dcreate.disablestencil=true/false (force disable stencil)
 * -Dcreate.disableshaders=true/false (force disable custom shaders)
 * -Dcreate.disableponder=true/false (force disable Ponder scenes)
 */
public final class PojavCompatManager {

    public static final String PROP_ENABLE = "create.pojavcompat";
    public static final String PROP_DISABLE_FLYWHEEL = "create.disableflywheel";
    public static final String PROP_DISABLE_STENCIL = "create.disablestencil";
    public static final String PROP_DISABLE_SHADERS = "create.disableshaders";
    public static final String PROP_DISABLE_PONDER = "create.disableponder";
    public static final String PROP_DISABLE_CONTRAPTION = "create.disablecontraption";

    private static Boolean enabled = null;
    private static Boolean disableFlywheel = null;
    private static Boolean disableStencil = null;
    private static Boolean disableShaders = null;
    private static Boolean disablePonder = null;
    private static Boolean disableContraption = null;

    private PojavCompatManager() {}

    /**
     * Check if PojavLauncher compatibility is enabled globally.
     */
    public static boolean isEnabled() {
        if (enabled != null) return enabled;
        String prop = System.getProperty(PROP_ENABLE);
        enabled = prop == null || Boolean.parseBoolean(prop);
        return enabled;
    }

    /**
     * Check if Flywheel should be disabled.
     */
    public static boolean shouldDisableFlywheel() {
        if (disableFlywheel != null) return disableFlywheel;
        String prop = System.getProperty(PROP_DISABLE_FLYWHEEL);
        if (prop != null) {
            disableFlywheel = Boolean.parseBoolean(prop);
        } else {
            disableFlywheel = isEnabled() && PojavCompat.isIncompatibleDevice();
        }
        return disableFlywheel;
    }

    /**
     * Check if stencil buffer should be disabled.
     */
    public static boolean shouldDisableStencil() {
        if (disableStencil != null) return disableStencil;
        String prop = System.getProperty(PROP_DISABLE_STENCIL);
        if (prop != null) {
            disableStencil = Boolean.parseBoolean(prop);
        } else {
            disableStencil = isEnabled() && PojavCompat.isIncompatibleDevice();
        }
        return disableStencil;
    }

    /**
     * Check if custom shaders should be disabled.
     */
    public static boolean shouldDisableShaders() {
        if (disableShaders != null) return disableShaders;
        String prop = System.getProperty(PROP_DISABLE_SHADERS);
        if (prop != null) {
            disableShaders = Boolean.parseBoolean(prop);
        } else {
            disableShaders = isEnabled() && PojavCompat.isIncompatibleDevice();
        }
        return disableShaders;
    }

    /**
     * Check if Ponder scenes should be simplified.
     */
    public static boolean shouldDisablePonder() {
        if (disablePonder != null) return disablePonder;
        String prop = System.getProperty(PROP_DISABLE_PONDER);
        if (prop != null) {
            disablePonder = Boolean.parseBoolean(prop);
        } else {
            disablePonder = isEnabled() && PojavCompat.isIncompatibleDevice();
        }
        return disablePonder;
    }

    /**
     * Check if contraption rendering should be simplified.
     */
    public static boolean shouldDisableContraption() {
        if (disableContraption != null) return disableContraption;
        String prop = System.getProperty(PROP_DISABLE_CONTRAPTION);
        if (prop != null) {
            disableContraption = Boolean.parseBoolean(prop);
        } else {
            disableContraption = isEnabled() && PojavCompat.isIncompatibleDevice();
        }
        return disableContraption;
    }

    /**
     * Reset all cached values (for testing).
     */
    public static void reset() {
        enabled = null;
        disableFlywheel = null;
        disableStencil = null;
        disableShaders = null;
        disablePonder = null;
        disableContraption = null;
    }
}
