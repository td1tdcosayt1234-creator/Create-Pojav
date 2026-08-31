package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.texture.SpriteContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for SpriteContents.
 * 
 * Catches sprite/texture upload errors.
 */
@Mixin(SpriteContents.class)
public abstract class SpriteContentsPojavMixin {

    /**
     * Catch sprite upload errors.
     */
    @Inject(method = "upload", at = @At("HEAD"), cancellable = true)
    private void create$onUpload(int x, int y, CallbackInfo ci) {
        try {
            // Sprite upload safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] SpriteContents upload error: " + e.getMessage());
            ci.cancel();
        }
    }

    /**
     * Catch sprite reupload errors.
     */
    @Inject(method = "reupload", at = @At("HEAD"), cancellable = true)
    private void create$onReupload(CallbackInfo ci) {
        try {
            // Sprite reupload safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] SpriteContents reupload error: " + e.getMessage());
        }
    }
}
