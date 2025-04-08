# 🚗 Mini Fleet Tracker with Simulated IoT Sensor Integration

Aplikasi Android sederhana untuk simulasi tracking kendaraan real-time dengan tampilan peta dan sensor ala IoT. Dibuat dengan bahasa **Java**, memakai **Google Maps SDK**, dan UI mengikuti **Material Design 3**.

## ✨ Fitur Utama

- 🗺️ **Live Vehicle Map View**
  - Menampilkan kendaraan bergerak di Google Maps (rute dummy)
- 📡 **Simulasi Data Sensor**
  - Speed (acak 0–120 km/h)
  - Engine status (ON/OFF)
  - Door status (OPEN/CLOSED)
- ⚠️ **In-App Alerts**
  - Speed melebihi 80 km/h
  - Pintu terbuka saat kendaraan bergerak
  - Engine ON/OFF
- ✅ Clean UI dengan Material Design
- 📁 Struktur rapih dan siap dikembangkan ke MVVM
- 📱 Dibuat menggunakan Android Studio + Java

## 📷 Preview


## 📦 Tech Stack

- **Java**
- **Google Maps SDK**
- **Material Design 3**
- **Android SDK 35**
- **Gradle Kotlin DSL (kts)**

## 🛠️ Setup Proyek

1. Clone repo ini atau buat project baru di Android Studio (Java, minSdk 24)
2. Tambahkan Maps SDK:
    ```kotlin
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    ```
3. Tambahkan permission di `AndroidManifest.xml`:
    ```xml
    <uses-permission android:name="android.permission.INTERNET"/>
    ```
4. Tambahkan API Key di `AndroidManifest.xml`:
    ```xml
    <application>
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="YOUR_API_KEY_HERE"/>
    </application>
    ```
5. Jalankan aplikasi, dan lihat kendaraan bergerak di peta!

## 🔧 Struktur Folder

