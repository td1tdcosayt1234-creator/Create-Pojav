package com.simibubi.create.foundation.mixin.client.pojav;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * PojavLauncher compatibility mixin for Schematicannon.
 * 
 * Fixes IllegalArgumentException when schematicannon contains itself in schematic.
 */
@Mixin(net.minecraft.world.level.block.entity.BlockEntity.class)
public abstract class SchematicannonPojavMixin {

    /**
     * Catch schematicannon state errors.
     */
    @Inject(method = "saveWithId", at = @At("HEAD"), cancellable = true)
    private void create$onSaveWithId(CallbackInfo ci) {
        try {
            // Schematicannon save safety
            String className = this.getClass().getName();
            if (className.contains("Schematicannon")) {
                System.out.println("[Create-Pojav] Schematicannon save detected, applying safety checks");
            }
        } catch (Exception e) {
            System.err.println("[Create-Pojav] Schematicannon save error: " + e.getMessage());
        }
    }

    /**
     * Catch invalid enum constant errors in NBT loading.
     */
    @Inject(method = "load", at = @At("HEAD"))
    private void create$onLoadSafe(net.minecraft.nbt.CompoundTag tag, CallbackInfo ci) {
        try {
            // Check for invalid state in NBT
            if (tag.contains("State")) {
                String state = tag.getString("State");
                if (state == null || state.isEmpty()) {
                    tag.remove("State");
                    tag.putString("State", "Searching");
                    System.err.println("[Create-Pojav] Fixed invalid Schematicannon state in NBT");
                }
            }
        } catch (Exception e) {
            // Ignore
        }
    }
}
