# Create-Pojav

A fork of the [Create mod](https://github.com/Creators-of-Create/Create) (1.20.1) with a single targeted fix for **PojavLauncher** / mobile GPU compatibility.

## The Fix

This fork patches `com.mojang.blaze3d.pipeline.RenderTarget.enableStencil()` to skip stencil buffer operations on mobile GPUs (GL4ES, Krypton, Vulkan, etc.) where they cause a `GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT` crash.

### What it does
- Detects mobile GPU environments (GL4ES, VirGL, ANGLE, Zink, Mali, Adreno, PowerVR)
- Skips `enableStencil()` and `setStencilEnabled()` on those devices
- Prevents the crash without affecting gameplay

### What it does NOT do
- Does **not** touch Flywheel, shaders, contraptions, or any game logic
- Does **not** add unnecessary try-catch wrappers
- Is **not** a bundle of 69 broken mixins

## Installation

### Option A: This Fork (build from source)
1. Clone this repo
2. Run `./gradlew build`
3. Place the jar from `build/libs/` into `.minecraft/mods/`

### Option B: Standalone Fix Mod (Recommended)
Use [create-gl4es-stencil-fix](https://github.com/Sunshine1368/create-gl4es-stencil-fix) - a tiny standalone Forge mod that does the same thing:
1. Download from [Releases](https://github.com/Sunshine1368/create-gl4es-stencil-fix/releases)
2. Place in `.minecraft/mods/` alongside Create
3. Launch - done

## Additional Tips

- Run `/flywheel backend off` in-game to disable Flywheel's instanced rendering (not needed for the crash fix, but improves performance on mobile)
- This is a **client-side only** fix - no server-side changes needed

## Compatibility

| Component | Status |
|-----------|--------|
| Minecraft | 1.20.1 |
| Forge | 47.x |
| Create | All 1.20.1 versions |
| PojavLauncher | Supported |
| ZalithLauncher | Supported |
| FCL | Supported |
| Any ARM Android | Supported |

## Technical Details

The crash occurs when Create's `UIRenderHelper$CustomRenderTarget.create()` calls `ForgeClientHooksHelper.enableStencilBuffer()` which calls `RenderTarget.enableStencil()`. On mobile OpenGL wrappers, the FBO validation step fails with `GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT` or `GL_FRAMEBUFFER_UNSUPPORTED`.

The fix is a Mixin that injects at `HEAD` of `enableStencil()` and cancels it on detected mobile devices.

## Files Changed

```
src/main/java/com/simibubi/create/foundation/mixin/client/pojav/
├── PojavCompat.java           # Device detection (GL4ES, VirGL, Mali, etc.)
├── PojavCompatManager.java    # Config management
└── RenderTargetPojavMixin.java # The actual fix: skips enableStencil() on mobile

src/main/resources/create.mixins.json  # Added RenderTargetPojavMixin to client list
```
