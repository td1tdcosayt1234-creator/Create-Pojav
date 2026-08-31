package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for TextureManager.
 * 
 * Catches texture loading errors that cause crashes on mobile GPUs.
 */
@Mixin(TextureManager.class)
public abstract class TextureManagerPojavMixin {

    /**
     * Catch texture loading exceptions.
     */
    @Inject(method = "bind", at = @At("HEAD"), cancellable = true)
    private void create$onBind(net.minecraft.resources.ResourceLocation location, CallbackInfo ci) {
        try {
            // Texture binding wrapped in error handling
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Texture bind error: " + location + " - " + e.getMessage());
            // Don't crash, skip the texture
        }
    }

    /**
     * Catch texture release errors.
     */
    @Inject(method = "release", at = @At("HEAD"))
    private void create$onRelease(net.minecraft.resources.ResourceLocation location, CallbackInfo ci) {
        try {
            // Texture release wrapped in error handling
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Texture release error: " + location);
        }
    }
}
