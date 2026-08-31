# Create Mod - PojavLauncher Compatible

Minecraft Create mod with PojavLauncher compatibility fixes.

## What This Fork Does

This is a modified version of the Create mod (1.20.1) that fixes the `GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT` crash on PojavLauncher and other mobile Minecraft launchers.

### The Problem

Create mod uses advanced OpenGL features (stencil buffers, custom framebuffers) that are not properly supported by mobile GPU translation layers like:
- GL4ES (PojavLauncher's default renderer)
- VirGL
- ANGLE
- Zink/Vulkan

This causes the game to crash with:
```
java.lang.RuntimeException: GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT
```

### The Solution

This fork adds Mixin-based patches that:
1. **Detect incompatible devices** - Automatically detects PojavLauncher/mobile GPUs
2. **Skip stencil buffer operations** - Prevents the framebuffer crash
3. **Graceful degradation** - Disables advanced rendering features that don't work on mobile

## Files Modified

### New Files Added
- `src/main/java/com/simibubi/create/foundation/mixin/client/pojav/`
  - `PojavCompat.java` - Device detection utility
  - `PojavCompatConfig.java` - Configuration options
  - `RenderTargetPojavMixin.java` - Framebuffer/stencil fix
  - `GlStateManagerPojavMixin.java` - GL state management fix
  - `PostChainCompatMixin.java` - Post-processing fix

### Modified Files
- `src/main/resources/create.mixins.json` - Added PojavLauncher mixins

## Building

```bash
./gradlew build
```

The output JAR will be in `build/libs/`.

## Installation on PojavLauncher

1. Build the mod or download the pre-built JAR
2. Copy the JAR to PojavLauncher's mods folder:
   - Android: `/storage/emulated/0/games/PojavLauncher/.minecraft/mods/`
3. Install Forge 1.20.1 in PojavLauncher
4. Launch the game

## Recommended PojavLauncher Settings

- **Renderer:** Holy GL4ES (for Create mod)
- **Memory Allocation:** 2048-3072MB
- **Java Runtime:** Java 17
- **Render Distance:** 4-6 chunks
- **Graphics:** Fast
- **Particles:** Minimal

## Limitations

- Some visual effects may be reduced on mobile
- Flywheel instanced rendering may be limited
- Performance depends on device hardware

## Credits

- Original Create mod by simibubi
- PojavLauncher team for the mobile launcher

## License

Same as original Create mod - MIT License
