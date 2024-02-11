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
    val codeAlpha2: Code.Alpha2

    /**
     * Country code in Alpha-3 ISO 3166-1 format
     */
    val codeAlpha3: Code.Alpha3

    /**
     * Country code in numeric ISO 3166-1 format
     */
    val codeNumeric: Code.Numeric

    /**
     * Country code formats, following the ISO 3166-1 standard.
     * * [Alpha-2][Alpha2]
     * * [Alpha-3][Alpha3]
     * * [Numeric]
     */
    @Serializable
    sealed interface Code {

        /**
         * ISO 3166-1 Alpha-2 format.
         *
         * @param code country code.
         */
        @Serializable
        data class Alpha2 internal constructor(
            @SerialName("code") val code: String
        ) : Code, CharSequence {

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
                return code.contentEquals(other as? CharSequence, ignoreCase = true) || super.equals(other)
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
        ) : Code, CharSequence {

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
                return code.contentEquals(other as? CharSequence, ignoreCase = true) || super.equals(other)
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
        ) : Code, Number() {

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
         * @param code country code following any [ISO 3166 Format][Code]
         * @return [Country] object or null
         */
        @JvmStatic
        fun forCodeOrNull(code: CharSequence): Country? {
            val trimmedCode = code.trim()

            fun parseAlpha2(): Country? {
                val validCode = trimmedCode.takeIf(Code.Alpha2::isValidFormat)
                return when {
                    validCode == null -> null // make sure it doesn't check other types
                    Afghanistan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Afghanistan
                    Albania.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Albania
                    Algeria.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Algeria
                    AmericanSamoa.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> AmericanSamoa
                    Andorra.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Andorra
                    Angola.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Angola
                    Anguilla.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Anguilla
                    Antarctica.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Antarctica
                    AntiguaAndBarbuda.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> AntiguaAndBarbuda
                    Argentina.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Argentina
                    Armenia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Armenia
                    Aruba.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Aruba
                    Australia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Australia
                    Austria.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Austria
                    Azerbaijan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Azerbaijan
                    Bahamas.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bahamas
                    Bahrain.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bahrain
                    Bangladesh.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bangladesh
                    Barbados.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Barbados
                    Belarus.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Belarus
                    Belgium.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Belgium
                    Belize.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Belize
                    Benin.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Benin
                    Bermuda.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bermuda
                    ÅlandIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> ÅlandIslands
                    Bhutan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bhutan
                    Bolivia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bolivia
                    Bonaire.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bonaire
                    BosniaAndHerzegovina.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> BosniaAndHerzegovina
                    Botswana.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Botswana
                    BouvetIsland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> BouvetIsland
                    Brazil.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Brazil
                    BritishIndianOceanTerritory.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> BritishIndianOceanTerritory
                    BruneiDarussalam.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> BruneiDarussalam
                    Bulgaria.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Bulgaria
                    BurkinaFaso.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> BurkinaFaso
                    Burundi.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Burundi
                    CaboVerde.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> CaboVerde
                    Cambodia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Cambodia
                    Cameroon.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Cameroon
                    Canada.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Canada
                    CaymanIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> CaymanIslands
                    CentralAfricanRepublic.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> CentralAfricanRepublic
                    Chad.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Chad
                    Chile.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Chile
                    China.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> China
                    ChristmasIsland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> ChristmasIsland
                    CocosIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> CocosIslands
                    Colombia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Colombia
                    Comoros.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Comoros
                    DemocraticRepublicCongo.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> DemocraticRepublicCongo
                    Congo.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Congo
                    CookIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> CookIslands
                    CostaRica.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> CostaRica
                    Croatia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Croatia
                    Cuba.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Cuba
                    Curaçao.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Curaçao
                    Cyprus.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Cyprus
                    Czechia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Czechia
                    IvoryCoast.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> IvoryCoast
                    Denmark.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Denmark
                    Djibouti.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Djibouti
                    Dominica.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Dominica
                    DominicanRepublic.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> DominicanRepublic
                    Ecuador.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Ecuador
                    Egypt.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Egypt
                    ElSalvador.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> ElSalvador
                    EquatorialGuinea.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> EquatorialGuinea
                    Eritrea.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Eritrea
                    Estonia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Estonia
                    Eswatini.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Eswatini
                    Ethiopia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Ethiopia
                    FalklandIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> FalklandIslands
                    FaroeIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> FaroeIslands
                    Fiji.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Fiji
                    Finland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Finland
                    France.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> France
                    FrenchGuiana.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> FrenchGuiana
                    FrenchPolynesia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> FrenchPolynesia
                    FrenchSouthernTerritories.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> FrenchSouthernTerritories
                    Gabon.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Gabon
                    Gambia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Gambia
                    Georgia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Georgia
                    Germany.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Germany
                    Ghana.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Ghana
                    Gibraltar.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Gibraltar
                    Greece.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Greece
                    Greenland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Greenland
                    Grenada.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Grenada
                    Guadeloupe.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Guadeloupe
                    Guam.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Guam
                    Guatemala.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Guatemala
                    Guernsey.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Guernsey
                    Guinea.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Guinea
                    GuineaBissau.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> GuineaBissau
                    Guyana.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Guyana
                    Haiti.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Haiti
                    HeardIslandAndMcDonaldIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> HeardIslandAndMcDonaldIslands
                    HolySee.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> HolySee
                    Honduras.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Honduras
                    HongKong.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> HongKong
                    Hungary.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Hungary
                    Iceland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Iceland
                    India.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> India
                    Indonesia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Indonesia
                    Iran.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Iran
                    Iraq.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Iraq
                    Ireland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Ireland
                    IsleOfMan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> IsleOfMan
                    Israel.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Israel
                    Italy.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Italy
                    Jamaica.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Jamaica
                    Japan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Japan
                    Jersey.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Jersey
                    Jordan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Jordan
                    Kazakhstan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Kazakhstan
                    Kenya.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Kenya
                    Kiribati.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Kiribati
                    NorthKorea.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> NorthKorea
                    SouthKorea.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SouthKorea
                    Kuwait.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Kuwait
                    Kyrgyzstan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Kyrgyzstan
                    Lao.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Lao
                    Latvia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Latvia
                    Lebanon.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Lebanon
                    Lesotho.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Lesotho
                    Liberia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Liberia
                    Libya.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Libya
                    Liechtenstein.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Liechtenstein
                    Lithuania.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Lithuania
                    Luxembourg.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Luxembourg
                    Macao.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Macao
                    Madagascar.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Madagascar
                    Malawi.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Malawi
                    Malaysia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Malaysia
                    Maldives.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Maldives
                    Mali.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mali
                    Malta.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Malta
                    MarshallIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> MarshallIslands
                    Martinique.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Martinique
                    Mauritania.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mauritania
                    Mauritius.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mauritius
                    Mayotte.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mayotte
                    Mexico.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mexico
                    Micronesia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Micronesia
                    Moldova.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Moldova
                    Monaco.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Monaco
                    Mongolia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mongolia
                    Montenegro.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Montenegro
                    Montserrat.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Montserrat
                    Morocco.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Morocco
                    Mozambique.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Mozambique
                    Myanmar.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Myanmar
                    Namibia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Namibia
                    Nauru.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Nauru
                    Nepal.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Nepal
                    Netherlands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Netherlands
                    NewCaledonia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> NewCaledonia
                    NewZealand.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> NewZealand
                    Nicaragua.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Nicaragua
                    Niger.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Niger
                    Nigeria.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Nigeria
                    Niue.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Niue
                    NorfolkIsland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> NorfolkIsland
                    NorthMacedonia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> NorthMacedonia
                    NorthernMarianaIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> NorthernMarianaIslands
                    Norway.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Norway
                    Oman.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Oman
                    Pakistan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Pakistan
                    Palau.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Palau
                    Palestine.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Palestine
                    Panama.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Panama
                    PapuaNewGuinea.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> PapuaNewGuinea
                    Paraguay.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Paraguay
                    Peru.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Peru
                    Philippines.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Philippines
                    Pitcairn.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Pitcairn
                    Poland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Poland
                    Portugal.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Portugal
                    PuertoRico.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> PuertoRico
                    Qatar.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Qatar
                    Romania.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Romania
                    RussianFederation.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> RussianFederation
                    Rwanda.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Rwanda
                    Réunion.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Réunion
                    SaintBarthélemy.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintBarthélemy
                    SaintHelena.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintHelena
                    SaintKittsAndNevis.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintKittsAndNevis
                    SaintLucia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintLucia
                    SaintMartin.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintMartin
                    SaintPierreAndMiquelon.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintPierreAndMiquelon
                    SaintVincentAndTheGrenadines.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaintVincentAndTheGrenadines
                    Samoa.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Samoa
                    SaoTomeAndPrincipe.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaoTomeAndPrincipe
                    SaudiArabia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SaudiArabia
                    Senegal.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Senegal
                    Serbia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Serbia
                    Seychelles.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Seychelles
                    SierraLeone.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SierraLeone
                    Singapore.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Singapore
                    SintMaarten.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SintMaarten
                    Slovakia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Slovakia
                    Slovenia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Slovenia
                    SolomonIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SolomonIslands
                    SouthAfrica.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SouthAfrica
                    SouthGeorgiaAndTheSouthSandwichIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SouthGeorgiaAndTheSouthSandwichIslands
                    SouthSudan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SouthSudan
                    Spain.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Spain
                    SriLanka.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SriLanka
                    Sudan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Sudan
                    Suriname.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Suriname
                    SvalbardAndJanMayen.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> SvalbardAndJanMayen
                    Sweden.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Sweden
                    Switzerland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Switzerland
                    Syrian.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Syrian
                    Taiwan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Taiwan
                    Tajikistan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Tajikistan
                    Tanzania.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Tanzania
                    Thailand.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Thailand
                    TimorLeste.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> TimorLeste
                    Togo.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Togo
                    Tokelau.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Tokelau
                    Tonga.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Tonga
                    TrinidadAndTobago.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> TrinidadAndTobago
                    Tunisia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Tunisia
                    Turkmenistan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Turkmenistan
                    TurksAndCaicosIsland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> TurksAndCaicosIsland
                    Tuvalu.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Tuvalu
                    Türkiye.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Türkiye
                    Uganda.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Uganda
                    Ukraine.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Ukraine
                    UnitedArabEmirates.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> UnitedArabEmirates
                    UnitedKingdomOfGreatBritainAndNorthernIreland.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> UnitedKingdomOfGreatBritainAndNorthernIreland
                    UnitedStatesMinorOutlyingIslands.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> UnitedStatesMinorOutlyingIslands
                    UnitedStatesOfAmerica.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> UnitedStatesOfAmerica
                    Uruguay.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Uruguay
                    Uzbekistan.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Uzbekistan
                    Vanuatu.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Vanuatu
                    Venezuela.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Venezuela
                    VietNam.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> VietNam
                    VirginIslandsBritish.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> VirginIslandsBritish
                    VirginIslandsUS.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> VirginIslandsUS
                    WallisAndFutuna.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> WallisAndFutuna
                    WesternSahara.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> WesternSahara
                    Yemen.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Yemen
                    Zambia.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Zambia
                    Zimbabwe.codeAlpha2.contentEquals(validCode, ignoreCase = true) -> Zimbabwe
                    else -> null

                }
            }

            fun parseAlpha3(): Country? {
                val validCode = trimmedCode.takeIf(Code.Alpha3::isValidFormat)
                return when {
                    validCode == null -> null // make sure it doesn't check other types
                    Afghanistan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Afghanistan
                    Albania.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Albania
                    Algeria.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Algeria
                    AmericanSamoa.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> AmericanSamoa
                    Andorra.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Andorra
                    Angola.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Angola
                    Anguilla.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Anguilla
                    Antarctica.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Antarctica
                    AntiguaAndBarbuda.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> AntiguaAndBarbuda
                    Argentina.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Argentina
                    Armenia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Armenia
                    Aruba.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Aruba
                    Australia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Australia
                    Austria.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Austria
                    Azerbaijan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Azerbaijan
                    Bahamas.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bahamas
                    Bahrain.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bahrain
                    Bangladesh.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bangladesh
                    Barbados.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Barbados
                    Belarus.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Belarus
                    Belgium.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Belgium
                    Belize.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Belize
                    Benin.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Benin
                    Bermuda.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bermuda
                    ÅlandIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> ÅlandIslands
                    Bhutan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bhutan
                    Bolivia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bolivia
                    Bonaire.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bonaire
                    BosniaAndHerzegovina.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> BosniaAndHerzegovina
                    Botswana.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Botswana
                    BouvetIsland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> BouvetIsland
                    Brazil.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Brazil
                    BritishIndianOceanTerritory.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> BritishIndianOceanTerritory
                    BruneiDarussalam.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> BruneiDarussalam
                    Bulgaria.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Bulgaria
                    BurkinaFaso.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> BurkinaFaso
                    Burundi.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Burundi
                    CaboVerde.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> CaboVerde
                    Cambodia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Cambodia
                    Cameroon.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Cameroon
                    Canada.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Canada
                    CaymanIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> CaymanIslands
                    CentralAfricanRepublic.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> CentralAfricanRepublic
                    Chad.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Chad
                    Chile.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Chile
                    China.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> China
                    ChristmasIsland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> ChristmasIsland
                    CocosIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> CocosIslands
                    Colombia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Colombia
                    Comoros.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Comoros
                    DemocraticRepublicCongo.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> DemocraticRepublicCongo
                    Congo.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Congo
                    CookIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> CookIslands
                    CostaRica.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> CostaRica
                    Croatia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Croatia
                    Cuba.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Cuba
                    Curaçao.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Curaçao
                    Cyprus.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Cyprus
                    Czechia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Czechia
                    IvoryCoast.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> IvoryCoast
                    Denmark.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Denmark
                    Djibouti.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Djibouti
                    Dominica.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Dominica
                    DominicanRepublic.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> DominicanRepublic
                    Ecuador.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Ecuador
                    Egypt.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Egypt
                    ElSalvador.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> ElSalvador
                    EquatorialGuinea.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> EquatorialGuinea
                    Eritrea.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Eritrea
                    Estonia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Estonia
                    Eswatini.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Eswatini
                    Ethiopia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Ethiopia
                    FalklandIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> FalklandIslands
                    FaroeIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> FaroeIslands
                    Fiji.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Fiji
                    Finland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Finland
                    France.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> France
                    FrenchGuiana.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> FrenchGuiana
                    FrenchPolynesia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> FrenchPolynesia
                    FrenchSouthernTerritories.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> FrenchSouthernTerritories
                    Gabon.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Gabon
                    Gambia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Gambia
                    Georgia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Georgia
                    Germany.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Germany
                    Ghana.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Ghana
                    Gibraltar.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Gibraltar
                    Greece.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Greece
                    Greenland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Greenland
                    Grenada.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Grenada
                    Guadeloupe.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Guadeloupe
                    Guam.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Guam
                    Guatemala.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Guatemala
                    Guernsey.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Guernsey
                    Guinea.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Guinea
                    GuineaBissau.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> GuineaBissau
                    Guyana.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Guyana
                    Haiti.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Haiti
                    HeardIslandAndMcDonaldIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> HeardIslandAndMcDonaldIslands
                    HolySee.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> HolySee
                    Honduras.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Honduras
                    HongKong.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> HongKong
                    Hungary.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Hungary
                    Iceland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Iceland
                    India.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> India
                    Indonesia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Indonesia
                    Iran.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Iran
                    Iraq.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Iraq
                    Ireland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Ireland
                    IsleOfMan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> IsleOfMan
                    Israel.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Israel
                    Italy.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Italy
                    Jamaica.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Jamaica
                    Japan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Japan
                    Jersey.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Jersey
                    Jordan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Jordan
                    Kazakhstan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Kazakhstan
                    Kenya.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Kenya
                    Kiribati.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Kiribati
                    NorthKorea.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> NorthKorea
                    SouthKorea.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SouthKorea
                    Kuwait.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Kuwait
                    Kyrgyzstan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Kyrgyzstan
                    Lao.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Lao
                    Latvia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Latvia
                    Lebanon.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Lebanon
                    Lesotho.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Lesotho
                    Liberia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Liberia
                    Libya.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Libya
                    Liechtenstein.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Liechtenstein
                    Lithuania.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Lithuania
                    Luxembourg.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Luxembourg
                    Macao.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Macao
                    Madagascar.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Madagascar
                    Malawi.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Malawi
                    Malaysia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Malaysia
                    Maldives.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Maldives
                    Mali.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mali
                    Malta.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Malta
                    MarshallIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> MarshallIslands
                    Martinique.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Martinique
                    Mauritania.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mauritania
                    Mauritius.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mauritius
                    Mayotte.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mayotte
                    Mexico.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mexico
                    Micronesia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Micronesia
                    Moldova.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Moldova
                    Monaco.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Monaco
                    Mongolia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mongolia
                    Montenegro.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Montenegro
                    Montserrat.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Montserrat
                    Morocco.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Morocco
                    Mozambique.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Mozambique
                    Myanmar.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Myanmar
                    Namibia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Namibia
                    Nauru.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Nauru
                    Nepal.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Nepal
                    Netherlands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Netherlands
                    NewCaledonia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> NewCaledonia
                    NewZealand.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> NewZealand
                    Nicaragua.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Nicaragua
                    Niger.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Niger
                    Nigeria.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Nigeria
                    Niue.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Niue
                    NorfolkIsland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> NorfolkIsland
                    NorthMacedonia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> NorthMacedonia
                    NorthernMarianaIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> NorthernMarianaIslands
                    Norway.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Norway
                    Oman.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Oman
                    Pakistan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Pakistan
                    Palau.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Palau
                    Palestine.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Palestine
                    Panama.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Panama
                    PapuaNewGuinea.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> PapuaNewGuinea
                    Paraguay.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Paraguay
                    Peru.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Peru
                    Philippines.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Philippines
                    Pitcairn.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Pitcairn
                    Poland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Poland
                    Portugal.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Portugal
                    PuertoRico.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> PuertoRico
                    Qatar.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Qatar
                    Romania.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Romania
                    RussianFederation.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> RussianFederation
                    Rwanda.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Rwanda
                    Réunion.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Réunion
                    SaintBarthélemy.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintBarthélemy
                    SaintHelena.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintHelena
                    SaintKittsAndNevis.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintKittsAndNevis
                    SaintLucia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintLucia
                    SaintMartin.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintMartin
                    SaintPierreAndMiquelon.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintPierreAndMiquelon
                    SaintVincentAndTheGrenadines.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaintVincentAndTheGrenadines
                    Samoa.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Samoa
                    SaoTomeAndPrincipe.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaoTomeAndPrincipe
                    SaudiArabia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SaudiArabia
                    Senegal.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Senegal
                    Serbia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Serbia
                    Seychelles.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Seychelles
                    SierraLeone.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SierraLeone
                    Singapore.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Singapore
                    SintMaarten.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SintMaarten
                    Slovakia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Slovakia
                    Slovenia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Slovenia
                    SolomonIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SolomonIslands
                    SouthAfrica.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SouthAfrica
                    SouthGeorgiaAndTheSouthSandwichIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SouthGeorgiaAndTheSouthSandwichIslands
                    SouthSudan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SouthSudan
                    Spain.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Spain
                    SriLanka.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SriLanka
                    Sudan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Sudan
                    Suriname.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Suriname
                    SvalbardAndJanMayen.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> SvalbardAndJanMayen
                    Sweden.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Sweden
                    Switzerland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Switzerland
                    Syrian.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Syrian
                    Taiwan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Taiwan
                    Tajikistan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Tajikistan
                    Tanzania.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Tanzania
                    Thailand.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Thailand
                    TimorLeste.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> TimorLeste
                    Togo.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Togo
                    Tokelau.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Tokelau
                    Tonga.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Tonga
                    TrinidadAndTobago.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> TrinidadAndTobago
                    Tunisia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Tunisia
                    Turkmenistan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Turkmenistan
                    TurksAndCaicosIsland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> TurksAndCaicosIsland
                    Tuvalu.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Tuvalu
                    Türkiye.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Türkiye
                    Uganda.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Uganda
                    Ukraine.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Ukraine
                    UnitedArabEmirates.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> UnitedArabEmirates
                    UnitedKingdomOfGreatBritainAndNorthernIreland.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> UnitedKingdomOfGreatBritainAndNorthernIreland
                    UnitedStatesMinorOutlyingIslands.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> UnitedStatesMinorOutlyingIslands
                    UnitedStatesOfAmerica.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> UnitedStatesOfAmerica
                    Uruguay.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Uruguay
                    Uzbekistan.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Uzbekistan
                    Vanuatu.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Vanuatu
                    Venezuela.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Venezuela
                    VietNam.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> VietNam
                    VirginIslandsBritish.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> VirginIslandsBritish
                    VirginIslandsUS.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> VirginIslandsUS
                    WallisAndFutuna.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> WallisAndFutuna
                    WesternSahara.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> WesternSahara
                    Yemen.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Yemen
                    Zambia.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Zambia
                    Zimbabwe.codeAlpha3.contentEquals(validCode, ignoreCase = true) -> Zimbabwe
                    else -> null
                }
            }

            return trimmedCode.toString().toIntOrNull()?.let(::forCodeOrNull) ?: parseAlpha3() ?: parseAlpha2()
        }

        /**
         * Parse numeric country code to a country object.
         *
         * @param code country code following numeric [ISO 3166 Format][Code.Numeric]
         * @return [Country] object or null
         */
        @JvmStatic
        fun forCodeOrNull(code: Int): Country? {
            return when (code.takeIf(Code.Numeric::isValidFormat)) {
                null -> null // make sure it doesn't check other types
                Afghanistan.codeNumeric.toInt() -> Afghanistan
                Albania.codeNumeric.toInt() -> Albania
                Algeria.codeNumeric.toInt() -> Algeria
                AmericanSamoa.codeNumeric.toInt() -> AmericanSamoa
                Andorra.codeNumeric.toInt() -> Andorra
                Angola.codeNumeric.toInt() -> Angola
                Anguilla.codeNumeric.toInt() -> Anguilla
                Antarctica.codeNumeric.toInt() -> Antarctica
                AntiguaAndBarbuda.codeNumeric.toInt() -> AntiguaAndBarbuda
                Argentina.codeNumeric.toInt() -> Argentina
                Armenia.codeNumeric.toInt() -> Armenia
                Aruba.codeNumeric.toInt() -> Aruba
                Australia.codeNumeric.toInt() -> Australia
                Austria.codeNumeric.toInt() -> Austria
                Azerbaijan.codeNumeric.toInt() -> Azerbaijan
                Bahamas.codeNumeric.toInt() -> Bahamas
                Bahrain.codeNumeric.toInt() -> Bahrain
                Bangladesh.codeNumeric.toInt() -> Bangladesh
                Barbados.codeNumeric.toInt() -> Barbados
                Belarus.codeNumeric.toInt() -> Belarus
                Belgium.codeNumeric.toInt() -> Belgium
                Belize.codeNumeric.toInt() -> Belize
                Benin.codeNumeric.toInt() -> Benin
                Bermuda.codeNumeric.toInt() -> Bermuda
                ÅlandIslands.codeNumeric.toInt() -> ÅlandIslands
                Bhutan.codeNumeric.toInt() -> Bhutan
                Bolivia.codeNumeric.toInt() -> Bolivia
                Bonaire.codeNumeric.toInt() -> Bonaire
                BosniaAndHerzegovina.codeNumeric.toInt() -> BosniaAndHerzegovina
                Botswana.codeNumeric.toInt() -> Botswana
                BouvetIsland.codeNumeric.toInt() -> BouvetIsland
                Brazil.codeNumeric.toInt() -> Brazil
                BritishIndianOceanTerritory.codeNumeric.toInt() -> BritishIndianOceanTerritory
                BruneiDarussalam.codeNumeric.toInt() -> BruneiDarussalam
                Bulgaria.codeNumeric.toInt() -> Bulgaria
                BurkinaFaso.codeNumeric.toInt() -> BurkinaFaso
                Burundi.codeNumeric.toInt() -> Burundi
                CaboVerde.codeNumeric.toInt() -> CaboVerde
                Cambodia.codeNumeric.toInt() -> Cambodia
                Cameroon.codeNumeric.toInt() -> Cameroon
                Canada.codeNumeric.toInt() -> Canada
                CaymanIslands.codeNumeric.toInt() -> CaymanIslands
                CentralAfricanRepublic.codeNumeric.toInt() -> CentralAfricanRepublic
                Chad.codeNumeric.toInt() -> Chad
                Chile.codeNumeric.toInt() -> Chile
                China.codeNumeric.toInt() -> China
                ChristmasIsland.codeNumeric.toInt() -> ChristmasIsland
                CocosIslands.codeNumeric.toInt() -> CocosIslands
                Colombia.codeNumeric.toInt() -> Colombia
                Comoros.codeNumeric.toInt() -> Comoros
                DemocraticRepublicCongo.codeNumeric.toInt() -> DemocraticRepublicCongo
                Congo.codeNumeric.toInt() -> Congo
                CookIslands.codeNumeric.toInt() -> CookIslands
                CostaRica.codeNumeric.toInt() -> CostaRica
                Croatia.codeNumeric.toInt() -> Croatia
                Cuba.codeNumeric.toInt() -> Cuba
                Curaçao.codeNumeric.toInt() -> Curaçao
                Cyprus.codeNumeric.toInt() -> Cyprus
                Czechia.codeNumeric.toInt() -> Czechia
                IvoryCoast.codeNumeric.toInt() -> IvoryCoast
                Denmark.codeNumeric.toInt() -> Denmark
                Djibouti.codeNumeric.toInt() -> Djibouti
                Dominica.codeNumeric.toInt() -> Dominica
                DominicanRepublic.codeNumeric.toInt() -> DominicanRepublic
                Ecuador.codeNumeric.toInt() -> Ecuador
                Egypt.codeNumeric.toInt() -> Egypt
                ElSalvador.codeNumeric.toInt() -> ElSalvador
                EquatorialGuinea.codeNumeric.toInt() -> EquatorialGuinea
                Eritrea.codeNumeric.toInt() -> Eritrea
                Estonia.codeNumeric.toInt() -> Estonia
                Eswatini.codeNumeric.toInt() -> Eswatini
                Ethiopia.codeNumeric.toInt() -> Ethiopia
                FalklandIslands.codeNumeric.toInt() -> FalklandIslands
                FaroeIslands.codeNumeric.toInt() -> FaroeIslands
                Fiji.codeNumeric.toInt() -> Fiji
                Finland.codeNumeric.toInt() -> Finland
                France.codeNumeric.toInt() -> France
                FrenchGuiana.codeNumeric.toInt() -> FrenchGuiana
                FrenchPolynesia.codeNumeric.toInt() -> FrenchPolynesia
                FrenchSouthernTerritories.codeNumeric.toInt() -> FrenchSouthernTerritories
                Gabon.codeNumeric.toInt() -> Gabon
                Gambia.codeNumeric.toInt() -> Gambia
                Georgia.codeNumeric.toInt() -> Georgia
                Germany.codeNumeric.toInt() -> Germany
                Ghana.codeNumeric.toInt() -> Ghana
                Gibraltar.codeNumeric.toInt() -> Gibraltar
                Greece.codeNumeric.toInt() -> Greece
                Greenland.codeNumeric.toInt() -> Greenland
                Grenada.codeNumeric.toInt() -> Grenada
                Guadeloupe.codeNumeric.toInt() -> Guadeloupe
                Guam.codeNumeric.toInt() -> Guam
                Guatemala.codeNumeric.toInt() -> Guatemala
                Guernsey.codeNumeric.toInt() -> Guernsey
                Guinea.codeNumeric.toInt() -> Guinea
                GuineaBissau.codeNumeric.toInt() -> GuineaBissau
                Guyana.codeNumeric.toInt() -> Guyana
                Haiti.codeNumeric.toInt() -> Haiti
                HeardIslandAndMcDonaldIslands.codeNumeric.toInt() -> HeardIslandAndMcDonaldIslands
                HolySee.codeNumeric.toInt() -> HolySee
                Honduras.codeNumeric.toInt() -> Honduras
                HongKong.codeNumeric.toInt() -> HongKong
                Hungary.codeNumeric.toInt() -> Hungary
                Iceland.codeNumeric.toInt() -> Iceland
                India.codeNumeric.toInt() -> India
                Indonesia.codeNumeric.toInt() -> Indonesia
                Iran.codeNumeric.toInt() -> Iran
                Iraq.codeNumeric.toInt() -> Iraq
                Ireland.codeNumeric.toInt() -> Ireland
                IsleOfMan.codeNumeric.toInt() -> IsleOfMan
                Israel.codeNumeric.toInt() -> Israel
                Italy.codeNumeric.toInt() -> Italy
                Jamaica.codeNumeric.toInt() -> Jamaica
                Japan.codeNumeric.toInt() -> Japan
                Jersey.codeNumeric.toInt() -> Jersey
                Jordan.codeNumeric.toInt() -> Jordan
                Kazakhstan.codeNumeric.toInt() -> Kazakhstan
                Kenya.codeNumeric.toInt() -> Kenya
                Kiribati.codeNumeric.toInt() -> Kiribati
                NorthKorea.codeNumeric.toInt() -> NorthKorea
                SouthKorea.codeNumeric.toInt() -> SouthKorea
                Kuwait.codeNumeric.toInt() -> Kuwait
                Kyrgyzstan.codeNumeric.toInt() -> Kyrgyzstan
                Lao.codeNumeric.toInt() -> Lao
                Latvia.codeNumeric.toInt() -> Latvia
                Lebanon.codeNumeric.toInt() -> Lebanon
                Lesotho.codeNumeric.toInt() -> Lesotho
                Liberia.codeNumeric.toInt() -> Liberia
                Libya.codeNumeric.toInt() -> Libya
                Liechtenstein.codeNumeric.toInt() -> Liechtenstein
                Lithuania.codeNumeric.toInt() -> Lithuania
                Luxembourg.codeNumeric.toInt() -> Luxembourg
                Macao.codeNumeric.toInt() -> Macao
                Madagascar.codeNumeric.toInt() -> Madagascar
                Malawi.codeNumeric.toInt() -> Malawi
                Malaysia.codeNumeric.toInt() -> Malaysia
                Maldives.codeNumeric.toInt() -> Maldives
                Mali.codeNumeric.toInt() -> Mali
                Malta.codeNumeric.toInt() -> Malta
                MarshallIslands.codeNumeric.toInt() -> MarshallIslands
                Martinique.codeNumeric.toInt() -> Martinique
                Mauritania.codeNumeric.toInt() -> Mauritania
                Mauritius.codeNumeric.toInt() -> Mauritius
                Mayotte.codeNumeric.toInt() -> Mayotte
                Mexico.codeNumeric.toInt() -> Mexico
                Micronesia.codeNumeric.toInt() -> Micronesia
                Moldova.codeNumeric.toInt() -> Moldova
                Monaco.codeNumeric.toInt() -> Monaco
                Mongolia.codeNumeric.toInt() -> Mongolia
                Montenegro.codeNumeric.toInt() -> Montenegro
                Montserrat.codeNumeric.toInt() -> Montserrat
                Morocco.codeNumeric.toInt() -> Morocco
                Mozambique.codeNumeric.toInt() -> Mozambique
                Myanmar.codeNumeric.toInt() -> Myanmar
                Namibia.codeNumeric.toInt() -> Namibia
                Nauru.codeNumeric.toInt() -> Nauru
                Nepal.codeNumeric.toInt() -> Nepal
                Netherlands.codeNumeric.toInt() -> Netherlands
                NewCaledonia.codeNumeric.toInt() -> NewCaledonia
                NewZealand.codeNumeric.toInt() -> NewZealand
                Nicaragua.codeNumeric.toInt() -> Nicaragua
                Niger.codeNumeric.toInt() -> Niger
                Nigeria.codeNumeric.toInt() -> Nigeria
                Niue.codeNumeric.toInt() -> Niue
                NorfolkIsland.codeNumeric.toInt() -> NorfolkIsland
                NorthMacedonia.codeNumeric.toInt() -> NorthMacedonia
                NorthernMarianaIslands.codeNumeric.toInt() -> NorthernMarianaIslands
                Norway.codeNumeric.toInt() -> Norway
                Oman.codeNumeric.toInt() -> Oman
                Pakistan.codeNumeric.toInt() -> Pakistan
                Palau.codeNumeric.toInt() -> Palau
                Palestine.codeNumeric.toInt() -> Palestine
                Panama.codeNumeric.toInt() -> Panama
                PapuaNewGuinea.codeNumeric.toInt() -> PapuaNewGuinea
                Paraguay.codeNumeric.toInt() -> Paraguay
                Peru.codeNumeric.toInt() -> Peru
                Philippines.codeNumeric.toInt() -> Philippines
                Pitcairn.codeNumeric.toInt() -> Pitcairn
                Poland.codeNumeric.toInt() -> Poland
                Portugal.codeNumeric.toInt() -> Portugal
                PuertoRico.codeNumeric.toInt() -> PuertoRico
                Qatar.codeNumeric.toInt() -> Qatar
                Romania.codeNumeric.toInt() -> Romania
                RussianFederation.codeNumeric.toInt() -> RussianFederation
                Rwanda.codeNumeric.toInt() -> Rwanda
                Réunion.codeNumeric.toInt() -> Réunion
                SaintBarthélemy.codeNumeric.toInt() -> SaintBarthélemy
                SaintHelena.codeNumeric.toInt() -> SaintHelena
                SaintKittsAndNevis.codeNumeric.toInt() -> SaintKittsAndNevis
                SaintLucia.codeNumeric.toInt() -> SaintLucia
                SaintMartin.codeNumeric.toInt() -> SaintMartin
                SaintPierreAndMiquelon.codeNumeric.toInt() -> SaintPierreAndMiquelon
                SaintVincentAndTheGrenadines.codeNumeric.toInt() -> SaintVincentAndTheGrenadines
                Samoa.codeNumeric.toInt() -> Samoa
                SaoTomeAndPrincipe.codeNumeric.toInt() -> SaoTomeAndPrincipe
                SaudiArabia.codeNumeric.toInt() -> SaudiArabia
                Senegal.codeNumeric.toInt() -> Senegal
                Serbia.codeNumeric.toInt() -> Serbia
                Seychelles.codeNumeric.toInt() -> Seychelles
                SierraLeone.codeNumeric.toInt() -> SierraLeone
                Singapore.codeNumeric.toInt() -> Singapore
                SintMaarten.codeNumeric.toInt() -> SintMaarten
                Slovakia.codeNumeric.toInt() -> Slovakia
                Slovenia.codeNumeric.toInt() -> Slovenia
                SolomonIslands.codeNumeric.toInt() -> SolomonIslands
                SouthAfrica.codeNumeric.toInt() -> SouthAfrica
                SouthGeorgiaAndTheSouthSandwichIslands.codeNumeric.toInt() -> SouthGeorgiaAndTheSouthSandwichIslands
                SouthSudan.codeNumeric.toInt() -> SouthSudan
                Spain.codeNumeric.toInt() -> Spain
                SriLanka.codeNumeric.toInt() -> SriLanka
                Sudan.codeNumeric.toInt() -> Sudan
                Suriname.codeNumeric.toInt() -> Suriname
                SvalbardAndJanMayen.codeNumeric.toInt() -> SvalbardAndJanMayen
                Sweden.codeNumeric.toInt() -> Sweden
                Switzerland.codeNumeric.toInt() -> Switzerland
                Syrian.codeNumeric.toInt() -> Syrian
                Taiwan.codeNumeric.toInt() -> Taiwan
                Tajikistan.codeNumeric.toInt() -> Tajikistan
                Tanzania.codeNumeric.toInt() -> Tanzania
                Thailand.codeNumeric.toInt() -> Thailand
                TimorLeste.codeNumeric.toInt() -> TimorLeste
                Togo.codeNumeric.toInt() -> Togo
                Tokelau.codeNumeric.toInt() -> Tokelau
                Tonga.codeNumeric.toInt() -> Tonga
                TrinidadAndTobago.codeNumeric.toInt() -> TrinidadAndTobago
                Tunisia.codeNumeric.toInt() -> Tunisia
                Turkmenistan.codeNumeric.toInt() -> Turkmenistan
                TurksAndCaicosIsland.codeNumeric.toInt() -> TurksAndCaicosIsland
                Tuvalu.codeNumeric.toInt() -> Tuvalu
                Türkiye.codeNumeric.toInt() -> Türkiye
                Uganda.codeNumeric.toInt() -> Uganda
                Ukraine.codeNumeric.toInt() -> Ukraine
                UnitedArabEmirates.codeNumeric.toInt() -> UnitedArabEmirates
                UnitedKingdomOfGreatBritainAndNorthernIreland.codeNumeric.toInt() -> UnitedKingdomOfGreatBritainAndNorthernIreland
                UnitedStatesMinorOutlyingIslands.codeNumeric.toInt() -> UnitedStatesMinorOutlyingIslands
                UnitedStatesOfAmerica.codeNumeric.toInt() -> UnitedStatesOfAmerica
                Uruguay.codeNumeric.toInt() -> Uruguay
                Uzbekistan.codeNumeric.toInt() -> Uzbekistan
                Vanuatu.codeNumeric.toInt() -> Vanuatu
                Venezuela.codeNumeric.toInt() -> Venezuela
                VietNam.codeNumeric.toInt() -> VietNam
                VirginIslandsBritish.codeNumeric.toInt() -> VirginIslandsBritish
                VirginIslandsUS.codeNumeric.toInt() -> VirginIslandsUS
                WallisAndFutuna.codeNumeric.toInt() -> WallisAndFutuna
                WesternSahara.codeNumeric.toInt() -> WesternSahara
                Yemen.codeNumeric.toInt() -> Yemen
                Zambia.codeNumeric.toInt() -> Zambia
                Zimbabwe.codeNumeric.toInt() -> Zimbabwe
                else -> null
            }
        }
    }
}
