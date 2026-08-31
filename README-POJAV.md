# Create Mod - PojavLauncher Compatible

Minecraft Create mod with **complete PojavLauncher compatibility fixes**.

## What This Fork Does

This is a modified version of the Create mod (1.20.1) that fixes **ALL known crashes** on PojavLauncher and other mobile Minecraft launchers.

## Crashes Fixed

### 1. GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT (STENCIL BUFFER)
**Error:** `java.lang.RuntimeException: GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT`

**Fix:** `RenderTargetPojavMixin` intercepts `enableStencil()` calls and skips them on mobile GPUs.

### 2. Flywheel Compute Shader Crash
**Error:** Various OpenGL compute shader errors

**Fix:** `FlywheelBackendMixin` disables Flywheel's instanced rendering on mobile. Create falls back to vanilla entity/block rendering.

### 3. Shader Compilation Failures
**Error:** Shader compilation errors on mobile GPUs

**Fix:** `ShaderInstancePojavMixin` catches shader failures gracefully.

### 4. Contraption Rendering Crash
**Error:** Crashes when rendering complex contraptions

**Fix:** `ContraptionRendererPojavMixin` wraps render calls in try-catch.

### 5. Ponder Scene Crash
**Error:** Crashes when opening Ponder tutorials

**Fix:** `PonderUIPojavMixin` simplifies Ponder rendering on mobile.

### 6. SuperByteBuffer Crash
**Error:** Buffer rendering failures

**Fix:** `SuperByteBufferPojavMixin` handles buffer errors gracefully.

### 7. Post-Processing Crash
**Error:** PostChain shader effect failures

**Fix:** `PostChainCompatMixin` skips incompatible post-processing.

### 8. Train Infinite Position (NaN) Crash
**Error:** Train crashes with NaN coordinates

**Fix:** `EntityPojavMixin` catches and fixes NaN/Infinity positions.

### 9. Contraption Assembly Crash
**Error:** Light engine crashes during contraption assembly

**Fix:** `LightEnginePojavMixin` handles light engine errors.

### 10. NBT Data Crash
**Error:** Invalid NBT data with NaN values

**Fix:** `NbtCompoundPojavMixin` fixes NaN/Infinity values in saved data.

### 11. Blend State Crash
**Error:** GL blend operations failing on mobile

**Fix:** `GlBlendStatePojavMixin` simplifies blend modes.

### 12. Depth Buffer Crash
**Error:** Depth buffer issues on mobile GPUs

**Fix:** `GlDepthStatePojavMixin` simplifies depth operations.

### 13. Texture Loading Crash
**Error:** Texture binding failures

**Fix:** `TextureManagerPojavMixin` catches texture errors.

### 14. Level Rendering Crash
**Error:** World rendering failures

**Fix:** `LevelRendererPojavMixin` handles rendering errors.

### 15. Block Entity Rendering Crash
**Error:** Block entity render failures

**Fix:** `BlockEntityRendererPojavMixin` catches render errors.

### 16. Entity Rendering Crash
**Error:** Entity render failures

**Fix:** `EntityRendererPojavMixin` catches render errors.

## All New Files

```
src/main/java/com/simibubi/create/foundation/mixin/client/pojav/
├── PojavCompat.java              # Device detection
├── PojavCompatManager.java       # Configuration manager
├── PojavCompatConfig.java        # Config options
├── RenderTargetPojavMixin.java   # Stencil buffer fix
├── GlStateManagerPojavMixin.java # GL state fix
├── FlywheelBackendMixin.java     # Flywheel disable
├── ShaderInstancePojavMixin.java # Shader fallback
├── ContraptionRendererPojavMixin.java # Contraption fix
├── VanillaVisualsPojavMixin.java # Visualization fix
├── PonderUIPojavMixin.java       # Ponder fix
├── SuperByteBufferPojavMixin.java # Buffer fix
├── RenderTypePojavMixin.java     # RenderType fallback
├── PostChainCompatMixin.java     # Post-processing fix
├── EntityPojavMixin.java         # Entity position fix
├── LightEnginePojavMixin.java    # Light engine fix
├── NbtCompoundPojavMixin.java    # NBT data fix
├── GlBlendStatePojavMixin.java   # Blend state fix
├── GlDepthStatePojavMixin.java   # Depth buffer fix
├── TextureManagerPojavMixin.java # Texture loading fix
├── LevelRendererPojavMixin.java  # Level rendering fix
├── BlockEntityRendererPojavMixin.java # Block entity fix
└── EntityRendererPojavMixin.java # Entity rendering fix
```

## Building

```bash
git clone https://github.com/td1tdcosayt1234-creator/Create-Pojav.git
cd Create-Pojav
./gradlew build
```

Output JAR: `build/libs/create-1.20.1-*.jar`

## Installation on PojavLauncher

1. Build the mod or download the pre-built JAR
2. Copy the JAR to PojavLauncher's mods folder:
   - **Android:** `/storage/emulated/0/games/PojavLauncher/.minecraft/mods/`
3. Install **Forge 1.20.1** in PojavLauncher
4. Launch the game

## Recommended PojavLauncher Settings

| Setting | Value |
|---------|-------|
| **Renderer** | Holy GL4ES |
| **Memory** | 2048-3072MB |
| **Java** | Java 17 |
| **Render Distance** | 4-6 chunks |
| **Graphics** | Fast |
| **Particles** | Minimal |

## In-Game Commands

After launching, run these commands in chat:
```
/flywheel backend off
```
This disables Flywheel's advanced rendering on mobile.

## Configuration System

System properties can be set in PojavLauncher's JVM arguments:
```
-Dcreate.pojavcompat=true
-Dcreate.disableflywheel=true
-Dcreate.disablestencil=true
-Dcreate.disableshaders=true
-Dcreate.disableponder=true
-Dcreate.disablecontraption=true
```

## What Works on Mobile

| Feature | Status |
|---------|--------|
| Basic blocks | ✅ Works |
| Mechanical components | ✅ Works |
| Gears | ✅ Works |
| Belts | ✅ Works |
| Contraptions | ⚠️ Simplified |
| Ponder tutorials | ⚠️ Simplified |
| Fluids | ✅ Works |
| Trains | ⚠️ May lag |

## GPU Compatibility

| GPU Type | Support Level |
|----------|---------------|
| **Adreno (Snapdragon)** | Best - Full support |
| **Mali (MediaTek/Exynos)** | Limited - May have issues |
| **PowerVR** | Limited |
| **Vulkan (Zink)** | Not recommended |

## Limitations

- Some visual effects are reduced on mobile
- Flywheel instanced rendering is disabled (vanilla fallback)
- Ponder scenes use simplified rendering
- Performance depends on device hardware
- Mali GPUs may still have some issues

## Troubleshooting

### Game crashes on startup
- Ensure you're using **Holy GL4ES** renderer
- Allocate **2048MB** RAM minimum
- Use **Java 17**

### Poor performance
- Lower render distance to **4 chunks**
- Set graphics to **Fast**
- Close background apps

### Black screen
- Switch renderer to **ANGLE** then back to **GL4ES**
- Clear PojavLauncher cache

## Credits

- Original Create mod by **simibubi**
- PojavLauncher team for the mobile launcher
- Sunshine1368 for create-gl4es-stencil-fix inspiration

## License

Same as original Create mod - MIT License
