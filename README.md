# 📱 MNEMO — Personal AI Memory Assistant for Smartphones

<div align="center">

# **MNEMO**
### *DON'T REMEMBER WHERE. REMEMBER WHAT.*

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-000000?style=for-the-badge&logo=materialdesign&logoColor=white)](https://m3.material.io/)
[![Privacy First](https://img.shields.io/badge/Security-Local--First%20%26%20Biometric-green?style=for-the-badge)](https://developer.android.com/training/articles/user-data-security)

</div>

---

## 💡 What is MNEMO?

**MNEMO** is a privacy-first, local-first personal AI memory assistant for smartphones. It creates a semantic memory layer over everything you save on your phone — screenshots, photos, documents, certificates, notes, and voice clips.

Instead of forcing you to remember:
- Which app contained it
- The exact filename or folder
- The exact date or keywords

**MNEMO lets you describe what you remember naturally.**

> *"Find that gaming laptop I saw around ₹90,000."*  
> *"Show my 10th class marks memo."*  
> *"Where did we take these photos during our beach trip?"*

---

## 🔒 Security & Privacy Principle

### **`USER SELECTS → MNEMO REMEMBERS`**

NEVER scan the whole phone silently. MNEMO adheres to strict privacy principles:
- **No Background Sweeping**: MNEMO does NOT quietly crawl your gallery, messages, contacts, or financial apps.
- **User-Controlled Ingestion**: Uses Android's modern **Photo Picker** and **Storage Access Framework (SAF)**.
- **Biometric Safeguard**: Automatically detects sensitive data (passwords, PINs, IDs) via `SensitivityDetector` and offers 1-tap lock into the **Biometric Vault**.

---

## 🔥 Key Features

- 🔍 **Natural Language Memory Search**: Instant search across text, photos, documents, and voice notes.
- 💬 **Ask MNEMO**: Conversational Q&A backed by your saved evidence (*"Why did I save this?"*, *"Compare these options"*).
- 📍 **Grounded Location Reasoning**: Differentiates between **GPS EXIF Data** (`📍 Verified Location`) and **Visual Clues** (`📍 Visual Clue: Palm trees & beach`).
- 💊 **Memory Capsules**: Smart grouping of memories into projects and trips (*"Puducherry Trip"*, *"Final Year Project"*).
- 🔐 **Biometric Vault**: Fingerprint/Face authentication for sensitive document storage.
- 🎨 **Midnight Dark Theme**: Customized Material 3 aesthetic (`#090B15` midnight background with electric violet & soft blue accents).

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin 1.9.23
- **UI Framework**: Jetpack Compose + Material 3
- **Architecture**: Clean Architecture (ViewModel + Repository Pattern + Coroutines StateFlow)
- **Local Database**: Room Database
- **ML & Intelligence**: On-device ML Kit OCR & EXIF Metadata parsing
- **Navigation**: Android Compose Navigation
- **Security**: Android Biometric Prompt API + Encrypted SharedPreferences

---

## 🚀 How to Run in Android Studio

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/jaswanth07108/MNEMO.git
   ```

2. **Open in Android Studio**:
   - Open Android Studio.
   - Click **Open** and select the cloned `MNEMO` project folder.

3. **Build & Run**:
   - Let Gradle sync dependencies.
   - Select your Android Emulator or USB-connected Physical Device.
   - Press **Run `▶`** (`Shift + F10`).

---

<div align="center">
  <sub>Built with ❤️ for Privacy & Intelligence.</sub>
</div>
