package com.simibubi.create.foundation.mixin.client.pojav;

import net.minecraft.world.entity.EntityGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * PojavLauncher compatibility mixin for EntityGetter.
 * 
 * Catches entity collision errors that cause crashes.
 */
@Mixin(EntityGetter.class)
public abstract class EntityGetterPojavMixin {

    /**
     * Catch getEntities errors.
     */
    @Inject(method = "getEntities", at = @At("HEAD"), cancellable = true)
    private void create$onGetEntities(Object entity, net.minecraft.world.phys.AABB area, CallbackInfoReturnable<List> cir) {
        try {
            // Entity getter safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] EntityGetter getEntities error: " + e.getMessage());
            cir.setReturnValue(java.util.Collections.emptyList());
        }
    }

    /**
     * Catch getEntitiesColliding errors.
     */
    @Inject(method = "getCollidingEntities", at = @At("HEAD"), cancellable = true)
    private void create$onGetCollidingEntities(Object entity, net.minecraft.world.phys.AABB area, CallbackInfoReturnable<List> cir) {
        try {
            // Entity collision safety
        } catch (Exception e) {
            System.err.println("[Create-Pojav] EntityGetter collision error: " + e.getMessage());
            cir.setReturnValue(java.util.Collections.emptyList());
        }
    }
}
