import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.publish)
    `maven-publish`
    signing
}

val libName = "tooling-decompose"
val artifact = "dev.datlag.tooling"

group = artifact
version = libraryVersion

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

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.get().dependencies {
            api(project(":tooling-compose"))

            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.coroutines)
        }

        androidMain.get().dependencies {
            implementation(libs.coroutines.android)
        }
        jvmMain.get().dependencies {
            implementation(libs.coroutines.swing)
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

compose {
    kotlinCompilerPlugin.set(dependencies.compiler.forKotlin("1.9.21"))
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.22")
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