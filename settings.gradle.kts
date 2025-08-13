rootProject.name = "tooling"

include(":tooling")
include(":tooling-async")
include(":tooling-compose")
include(":tooling-country")
include(":tooling-wanakana")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}