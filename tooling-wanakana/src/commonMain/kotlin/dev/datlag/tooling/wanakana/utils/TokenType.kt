package dev.datlag.tooling.wanakana.utils

import dev.datlag.tooling.wanakana.common.isEnNum
import dev.datlag.tooling.wanakana.common.isEnSpace
import dev.datlag.tooling.wanakana.common.isEnglishPunctuation
import dev.datlag.tooling.wanakana.common.isHiragana
import dev.datlag.tooling.wanakana.common.isJaNum
import dev.datlag.tooling.wanakana.common.isJaSpace
import dev.datlag.tooling.wanakana.common.isJapanese
import dev.datlag.tooling.wanakana.common.isJapanesePunctuation
import dev.datlag.tooling.wanakana.common.isKanji
import dev.datlag.tooling.wanakana.common.isKatakana
import dev.datlag.tooling.wanakana.common.isRomaji

internal sealed interface TokenType {

    abstract val value: String

    data object En : TokenType {
        override val value: String = "en"
    }

    data object Ja : TokenType {
        override val value: String = "ja"
    }

    data object EnNumber : TokenType {
        override val value: String = "englishNumeral"
    }

    data object JaNumber : TokenType {
        override val value: String = "japaneseNumeral"
    }

    data object EnPunctuation : TokenType {
        override val value: String = "englishPunctuation"
    }

    data object JaPunctuation : TokenType {
        override val value: String = "japanesePunctuation"
    }

    data object Kanji : TokenType {
        override val value: String = "kanji"
    }

    data object Hiragana : TokenType {
        override val value: String = "hiragana"
    }

    data object Katakana : TokenType {
        override val value: String = "katakana"
    }

    data object Space : TokenType {
        override val value: String = "space"
    }

    data object Other : TokenType {
        override val value: String = "other"
    }

    companion object {
        fun from(input: Char, compact: Boolean = false): TokenType {
            return if (compact) {
                when {
                    input.isEnNum() -> Other
                    input.isJaNum() -> Other
                    input.isEnSpace() -> En
                    input.isJaSpace() -> Ja
                    input.isEnglishPunctuation() -> Other
                    input.isJapanesePunctuation() -> Other
                    input.isJapanese() -> Ja
                    input.isRomaji() -> En
                    else -> Other
                }
            } else {
                when {
                    input.isJaSpace() -> Space
                    input.isEnSpace() -> Space
                    input.isJaNum() -> JaNumber
                    input.isEnNum() -> EnNumber
                    input.isJapanesePunctuation() -> JaPunctuation
                    input.isEnglishPunctuation() -> EnPunctuation
                    input.isKanji() -> Kanji
                    input.isHiragana() -> Hiragana
                    input.isKatakana() -> Katakana
                    input.isJapanese() -> Ja
                    input.isRomaji() -> En
                    else -> Other
                }
            }
        }
    }
}