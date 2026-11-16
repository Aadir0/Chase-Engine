# Chase-Engine

A 2D Game Engine built in Java utilizing **LWJGL 3 (Lightweight Java Game Library)**, **OpenGL 3.3+**, and **Dear ImGui**.

---

## Features

- **Batch Renderer:**
  - High-performance 2D quad batching with dynamic vertex buffer streaming (`glBufferSubData`).
  - Multi-texture batching supporting up to 8 hardware texture slots per batch.
  - Layer-based Z-indexing and depth ordering for sprites.
- **2D Physics Engine (`physics2d`):**
  - Geometric primitives: `AABB`, `Box2D`, `Circle`, `Collider2D`, `Ray2D`, `RaycastResult`.
  - Collision detection & intersection tests via `IntersectionDetector2D` (point, ray, circle, and box intersections).
  - Physics body representation via `Rigidbody2D`.
- **Debug Drawing System (`renderer.DebugDraw`):**
  - Immediate-mode 2D debug rendering for lines, boxes, and circles with configurable lifetimes and colors.
  - Dedicated debug shader pipeline (`debugLine2D.glsl`).
- **Level Editor & Tooling (`scenes.LevelEditorScene`):**
  - Embedded **Dear ImGui** editor with High-DPI and framebuffer scaling support.
  - Palette window displaying spritesheet icons with automatic responsive wrapping.
  - **Drag-and-Drop Placement (`MouseControls`):** Interactive object placement snapped to grid dimensions.
  - **Editor Grid Lines (`GridLines`):** Dynamic viewport editor grid rendered via debug line batching.
  - **Prefabs (`Prefabs`):** Factory helpers to instantiate standard sprite objects.
- **Component-Based Entity Architecture:**
  - Modular `GameObject` hierarchy with runtime `Component` attachment.
  - Built-in components: `Transform`, `SpriteRenderer`, `FontRenderer`, `Rigidbody`, `MouseControls`, `GridLines`.
  - Scene management system (`Scene`, `LevelEditorScene`, `LevelScene`).
- **Scene Serialization & Deserialization:**
  - Full scene persistence to JSON via **Gson** with polymorphic component adapters (`ComponentDeserializer`, `GameObjectDeserializer`).
  - Automatic level loading and saving (`level.txt`).
- **Real-Time Component Inspector:**
  - Reflection-based inspector window that dynamically discovers and exposes editable fields (`int`, `float`, `boolean`, `Vector3f`, `Vector4f`) while respecting transient properties.
- **2D Camera & Math:**
  - Orthographic projection and view matrix transformations via **JOML**.
  - Smooth camera panning and viewport adjustments.
- **Asset Management (`AssetPool`):**
  - Centralized asset caching for Shaders, Textures, and Spritesheets to prevent redundant disk I/O and GPU allocations.
- **Custom Shader Pipeline:**
  - Single-file GLSL format (`#type vertex` / `#type fragment`).
  - Automatic shader parsing, compilation, linking, and uniform upload utilities.
- **Input System:**
  - Event-driven and polling keyboard/mouse listeners (`KeyListner`, `MouseListner`).
  - Screen-to-world coordinate conversion (`getOrthoX`, `getOrthoY`), drag detection, and scroll tracking.

---

## Tech Stack

- **Language:** Java 21
- **Graphics API:** OpenGL 3.3 Core Profile
- **Windowing & Input:** GLFW (via LWJGL 3.3.6)
- **Math Library:** JOML (Java OpenGL Math Library) 1.10.8
- **GUI:** Dear ImGui (`imgui-java` 1.86.12)
- **Serialization:** Google Gson 2.15.0
- **Image Loading:** STB Image (via LWJGL 3)
- **Build Tool:** Gradle (Wrapper included)

---

## Prerequisites

- **Java Development Kit (JDK):** Version 21 or higher
- **Git**

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/Aadir0/Chase-Engine.git
cd Chase-Engine
```

### 2. Build the Project
```bash
./gradlew build
```

### 3. Run the Engine
```bash
./gradlew run
```

---

## Project Structure

```
Chase-Engine/
├── app/
│   ├── build.gradle               # Subproject build definition & dependencies
│   └── src/main/java/
│       ├── Main.java              # Application entry point
│       ├── chase/                 # Core engine systems (Window, Camera, GameObject, Transform, Input, ImGui, Prefabs)
│       ├── components/            # Entity components (Component, SpriteRenderer, Rigidbody, MouseControls, GridLines, Deserializers)
│       ├── physics2d/             # 2D physics subsystem
│       │   ├── primitives/        # Geometric primitives (AABB, Box2D, Circle, Ray2D, Collider2D, RaycastResult)
│       │   └── rigidbody/         # Rigidbody2D and IntersectionDetector2D
│       ├── renderer/              # Batch renderer, DebugDraw, Line2D, Shader, Texture
│       ├── scenes/                # Scene management (Scene, LevelEditorScene, LevelScene)
│       └── util/                  # Utilities (AssetPool, JMath, Settings)
├── assets/
│   ├── fonts/                     # TTF fonts (e.g. Segoe UI)
│   ├── images/                    # Textures and spritesheets
│   │   └── spritesheet/           # Spritesheet atlases
│   └── shaders/                   # GLSL shader files (default.glsl, debugLine2D.glsl)
├── gradle/
│   ├── libs.versions.toml         # Version catalog
│   └── wrapper/                   # Gradle wrapper binaries
├── gradle.properties
├── gradlew                        # Gradle wrapper script (Unix)
├── gradlew.bat                    # Gradle wrapper batch script (Windows)
├── level.txt                      # Serialized level data
├── settings.gradle                # Multi-project Gradle settings
└── README.md
```

---

## License

This project is licensed under the terms specified in the [LICENSE](LICENSE) file.
