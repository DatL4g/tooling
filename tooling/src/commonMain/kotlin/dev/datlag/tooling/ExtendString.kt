package dev.datlag.tooling

fun String.getDigitsOrNull(): String? {
    val replaced = this.replace("\\D+".toRegex(), "")
    return replaced.ifBlank { null }
}