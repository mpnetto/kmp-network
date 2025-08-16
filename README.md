# KmpNetwork

Biblioteca de rede Kotlin Multiplatform (KMP) com Ktor para Android, iOS e Desktop (JVM).

## Recursos
- Cliente Ktor configurado com:
  - ContentNegotiation (JSON via kotlinx-serialization)
  - Logging de requisições/respostas
- Wrapper de resultados (NetworkResult) para sucesso/erro
- ApiClient com helpers tipados para GET e POST
- DispatcherProvider multiplataforma

## Estrutura do projeto
- `network/`: módulo de biblioteca KMP
  - `commonMain/`: núcleo compartilhado (Ktor, modelos e utilitários)
  - `androidMain/`, `iosMain/`, `desktopMain/`: implementações específicas por plataforma
- `iosApp/`: projeto iOS (SwiftUI) para integração/execução no iOS

## Alvos e requisitos
- Android: minSdk 24, compileSdk 36
- iOS: iosArm64, iosX64, iosSimulatorArm64
- Desktop: JVM (gera JAR)

Requisitos de ambiente:
- JDK 17+
- Android SDK (para Android)
- Xcode 15+ em macOS (para iOS)
- Gradle via wrapper (`./gradlew`)

## Como construir
- Build completo do módulo:
  - Windows: `gradlew clean :network:assemble`
  - macOS/Linux: `./gradlew clean :network:assemble`
- Android (AAR): `:network:assembleRelease`
- Desktop (JAR): `:network:desktopJar`
- iOS (Frameworks):
  - Dispositivo: `:network:linkReleaseFrameworkIosArm64`
  - Simulador: `:network:linkReleaseFrameworkIosSimulatorArm64`

Os artefatos são gerados em `network/build/`.

## Uso (exemplo Kotlin)
```kotlin
import org.sacada.network.ApiClient
import org.sacada.network.NetworkResult

data class Todo(val id: Int, val title: String)

suspend fun fetchTodos(): List<Todo> {
    val api = ApiClient(baseUrl = "https://example.com")
    return when (val res = api.get<List<Todo>>(path = "todos")) {
        is NetworkResult.Success -> res.data
        is NetworkResult.Error -> emptyList() // trate o erro conforme sua necessidade
    }
}
```

## Versões principais
- Kotlin: 2.2.0
- Ktor: 3.2.3
- Coroutines: 1.10.2

## Notas
- Namespace Android: `org.sacada.network`
- Sem testes Android instrumentados neste módulo.

## Contribuição
Issues e PRs são bem-vindos.
