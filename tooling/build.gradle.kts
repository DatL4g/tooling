import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import java.net.URL

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.publish)
    `maven-publish`
    signing
    alias(libs.plugins.dokka)
}

val libName = "tooling"
val artifact = "dev.datlag.tooling"

group = artifact
version = libraryVersion

dokka {
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(file("src"))
            remoteUrl("https://github.com/DatL4g/tooling/tree/master/tooling/src")
        }
    }
}

kotlin {
    jvm()

    androidTarget {
        publishAllLibraryVariants()
    }
    androidNativeX64()
    androidNativeX86()
    androidNativeArm64()
    androidNativeArm32()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    watchosX64()
    watchosArm64()
    watchosArm32()
    watchosSimulatorArm64()

    macosX64()
    macosArm64()

    linuxX64()
    linuxArm64()

    mingwX64()

    js(IR) {
        browser()
        nodejs()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmWasi {
        nodejs()
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.immutable)
        }

        val javaMain by creating {
            dependsOn(commonMain.get())

            jvmMain.get().dependsOn(this)
            androidMain.get().dependsOn(this)
        }

        jvmMain.dependencies {
            implementation(libs.lang)
            implementation(libs.appdirs)
        }

        androidMain.dependencies {
            implementation(libs.android)
            implementation(libs.car)
        }
    }
}

android {
    compileSdk = Configuration.compileSdk
    namespace = artifact

    defaultConfig {
        minSdk = Configuration.minSdk
    }
    buildTypes {
        val debug by getting {
            isMinifyEnabled = false
            isShrinkResources = false
        }

        val release by getting {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    compileOptions {
        sourceCompatibility = CompileOptions.sourceCompatibility
        targetCompatibility = CompileOptions.targetCompatibility
    }
}

mavenPublishing {
    publishToMavenCentral(host = SonatypeHost.S01, automaticRelease = true)
    signAllPublications()

    coordinates(
        groupId = artifact,
        artifactId = libName,
        version = libraryVersion
    )

    pom {
        name.set(libName)
        description.set("Kotlin multiplatform tooling library.")
        url.set("https://github.com/DatL4g/tooling")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        scm {
            url.set("https://github.com/DatL4g/tooling")
            connection.set("scm:git:git://github.com/DatL4g/tooling.git")
        }

        developers {
            developer {
                id.set("DatL4g")
                name.set("Jeff Retz (DatLag)")
                url.set("https://github.com/DatL4g")
            }
        }
    }
}