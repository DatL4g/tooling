# Tooling

This is a collection of tooling methods useful for nearly all **Kotlin Multiplatform** applications.

It's divided in separate modules to provide minimal to excessive support depending on your usecase.

- [Core module](#core-module)
- [Async module](#async-module)
- [Compose module](#compose-module)
- [Decompose module](#decompose-module)

## Core module

The **tooling** core module provides simple platform information as well as **File** extension functions.

Take a look here: https://datl4g.github.io/tooling/tooling/dev.datlag.tooling/index.html

### Targets

It supports the following targets:

|  ✅ Supported   | ❌ Unsupported |
|:--------------:|:-------------:|
|    Android     |               |
| Android Native |               |
|      JVM       |               |
|      iOS       |               |
|      tvOS      |               |
|    watchOS     |               |
|     macOS      |               |
|     Linux      |               |
|    Windows     |               |
|   Javascript   |               |
|      WASM      |               |
|      WASI      |               |

### Usage

```gradle
dependencies {
  implementation("dev.datlag.tooling:tooling:1.1.0")
}
```

## Async module

The **tooling-async** module extends the core module with some coroutine methods.

Take a look here: https://datl4g.github.io/tooling/tooling-async/dev.datlag.tooling.async/index.html

### Targets

It supports the following targets:

|  ✅ Supported   | ❌ Unsupported |
|:--------------:|:-------------:|
|    Android     |     WASI      |
| Android Native |               |
|      JVM       |               |
|      iOS       |               |
|      tvOS      |               |
|    watchOS     |               |
|     macOS      |               |
|     Linux      |               |
|    Windows     |               |
|   Javascript   |               |
|      WASM      |               |

### Usage

```gradle
dependencies {
  implementation("dev.datlag.tooling:tooling-async:1.1.0")
}
```

## Compose module

The **tooling-compose** module extends the async module with some Compose related functions.

Take a look here: https://datl4g.github.io/tooling/tooling-compose/dev.datlag.tooling.compose/index.html

### Targets

It supports the following targets:

|  ✅ Supported   | ❌ Unsupported  |
|:--------------:|:--------------:|
|    Android     | Android Native |
|      JVM       |      tvOS      |
|      iOS       |    watchOS     |
|     macOS      |     Linux      |
|   Javascript   |    Windows     |
|      WASM      |      WASI      |

### Usage

```gradle
dependencies {
  implementation("dev.datlag.tooling:tooling-compose:1.1.0")
}
```

## Decompose module

The **tooling-decompose** module extends the compose module with some Decompose related functions.

Take a look here:

- https://datl4g.github.io/tooling/tooling-decompose/dev.datlag.tooling.decompose/index.html
- https://datl4g.github.io/tooling/tooling-decompose/dev.datlag.tooling.decompose.lifecycle/index.html

### Targets

It supports the following targets:

|  ✅ Supported   | ❌ Unsupported  |
|:--------------:|:--------------:|
|    Android     | Android Native |
|      JVM       |      tvOS      |
|      iOS       |    watchOS     |
|     macOS      |     Linux      |
|   Javascript   |    Windows     |
|      WASM      |      WASI      |

### Usage

```gradle
dependencies {
  implementation("dev.datlag.tooling:tooling-decompose:1.1.0")
}
```
