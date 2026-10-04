# 📱 MNEMO — On-Device AI Memory Assistant for OriginOS

<div align="center">

# **MNEMO**
### *DON'T REMEMBER WHERE. REMEMBER WHAT.*
#### *Powered by OriginOS 5 Atomic Design & On-Device NPU SLM Intelligence*

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![OriginOS 5](https://img.shields.io/badge/Design-OriginOS%205-00E5FF?style=for-the-badge&logo=vivo&logoColor=black)](https://www.vivo.com/originos)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Privacy First](https://img.shields.io/badge/Security-Local--First%20%26%20Biometric-green?style=for-the-badge)](https://developer.android.com/training/articles/user-data-security)

</div>

---

## 💡 What is MNEMO?

**MNEMO** is a privacy-first, local-first personal AI memory assistant designed for smartphones running **OriginOS 5**. It creates a semantic, indexed memory layer over everything you save on your phone — screenshots, photos, documents, certificates, notes, and voice clips.

Instead of forcing you to remember:
- Which app or folder contained it
- The exact filename or date saved
- Specific keywords or tag names

**MNEMO lets you describe what you remember naturally.**

> *"Show gaming laptops around ₹90,000."*  
> *"Show my B.Tech degree & certificates."*  
> *"Where did we take Puducherry beach trip photos?"*  
> *"What was I working on during final year?"*

---

## 🎨 OriginOS 5 Atomic Design System

MNEMO features a tailored UI matching iQOO's signature **OriginOS 5** aesthetic:

- ⚡ **Origin Dock**: Floating navigation dock (`36.dp` radius container) with squircle icon tiles and Cyan active glow.
- 🧪 **Atomic Glass Widgets**: Translucent glass containers (`#182038`) with `24.dp` squircle rounding and high-tech border accents.
- ⚡ **Origin NPU Status Badge**: Live `⚡ ORIGIN AI • NPU ACCELERATED` indicator tag on the main dashboard.
- 🤖 **On-Device SLM Benchmark Display**: Live NPU performance metrics chip (`🤖 MNEMO-SLM-2B • ⚡ 142 ms • 38.5 tok/s`).
- 🎨 **Obsidian OLED Palette**:
  - **Origin Energy Cyan (`#00E5FF`)**: Primary accent, active status, search focus.
  - **Origin Coral Orange (`#FF5722`)**: Biometric Vault encryption, critical tags, sensitivity alerts.
  - **Emerald Mint (`#10B981`)**: Grounded AI evidence & price metadata tags.
  - **Obsidian Dark (`#090C15`)**: Deep OLED background.

---

## 🔒 Security & Privacy Principle

### **`USER SELECTS → MNEMO REMEMBERS`**

- **Zero Background Sweeping**: MNEMO NEVER silently crawls your phone gallery, financial apps, or private messages in the background.
- **System Ingestion Pickers**: Uses Android's native **Photo Picker** (`PickVisualMedia`) and **Storage Access Framework (SAF)** — memory capture requires explicit user action.
- **Atomic Biometric Vault**: On-device sensitivity classification (`SensitivityDetector`) automatically protects credentials, IDs, and financial memos behind fingerprint/face authentication (`BiometricPrompt`).

---

## 🧠 On-Device AI Engine & Ingestion Capabilities

- 🤖 **On-Device SLM Inference Engine** (`LocalLLMInferenceEngine.kt`): Formats retrieved candidate memories into structured local LLM prompts (`<system> ... </system><user> ... </user>`) and streams grounded answers with token generation metrics (`142 ms`, `38.5 tok/s`).
- 🎙 **Voice Audio Recorder & STT Transcriber** (`AudioRecorderHelper.kt`): Microphone voice recording pipeline with real-time STT transcript processing into memory search index.
- 🖼 **System Storage Pickers**: Live device image and SAF document picking via `ActivityResultContracts.PickVisualMedia()` and `ActivityResultContracts.GetContent()`.
- 📊 **Dynamic Memory Enricher** (`MemoryEnricher.kt`): Auto-extracts prices (`₹`), GPU specs (RTX 4060/4050/3060), RAM (16GB/8GB/32GB), document categories (Certificate, Marks Memo, Receipt, Ticket), and 8 Indian city EXIF/visual location tags.
- ⚖️ **Atomic Spec Comparison**: Automatically builds side-by-side comparison tables for product research (price, GPU, RAM, rationale).
- 📍 **Location Reasoning**: Differentiates **Verified EXIF GPS** (`📍 EXIF: Puducherry`) from **Visual Location Clues** (`📍 Visual: Puducherry`).

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin 2.0+ (Target JVM 17)
- **SDK**: compileSdk 36 (Android 15 / OriginOS 5 compatible)
- **UI Framework**: Jetpack Compose + Material 3 + Navigation 3
- **Architecture**: Clean Architecture (ViewModel + Repository Pattern + Kotlin Coroutines StateFlow)
- **Build System**: Gradle 9.1.0 (Configuration Cache enabled)
- **Audio & Media**: Android MediaRecorder + AAC audio encoding
- **Security**: Android Biometric Prompt API + Encrypted SharedPreferences

---

## 🚀 How to Build & Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/jaswanth07108/MNEMO.git
   ```

2. **Open in Android Studio**:
   - Open Android Studio (Ladybug / Koala or newer).
   - Select **Open** and choose the `MNEMO` root directory.

3. **Build APK via Gradle Command Line**:
   ```bash
   .\gradlew.bat assembleDebug
   ```
   *Output APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.*

4. **Run on iQOO / Android Device**:
   - Enable USB Debugging on your device.
   - Click **Run `▶`** (`Shift + F10`) in Android Studio.

---

<div align="center">
  <sub>Built for iQOO Hackathon 2026 • OriginOS 5 AI Powered</sub>
</div>
