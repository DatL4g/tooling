package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic

/**
 * Country holding related country codes.
 */
@Serializable(CountryAsAlpha2StringSerializer::class)
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
                return code.contentEquals(other as? CharSequence, ignoreCase = true) ||  super.equals(other)
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
                return code.contentEquals(other as? CharSequence, ignoreCase = true) ||  super.equals(other)
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
                        AmericanSamoa.alpha2 -> AmericanSamoa
                        Andorra.alpha2 -> Andorra
                        Angola.alpha2 -> Angola
                        Anguilla.alpha2 -> Anguilla
                        Antarctica.alpha2 -> Antarctica
                        AntiguaAndBarbuda.alpha2 -> AntiguaAndBarbuda
                        Argentina.alpha2 -> Argentina
                        Armenia.alpha2 -> Armenia
                        Aruba.alpha2 -> Aruba
                        Australia.alpha2 -> Australia
                        Austria.alpha2 -> Austria
                        Azerbaijan.alpha2 -> Azerbaijan
                        Bahamas.alpha2 -> Bahamas
                        Bahrain.alpha2 -> Bahrain
                        Bangladesh.alpha2 -> Bangladesh
                        Barbados.alpha2 -> Barbados
                        Belarus.alpha2 -> Belarus
                        Belgium.alpha2 -> Belgium
                        Belize.alpha2 -> Belize
                        Benin.alpha2 -> Benin
                        Bermuda.alpha2 -> Bermuda
                        ÅlandIslands.alpha2 -> ÅlandIslands
                        Bhutan.alpha2 -> Bhutan
                        Bolivia.alpha2 -> Bolivia
                        Bonaire.alpha2 -> Bonaire
                        BosniaAndHerzegovina.alpha2 -> BosniaAndHerzegovina
                        Botswana.alpha2 -> Botswana
                        BouvetIsland.alpha2 -> BouvetIsland
                        Brazil.alpha2 -> Brazil
                        BritishIndianOceanTerritory.alpha2 -> BritishIndianOceanTerritory
                        BruneiDarussalam.alpha2 -> BruneiDarussalam
                        Bulgaria.alpha2 -> Bulgaria
                        BurkinaFaso.alpha2 -> BurkinaFaso
                        Burundi.alpha2 -> Burundi
                        CaboVerde.alpha2 -> CaboVerde
                        Cambodia.alpha2 -> Cambodia
                        Cameroon.alpha2 -> Cameroon
                        Canada.alpha2 -> Canada
                        CaymanIslands.alpha2 -> CaymanIslands
                        CentralAfricanRepublic.alpha2 -> CentralAfricanRepublic
                        Chad.alpha2 -> Chad
                        Chile.alpha2 -> Chile
                        China.alpha2 -> China
                        ChristmasIsland.alpha2 -> ChristmasIsland
                        CocosIslands.alpha2 -> CocosIslands
                        Colombia.alpha2 -> Colombia
                        Comoros.alpha2 -> Comoros
                        DemocraticRepublicCongo.alpha2 -> DemocraticRepublicCongo
                        Congo.alpha2 -> Congo
                        CookIslands.alpha2 -> CookIslands
                        CostaRica.alpha2 -> CostaRica
                        Croatia.alpha2 -> Croatia
                        Cuba.alpha2 -> Cuba
                        Curaçao.alpha2 -> Curaçao
                        Cyprus.alpha2 -> Cyprus
                        Czechia.alpha2 -> Czechia
                        IvoryCoast.alpha2 -> IvoryCoast
                        Denmark.alpha2 -> Denmark
                        Djibouti.alpha2 -> Djibouti
                        Dominica.alpha2 -> Dominica
                        DominicanRepublic.alpha2 -> DominicanRepublic
                        Ecuador.alpha2 -> Ecuador
                        Egypt.alpha2 -> Egypt
                        ElSalvador.alpha2 -> ElSalvador
                        EquatorialGuinea.alpha2 -> EquatorialGuinea
                        Eritrea.alpha2 -> Eritrea
                        Estonia.alpha2 -> Estonia
                        Eswatini.alpha2 -> Eswatini
                        Ethiopia.alpha2 -> Ethiopia
                        FalklandIslands.alpha2 -> FalklandIslands
                        FaroeIslands.alpha2 -> FaroeIslands
                        Fiji.alpha2 -> Fiji
                        Finland.alpha2 -> Finland
                        France.alpha2 -> France
                        FrenchGuiana.alpha2 -> FrenchGuiana
                        FrenchPolynesia.alpha2 -> FrenchPolynesia
                        FrenchSouthernTerritories.alpha2 -> FrenchSouthernTerritories
                        Gabon.alpha2 -> Gabon
                        Gambia.alpha2 -> Gambia
                        Georgia.alpha2 -> Georgia
                        Germany.alpha2 -> Germany
                        Ghana.alpha2 -> Ghana
                        Gibraltar.alpha2 -> Gibraltar
                        Greece.alpha2 -> Greece
                        Greenland.alpha2 -> Greenland
                        Grenada.alpha2 -> Grenada
                        Guadeloupe.alpha2 -> Guadeloupe
                        Guam.alpha2 -> Guam
                        Guatemala.alpha2 -> Guatemala
                        Guernsey.alpha2 -> Guernsey
                        Guinea.alpha2 -> Guinea
                        GuineaBissau.alpha2 -> GuineaBissau
                        Guyana.alpha2 -> Guyana
                        Haiti.alpha2 -> Haiti
                        HeardIslandAndMcDonaldIslands.alpha2 -> HeardIslandAndMcDonaldIslands
                        HolySee.alpha2 -> HolySee
                        Honduras.alpha2 -> Honduras
                        HongKong.alpha2 -> HongKong
                        Hungary.alpha2 -> Hungary
                        Iceland.alpha2 -> Iceland
                        India.alpha2 -> India
                        Indonesia.alpha2 -> Indonesia
                        Iran.alpha2 -> Iran
                        Iraq.alpha2 -> Iraq
                        Ireland.alpha2 -> Ireland
                        IsleOfMan.alpha2 -> IsleOfMan
                        Israel.alpha2 -> Israel
                        Italy.alpha2 -> Italy
                        Jamaica.alpha2 -> Jamaica
                        Japan.alpha2 -> Japan
                        Jersey.alpha2 -> Jersey
                        Jordan.alpha2 -> Jordan
                        Kazakhstan.alpha2 -> Kazakhstan
                        Kenya.alpha2 -> Kenya
                        Kiribati.alpha2 -> Kiribati
                        NorthKorea.alpha2 -> NorthKorea
                        SouthKorea.alpha2 -> SouthKorea
                        Kuwait.alpha2 -> Kuwait
                        Kyrgyzstan.alpha2 -> Kyrgyzstan
                        Lao.alpha2 -> Lao
                        Latvia.alpha2 -> Latvia
                        Lebanon.alpha2 -> Lebanon
                        Lesotho.alpha2 -> Lesotho
                        Liberia.alpha2 -> Liberia
                        Libya.alpha2 -> Libya
                        Liechtenstein.alpha2 -> Liechtenstein
                        Lithuania.alpha2 -> Lithuania
                        Luxembourg.alpha2 -> Luxembourg
                        Macao.alpha2 -> Macao
                        Madagascar.alpha2 -> Madagascar
                        Malawi.alpha2 -> Malawi
                        Malaysia.alpha2 -> Malaysia
                        Maldives.alpha2 -> Maldives
                        Mali.alpha2 -> Mali
                        Malta.alpha2 -> Malta
                        MarshallIslands.alpha2 -> MarshallIslands
                        Martinique.alpha2 -> Martinique
                        Mauritania.alpha2 -> Mauritania
                        Mauritius.alpha2 -> Mauritius
                        Mayotte.alpha2 -> Mayotte
                        Mexico.alpha2 -> Mexico
                        Micronesia.alpha2 -> Micronesia
                        Moldova.alpha2 -> Moldova
                        Monaco.alpha2 -> Monaco
                        Mongolia.alpha2 -> Mongolia
                        Montenegro.alpha2 -> Montenegro
                        Montserrat.alpha2 -> Montserrat
                        Morocco.alpha2 -> Morocco
                        Mozambique.alpha2 -> Mozambique
                        Myanmar.alpha2 -> Myanmar
                        Namibia.alpha2 -> Namibia
                        Nauru.alpha2 -> Nauru
                        Nepal.alpha2 -> Nepal
                        Netherlands.alpha2 -> Netherlands
                        NewCaledonia.alpha2 -> NewCaledonia
                        NewZealand.alpha2 -> NewZealand
                        Nicaragua.alpha2 -> Nicaragua
                        Niger.alpha2 -> Niger
                        Nigeria.alpha2 -> Nigeria
                        Niue.alpha2 -> Niue
                        NorfolkIsland.alpha2 -> NorfolkIsland
                        NorthMacedonia.alpha2 -> NorthMacedonia
                        NorthernMarianaIslands.alpha2 -> NorthernMarianaIslands
                        Norway.alpha2 -> Norway
                        Oman.alpha2 -> Oman
                        Pakistan.alpha2 -> Pakistan
                        Palau.alpha2 -> Palau
                        Palestine.alpha2 -> Palestine
                        Panama.alpha2 -> Panama
                        PapuaNewGuinea.alpha2 -> PapuaNewGuinea
                        Paraguay.alpha2 -> Paraguay
                        Peru.alpha2 -> Peru
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
