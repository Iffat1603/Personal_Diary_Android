# CA3 – Personal Diary (Android)

A simple Android diary app built with **Kotlin**, **Jetpack Compose** and **Room**. Users can write a diary entry with a title and content, and entries are stored locally in a SQLite database. The app allows a maximum of **3 entries**.

## Features

- Add a diary entry (title + content)
- Entries persist locally using Room (`diary_db`)
- Entry limit: saving a 4th entry shows "Maximum 3 entries reached!"
- Live counter of saved entries (`x/3`)
- Material 3 UI built entirely with Jetpack Compose

## Tech Stack

| Area | Technology |
|------|------------|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | `AndroidViewModel` + Room DAO |
| Database | Room (SQLite), KSP annotation processing |
| Build | Gradle Kotlin DSL, version catalog (`gradle/libs.versions.toml`) |
| Min SDK / Target SDK | 24 / 37 |

## Project Structure

```
CA3/
├── app/
│   └── src/main/java/com/example/ca3/
│       ├── MainActivity.kt      # Compose UI (DiaryScreen)
│       ├── DiaryViewModel.kt    # State + 3-entry limit logic
│       ├── DiaryEntry.kt        # Room entity (table: diary)
│       ├── DiaryDao.kt          # insert / getAll / count queries
│       ├── AppDatabase.kt       # Room database singleton
│       └── ui/theme/            # Color, Theme, Type
├── gradle/                      # Wrapper + version catalog
├── build.gradle.kts
└── settings.gradle.kts
```

## Getting Started

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd CA3
   ```
2. Open the folder in **Android Studio** (use a recent version that supports the Android Gradle Plugin version in `gradle/libs.versions.toml`).
3. Let Gradle sync finish.
4. Run on an emulator or a physical device (Android 7.0 / API 24 or higher).

Or build from the command line:

```bash
./gradlew assembleDebug        # Linux / macOS
gradlew.bat assembleDebug      # Windows
```

## How It Works

1. `DiaryScreen` collects a title and content from two text fields.
2. On **Save Entry**, `DiaryViewModel.addEntry()` checks the current row count through `DiaryDao.getCount()`.
3. If fewer than 3 entries exist, the entry is inserted and the list reloads; otherwise a limit message is shown.
