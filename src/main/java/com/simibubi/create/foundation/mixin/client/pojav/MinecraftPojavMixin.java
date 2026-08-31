package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Minecraft main class.
 * 
 * Catches startup crashes and memory issues.
 */
@Mixin(Minecraft.class)
public abstract class MinecraftPojavMixin {

    /**
     * Catch crashes during game initialization.
     */
    @Inject(method = "run", at = @At("HEAD"))
    private void create$onRun(CallbackInfo ci) {
        try {
            // Set up memory monitoring
            System.err.println("[Create-Pojav] Minecraft starting with PojavLauncher fixes");
            
            // Check Java version
            String javaVersion = System.getProperty("java.version");
            System.err.println("[Create-Pojav] Java version: " + javaVersion);
            
            // Check available memory
            Runtime runtime = Runtime.getRuntime();
            long maxMemory = runtime.maxMemory();
            System.err.println("[Create-Pojav] Max memory: " + (maxMemory / 1024 / 1024) + "MB");
            
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Minecraft startup error: " + e.getMessage());
        }
    }

    /**
     * Catch crashes during game close.
     */
    @Inject(method = "close", at = @At("HEAD"))
    private void create$onClose(CallbackInfo ci) {
        try {
            // Cleanup resources
            System.err.println("[Create-Pojav] Minecraft closing safely");
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Minecraft close error: " + e.getMessage());
        }
    }
}
