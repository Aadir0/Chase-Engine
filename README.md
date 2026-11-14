# Chase-Engine

A 2D Game Engine built in Java utilizing **LWJGL 3 (Lightweight Java Game Library)**, **OpenGL 3.3+**, and **Dear ImGui**.

---

## Features

- **Batch Renderer:**
  - High-performance 2D quad batching with dynamic vertex buffer uploading (`glBufferSubData`).
  - Multi-texture batching supporting up to 8 hardware texture slots per batch.
  - Layer-based Z-indexing and depth ordering for sprites.
- **Component-Based Entity Architecture:**
  - Modular `GameObject` system with dynamic `Component` attachment.
  - Built-in components including `Transform`, `SpriteRenderer`, and `FontRenderer`.
  - Scene management system (`Scene`, `LevelEditorScene`, `LevelScene`).
- **2D Camera & Math:**
  - Orthographic projection and view matrix transformations via **JOML**.
  - Camera positioning and viewport adjustments.
- **Asset Management (`AssetPool`):**
  - Centralized asset caching for Shaders, Textures, and Spritesheets to prevent redundant disk I/O and GPU memory allocations.
- **Custom Shader Pipeline:**
  - Single-file GLSL shader format (`#type vertex` / `#type fragment`).
  - Automatic shader parsing, compilation, linking, and uniform upload utilities.
- **Textures & Spritesheets:**
  - Image loading via `stb_image`.
  - Spritesheet parsing with sub-pixel UV coordinate mapping.
- **Input System:**
  - Polling and event-driven keyboard and mouse tracking (`KeyListner`, `MouseListner`).
  - Supports mouse position tracking, drag detection, and scroll offsets.
- **Editor GUI (Dear ImGui):**
  - Embedded **Dear ImGui** (using `imgui-java`) with LWJGL 3 & OpenGL 3 backends.
  - High-DPI and framebuffer scaling support for crisp rendering across resolutions and fullscreen/maximized display modes.

---

## Tech Stack

- **Language:** Java 21
- **Graphics API:** OpenGL 3.3 Core Profile
- **Windowing & Input:** GLFW (via LWJGL 3.3.6)
- **Math Library:** JOML (Java OpenGL Math Library) 1.10.8
- **GUI:** Dear ImGui (`imgui-java` 1.86.12)
- **Image Loading:** STB Image (via LWJGL 3)
- **Build Tool:** Gradle

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
│   ├── build.gradle
│   └── src/main/java/
│       ├── Main.java              # Application entry point
│       ├── aadi/                  # Core engine systems (Window, Scene, Camera, Input, ImGui)
│       ├── components/            # Game components (Sprite, SpriteRenderer, Spritesheet)
│       ├── renderer/              # Batch renderer, Shader, and Texture management
│       └── util/                  # Asset pooling and resource utilities
├── assets/
│   ├── images/                    # Spritesheets and textures
│   └── shaders/                   # GLSL shader programs
├── gradle/                        # Gradle wrapper and version catalog
├── build.gradle
├── settings.gradle
└── README.md
```

---

## License

This project is licensed under the terms specified in the [LICENSE](LICENSE) file.
