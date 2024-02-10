# tooling-country

Kotlin multiplatform country library.

Take a look here: https://datl4g.github.io/tooling/tooling-country/dev.datlag.tooling.country/index.html

## Installation

```gradle
dependencies {
  implementation("dev.datlag.tooling:tooling-country:<version>")
}
```

## Targets

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

## Usage

A class holding country code information

#### Retrieve country by ISO 3166-1 formats:

```kotlin
// By Alpha-2 code
val country = Country.forCodeOrNull("DE")

// By Alpha-3 code
val country2 = Country.forCodeOrNull("DEU")

// By Numeric code
val country3 = Country.forCodeOrNull(276)
```

#### Get country codes from Country:

```kotlin
val alpha2 = country.codeAlpha2
val alpha3 = country.codeAlpha3
val numeric = country.codeNumeric
```

### Serialization

It supports kotlin serialization of any kind (JSON, Protobuf, etc...).

Example an API which returns the following:

```json
{
  "country": "Germany",
  "countryCode": "DE"
}
```

Can be (de-)serialized like this:

```kotlin
@Serializable
data class Response(
    val country: String,
    val countryCode: Country?
)
```

#### Strategy

There are multiple Serializer provided:

`CountryAsAlpha2StringSerializer` default strategy:

> Can deserialize any **Alpha-2, Alpha-3 or Numeric codes** provided as String
> 
> Will always serialize as **Alpha-2 String**

`CountryAsAlpha3StringSerializer`:

> Can deserialize any **Alpha-2, Alpha-3 or Numeric codes** provided as String
> 
> Will always serialize as **Alpha-3 String**

`CountryAsNumericIntSerializer`:

> Can deserialize **Numeric codes** provided as Int
> 
> Will always serialize as **Numeric Int**
