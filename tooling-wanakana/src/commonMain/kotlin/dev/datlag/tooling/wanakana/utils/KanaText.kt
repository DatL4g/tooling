package dev.datlag.tooling.wanakana.utils

import dev.datlag.tooling.wanakana.common.applyMapping
import dev.datlag.tooling.wanakana.common.isKatakana
import dev.datlag.tooling.wanakana.common.katakanaToHiragana
import dev.datlag.tooling.wanakana.common.matchSelection
import dev.datlag.tooling.wanakana.conversion.KanaToRomaji

internal data class KanaText(
    val text: String,
    val selection: IntRange
) {
    constructor(text: String, start: Int, end: Int) : this(text, start..end)

    fun toRomaji(
        uppercaseKatakana: Boolean = false,
        convertEnding: Boolean = true
    ): KanaText {
        if (text.isEmpty()) {
            return this
        }

        val tokens = splitIntoRomaji(text, convertEnding)
        val newSelection = selection.matchSelection(tokens)
        val newText = tokens.joinToString(separator = "") { token ->
            val romaji = token.value
            if (romaji == null) {
                text.slice(token.range)
            } else {
                val makeUpperCase = uppercaseKatakana && text.slice(token.range).isKatakana()
                if (makeUpperCase) {
                    romaji.uppercase()
                } else {
                    romaji
                }
            }
        }

        return KanaText(newText, newSelection)
    }

    private fun splitIntoRomaji(input: String, convertEnding: Boolean): List<ConversionToken> {
        val map = KanaToRomaji.kanaToHepburnMap
        val hiragana = input.katakanaToHiragana(true)
        return hiragana.applyMapping(map, convertEnding)
    }
}
