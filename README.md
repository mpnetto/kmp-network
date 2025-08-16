# KmpNetwork

[![JitPack](https://jitpack.io/v/mpnetto/kmp-network.svg)](https://jitpack.io/#mpnetto/kmp-network)

Kotlin Multiplatform (KMP) networking library powered by Ktor for Android, iOS, and Desktop (JVM).

## Features
- Pre-configured Ktor HttpClient with:
  - ContentNegotiation (JSON via kotlinx-serialization)
  - Request/response Logging
- Result wrapper (NetworkResult) for success/error
- ApiClient with typed helpers for GET and POST
- Multiplatform DispatcherProvider

## Project structure
- `network/`: KMP library module
  - `commonMain/`: shared core (Ktor, models, utilities)
  - `androidMain/`, `iosMain/`, `desktopMain/`: platform-specific implementations
- `iosApp/`: iOS (SwiftUI) project for integration/running on iOS

## Targets & requirements
- Android: minSdk 24, compileSdk 36
- iOS: iosArm64, iosX64, iosSimulatorArm64
- Desktop: JVM (JAR)

Environment requirements:
- JDK 17+
- Android SDK (for Android)
- Xcode 15+ on macOS (for iOS)
- Gradle via wrapper (`./gradlew`)

## Build
- Full module build:
  - Windows: `gradlew clean :network:assemble`
  - macOS/Linux: `./gradlew clean :network:assemble`
- Android (AAR): `:network:assembleRelease`
- Desktop (JAR): `:network:desktopJar`
- iOS (Frameworks):
  - Device: `:network:linkReleaseFrameworkIosArm64`
  - Simulator: `:network:linkReleaseFrameworkIosSimulatorArm64`

Artifacts are generated under `network/build/`.

## Usage (Kotlin example)
```kotlin
import org.sacada.network.ApiClient
import org.sacada.network.NetworkResult

data class Todo(val id: Int, val title: String)

suspend fun fetchTodos(): List<Todo> {
    val api = ApiClient(baseUrl = "https://example.com")
    return when (val res = api.get<List<Todo>>(path = "todos")) {
        is NetworkResult.Success -> res.data
        is NetworkResult.Error -> emptyList() // handle the error as needed
    }
}
```

## Installation via JitPack
1) Add the JitPack repository:

Gradle Kotlin DSL (settings.gradle.kts):

```kotlin
dependencyResolutionManagement {
    repositories {
        // other repositories
        maven("https://jitpack.io")
    }
}
```

2) Add the dependency to your KMP commonMain:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("com.github.mpnetto:network:0.1.2-alpha")
            }
        }
    }
}
```

For Android-only projects: add inside `dependencies { implementation("com.github.mpnetto:network:0.1.2-alpha") }`.

## Key versions
- Kotlin: 2.2.0
- Ktor: 3.2.3
- Coroutines: 1.10.2

## Notes
- Android namespace: `org.sacada.network`
- No Android instrumented tests in this module.

## Contributing
Issues and PRs are welcome.
