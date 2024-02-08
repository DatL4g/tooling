package dev.datlag.tooling.country

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic

@Serializable
sealed interface Country {

    val alpha2: Format.Alpha2
    val alpha3: Format.Alpha3
    val numeric: Format.Numeric

    @Serializable
    sealed interface Format {

        @Serializable
        data class Alpha2(
            @SerialName("code") val code: String
        ) : Format, CharSequence {

            override val length: Int
                get() = code.length

            override fun get(index: Int): Char {
                return code[index]
            }

            override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                return code.subSequence(startIndex, endIndex)
            }

            override fun toString(): String {
                return code
            }

            override fun equals(other: Any?): Boolean {
                if (other is CharSequence) {
                    return other == code || other.toString() == code || super.equals(other)
                }
                return super.equals(other)
            }

            companion object {
                @JvmStatic
                fun isValidFormat(value: CharSequence): Boolean {
                    return value.length == 2
                            && value[0].isLatinLetter()
                            && value[1].isLatinLetter()
                }
            }
        }

        @Serializable
        data class Alpha3(
            @SerialName("code") val code: String
        ) : Format, CharSequence {

            override val length: Int
                get() = code.length

            override fun get(index: Int): Char {
                return code[index]
            }

            override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                return code.subSequence(startIndex, endIndex)
            }

            override fun toString(): String {
                return code
            }

            override fun equals(other: Any?): Boolean {
                if (other is CharSequence) {
                    return other == code || other.toString() == code || super.equals(other)
                }
                return super.equals(other)
            }

            companion object {
                @JvmStatic
                fun isValidFormat(value: CharSequence): Boolean {
                    return value.length == 3
                            && value[0].isLatinLetter()
                            && value[1].isLatinLetter()
                            && value[2].isLatinLetter()
                }
            }
        }

        @Serializable
        data class Numeric(
            @SerialName("code") val code: Int
        ) : Format, Number() {
            companion object {
                @JvmStatic
                fun isValidFormat(value: Int): Boolean {
                    return value in 0 .. 999
                }
            }

            override fun toByte(): Byte {
                return code.toByte()
            }

            override fun toDouble(): Double {
                return code.toDouble()
            }

            override fun toFloat(): Float {
                return code.toFloat()
            }

            override fun toInt(): Int {
                return code
            }

            override fun toLong(): Long {
                return code.toLong()
            }

            override fun toShort(): Short {
                return code.toShort()
            }
        }
    }

    companion object {
        @JvmStatic
        fun parseOrNull(code: CharSequence): Country? {
            fun parseAlpha2(): Country? {
                return code.takeIf(Format.Alpha2::isValidFormat)?.let { code ->
                    when (code) {
                        Afghanistan.alpha2 -> Afghanistan
                        Albania.alpha2 -> Albania
                        Algeria.alpha2 -> Algeria
                        else -> null
                    }
                }
            }

            fun parseAlpha3(): Country? {
                return code.takeIf(Format.Alpha3::isValidFormat)?.let { code ->
                    when (code) {
                        Afghanistan.alpha3 -> Afghanistan
                        Albania.alpha3 -> Albania
                        Algeria.alpha3 -> Algeria
                        else -> null
                    }
                }
            }

            return code.toString().trim().toIntOrNull()?.let(::parseOrNull) ?: parseAlpha3() ?: parseAlpha2()
        }

        @JvmStatic
        fun parseOrNull(code: Int): Country? {
            return when (code) {
                Afghanistan.numeric.toInt() -> Afghanistan
                Albania.numeric.toInt() -> Albania
                else -> null
            }
        }
    }
}
