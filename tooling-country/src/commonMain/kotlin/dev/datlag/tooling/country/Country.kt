package dev.datlag.tooling.country

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic

/**
 * Country with information
 */
@Serializable
sealed interface Country {

    /**
     * Country code in Alpha-2 ISO 3166-1 format
     */
    val alpha2: Format.Alpha2

    /**
     * Country code in Alpha-3 ISO 3166-1 format
     */
    val alpha3: Format.Alpha3

    /**
     * Country code in numeric ISO 3166-1 format
     */
    val numeric: Format.Numeric

    /**
     * Country code formats, following the ISO 3166-1 standard.
     * * [Alpha-2][Alpha2]
     * * [Alpha-3][Alpha3]
     * * [Numeric]
     */
    @Serializable
    sealed interface Format {

        /**
         * ISO 3166-1 Alpha-2 format.
         *
         * @param code country code.
         */
        @Serializable
        data class Alpha2 internal constructor(
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

            override fun hashCode(): Int {
                return code.hashCode()
            }

            companion object {

                /**
                 * Check if code matches common ISO 3166-1 standard.
                 * Just checks the format, doesn't look if the code actually exists.
                 *
                 * @param value the Alpha-2 country code to check.
                 * @return true if code format is valid.
                 */
                @JvmStatic
                fun isValidFormat(value: CharSequence): Boolean {
                    return value.length == 2
                            && value[0].isLatinLetter()
                            && value[1].isLatinLetter()
                }
            }
        }

        /**
         * ISO 3166-1 Alpha-3 format.
         *
         * @param code country code.
         */
        @Serializable
        data class Alpha3 internal constructor(
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

            override fun hashCode(): Int {
                return code.hashCode()
            }

            companion object {

                /**
                 * Check if code matches common ISO 3166-1 standard.
                 * Just checks the format, doesn't look if the code actually exists.
                 *
                 * @param value the Alpha-3 country code to check.
                 * @return true if code format is valid.
                 */
                @JvmStatic
                fun isValidFormat(value: CharSequence): Boolean {
                    return value.length == 3
                            && value[0].isLatinLetter()
                            && value[1].isLatinLetter()
                            && value[2].isLatinLetter()
                }
            }
        }

        /**
         * ISO 3166-1 Numeric format.
         *
         * @param code country code.
         */
        @Serializable
        data class Numeric internal constructor(
            @SerialName("code") val code: Int
        ) : Format, Number() {
            
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

            override fun hashCode(): Int {
                return code.hashCode()
            }

            companion object {
                /**
                 * Check if code matches common ISO 3166-1 standard.
                 * Just checks the format, doesn't look if the code actually exists.
                 *
                 * @param value the numeric country code to check.
                 * @return true if code format is valid.
                 */
                @JvmStatic
                fun isValidFormat(value: Int): Boolean {
                    return value in 0 .. 999
                }
            }
        }
    }

    companion object {

        /**
         * Parse country code to a country object.
         *
         * @param code country code following any [ISO 3166 Format][Format]
         * @return [Country] object or null
         */
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

        /**
         * Parse numeric country code to a country object.
         *
         * @param code country code following numeric [ISO 3166 Format][Format.Numeric]
         * @return [Country] object or null
         */
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
