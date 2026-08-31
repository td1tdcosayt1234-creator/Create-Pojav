package com.simibubi.create.foundation.mixin.client.pojav;

import org.lwjgl.opengl.GL11;

/**
 * PojavLauncher Compatibility Configuration.
 * 
 * This class provides configuration options for running Create mod on PojavLauncher.
 * 
 * Features that are automatically disabled on incompatible devices:
 * - Stencil buffer operations (prevents GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT)
 * - Advanced framebuffer effects
 * - Certain shader effects
 * 
 * To force enable/disable, set the system property:
 * -Dcreate.pojavcompat=true  (force enable)
 * -Dcreate.pojavcompat=false (force disable)
 */
public final class PojavCompatConfig {

    public static final String SYSTEM_PROPERTY = "create.pojavcompat";

    private PojavCompatConfig() {}

    /**
     * Check if PojavLauncher compatibility mode is enabled.
     */
    public static boolean isEnabled() {
        String prop = System.getProperty(SYSTEM_PROPERTY);
        if (prop != null) {
            return Boolean.parseBoolean(prop);
        }
        return true; // Default: enabled
    }

    /**
     * Check if a specific feature should be disabled.
     */
    public static boolean isFeatureDisabled(String feature) {
        if (!isEnabled()) return false;
        return PojavCompat.isIncompatibleDevice();
    }
}
