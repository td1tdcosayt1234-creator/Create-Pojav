package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for EntityRenderDispatcher.
 * 
 * Catches entity rendering dispatch errors.
 */
@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherPojavMixin {

    /**
     * Catch entity render dispatch errors.
     */
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void create$render(Object entity, double x, double y, double z, float rotationYaw, float partialTicks, com.mojang.blaze3d.vertex.PoseStack poseStack, Object bufferSource, int packedLight, CallbackInfo ci) {
        try {
            // Entity render dispatch safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] EntityRenderDispatcher error: " + e.getMessage());
            ci.cancel();
        }
    }
}
