import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import java.net.URL

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.publish)
    `maven-publish`
    signing
    alias(libs.plugins.dokka)
}

val libName = "tooling-compose"
val artifact = "dev.datlag.tooling"

group = artifact
version = libraryVersion

tasks.dokkaHtmlPartial {
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(file("src"))
            remoteUrl.set(URL("https://github.com/DatL4g/tooling/tree/master/tooling-compose/src"))
        }
    }
}

kotlin {
    jvm()
    androidTarget {
        publishAllLibraryVariants()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    js(IR) {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.get().dependencies {
            api(project(":tooling-async"))

            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(libs.coroutines)
        }

        androidMain.get().dependencies {
            implementation(libs.android)
            implementation(libs.coroutines.android)

            implementation(libs.tv.compose.foundation)
            implementation(libs.tv.compose.material)
        }
        jvmMain.get().dependencies {
            implementation(libs.coroutines.swing)
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain.get())

            jvmMain.orNull?.dependsOn(this)
            nativeMain.orNull?.dependsOn(this)
            jsMain.orNull?.dependsOn(this)
            wasmJsMain.orNull?.dependsOn(this)
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