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
                return when (trimmedCode.takeIf(Code.Alpha2::isValidFormat)) {
                    null -> null // make sure it doesn't check other types
                    Afghanistan.codeAlpha2 -> Afghanistan
                    Albania.codeAlpha2 -> Albania
                    Algeria.codeAlpha2 -> Algeria
                    AmericanSamoa.codeAlpha2 -> AmericanSamoa
                    Andorra.codeAlpha2 -> Andorra
                    Angola.codeAlpha2 -> Angola
                    Anguilla.codeAlpha2 -> Anguilla
                    Antarctica.codeAlpha2 -> Antarctica
                    AntiguaAndBarbuda.codeAlpha2 -> AntiguaAndBarbuda
                    Argentina.codeAlpha2 -> Argentina
                    Armenia.codeAlpha2 -> Armenia
                    Aruba.codeAlpha2 -> Aruba
                    Australia.codeAlpha2 -> Australia
                    Austria.codeAlpha2 -> Austria
                    Azerbaijan.codeAlpha2 -> Azerbaijan
                    Bahamas.codeAlpha2 -> Bahamas
                    Bahrain.codeAlpha2 -> Bahrain
                    Bangladesh.codeAlpha2 -> Bangladesh
                    Barbados.codeAlpha2 -> Barbados
                    Belarus.codeAlpha2 -> Belarus
                    Belgium.codeAlpha2 -> Belgium
                    Belize.codeAlpha2 -> Belize
                    Benin.codeAlpha2 -> Benin
                    Bermuda.codeAlpha2 -> Bermuda
                    ÅlandIslands.codeAlpha2 -> ÅlandIslands
                    Bhutan.codeAlpha2 -> Bhutan
                    Bolivia.codeAlpha2 -> Bolivia
                    Bonaire.codeAlpha2 -> Bonaire
                    BosniaAndHerzegovina.codeAlpha2 -> BosniaAndHerzegovina
                    Botswana.codeAlpha2 -> Botswana
                    BouvetIsland.codeAlpha2 -> BouvetIsland
                    Brazil.codeAlpha2 -> Brazil
                    BritishIndianOceanTerritory.codeAlpha2 -> BritishIndianOceanTerritory
                    BruneiDarussalam.codeAlpha2 -> BruneiDarussalam
                    Bulgaria.codeAlpha2 -> Bulgaria
                    BurkinaFaso.codeAlpha2 -> BurkinaFaso
                    Burundi.codeAlpha2 -> Burundi
                    CaboVerde.codeAlpha2 -> CaboVerde
                    Cambodia.codeAlpha2 -> Cambodia
                    Cameroon.codeAlpha2 -> Cameroon
                    Canada.codeAlpha2 -> Canada
                    CaymanIslands.codeAlpha2 -> CaymanIslands
                    CentralAfricanRepublic.codeAlpha2 -> CentralAfricanRepublic
                    Chad.codeAlpha2 -> Chad
                    Chile.codeAlpha2 -> Chile
                    China.codeAlpha2 -> China
                    ChristmasIsland.codeAlpha2 -> ChristmasIsland
                    CocosIslands.codeAlpha2 -> CocosIslands
                    Colombia.codeAlpha2 -> Colombia
                    Comoros.codeAlpha2 -> Comoros
                    DemocraticRepublicCongo.codeAlpha2 -> DemocraticRepublicCongo
                    Congo.codeAlpha2 -> Congo
                    CookIslands.codeAlpha2 -> CookIslands
                    CostaRica.codeAlpha2 -> CostaRica
                    Croatia.codeAlpha2 -> Croatia
                    Cuba.codeAlpha2 -> Cuba
                    Curaçao.codeAlpha2 -> Curaçao
                    Cyprus.codeAlpha2 -> Cyprus
                    Czechia.codeAlpha2 -> Czechia
                    IvoryCoast.codeAlpha2 -> IvoryCoast
                    Denmark.codeAlpha2 -> Denmark
                    Djibouti.codeAlpha2 -> Djibouti
                    Dominica.codeAlpha2 -> Dominica
                    DominicanRepublic.codeAlpha2 -> DominicanRepublic
                    Ecuador.codeAlpha2 -> Ecuador
                    Egypt.codeAlpha2 -> Egypt
                    ElSalvador.codeAlpha2 -> ElSalvador
                    EquatorialGuinea.codeAlpha2 -> EquatorialGuinea
                    Eritrea.codeAlpha2 -> Eritrea
                    Estonia.codeAlpha2 -> Estonia
                    Eswatini.codeAlpha2 -> Eswatini
                    Ethiopia.codeAlpha2 -> Ethiopia
                    FalklandIslands.codeAlpha2 -> FalklandIslands
                    FaroeIslands.codeAlpha2 -> FaroeIslands
                    Fiji.codeAlpha2 -> Fiji
                    Finland.codeAlpha2 -> Finland
                    France.codeAlpha2 -> France
                    FrenchGuiana.codeAlpha2 -> FrenchGuiana
                    FrenchPolynesia.codeAlpha2 -> FrenchPolynesia
                    FrenchSouthernTerritories.codeAlpha2 -> FrenchSouthernTerritories
                    Gabon.codeAlpha2 -> Gabon
                    Gambia.codeAlpha2 -> Gambia
                    Georgia.codeAlpha2 -> Georgia
                    Germany.codeAlpha2 -> Germany
                    Ghana.codeAlpha2 -> Ghana
                    Gibraltar.codeAlpha2 -> Gibraltar
                    Greece.codeAlpha2 -> Greece
                    Greenland.codeAlpha2 -> Greenland
                    Grenada.codeAlpha2 -> Grenada
                    Guadeloupe.codeAlpha2 -> Guadeloupe
                    Guam.codeAlpha2 -> Guam
                    Guatemala.codeAlpha2 -> Guatemala
                    Guernsey.codeAlpha2 -> Guernsey
                    Guinea.codeAlpha2 -> Guinea
                    GuineaBissau.codeAlpha2 -> GuineaBissau
                    Guyana.codeAlpha2 -> Guyana
                    Haiti.codeAlpha2 -> Haiti
                    HeardIslandAndMcDonaldIslands.codeAlpha2 -> HeardIslandAndMcDonaldIslands
                    HolySee.codeAlpha2 -> HolySee
                    Honduras.codeAlpha2 -> Honduras
                    HongKong.codeAlpha2 -> HongKong
                    Hungary.codeAlpha2 -> Hungary
                    Iceland.codeAlpha2 -> Iceland
                    India.codeAlpha2 -> India
                    Indonesia.codeAlpha2 -> Indonesia
                    Iran.codeAlpha2 -> Iran
                    Iraq.codeAlpha2 -> Iraq
                    Ireland.codeAlpha2 -> Ireland
                    IsleOfMan.codeAlpha2 -> IsleOfMan
                    Israel.codeAlpha2 -> Israel
                    Italy.codeAlpha2 -> Italy
                    Jamaica.codeAlpha2 -> Jamaica
                    Japan.codeAlpha2 -> Japan
                    Jersey.codeAlpha2 -> Jersey
                    Jordan.codeAlpha2 -> Jordan
                    Kazakhstan.codeAlpha2 -> Kazakhstan
                    Kenya.codeAlpha2 -> Kenya
                    Kiribati.codeAlpha2 -> Kiribati
                    NorthKorea.codeAlpha2 -> NorthKorea
                    SouthKorea.codeAlpha2 -> SouthKorea
                    Kuwait.codeAlpha2 -> Kuwait
                    Kyrgyzstan.codeAlpha2 -> Kyrgyzstan
                    Lao.codeAlpha2 -> Lao
                    Latvia.codeAlpha2 -> Latvia
                    Lebanon.codeAlpha2 -> Lebanon
                    Lesotho.codeAlpha2 -> Lesotho
                    Liberia.codeAlpha2 -> Liberia
                    Libya.codeAlpha2 -> Libya
                    Liechtenstein.codeAlpha2 -> Liechtenstein
                    Lithuania.codeAlpha2 -> Lithuania
                    Luxembourg.codeAlpha2 -> Luxembourg
                    Macao.codeAlpha2 -> Macao
                    Madagascar.codeAlpha2 -> Madagascar
                    Malawi.codeAlpha2 -> Malawi
                    Malaysia.codeAlpha2 -> Malaysia
                    Maldives.codeAlpha2 -> Maldives
                    Mali.codeAlpha2 -> Mali
                    Malta.codeAlpha2 -> Malta
                    MarshallIslands.codeAlpha2 -> MarshallIslands
                    Martinique.codeAlpha2 -> Martinique
                    Mauritania.codeAlpha2 -> Mauritania
                    Mauritius.codeAlpha2 -> Mauritius
                    Mayotte.codeAlpha2 -> Mayotte
                    Mexico.codeAlpha2 -> Mexico
                    Micronesia.codeAlpha2 -> Micronesia
                    Moldova.codeAlpha2 -> Moldova
                    Monaco.codeAlpha2 -> Monaco
                    Mongolia.codeAlpha2 -> Mongolia
                    Montenegro.codeAlpha2 -> Montenegro
                    Montserrat.codeAlpha2 -> Montserrat
                    Morocco.codeAlpha2 -> Morocco
                    Mozambique.codeAlpha2 -> Mozambique
                    Myanmar.codeAlpha2 -> Myanmar
                    Namibia.codeAlpha2 -> Namibia
                    Nauru.codeAlpha2 -> Nauru
                    Nepal.codeAlpha2 -> Nepal
                    Netherlands.codeAlpha2 -> Netherlands
                    NewCaledonia.codeAlpha2 -> NewCaledonia
                    NewZealand.codeAlpha2 -> NewZealand
                    Nicaragua.codeAlpha2 -> Nicaragua
                    Niger.codeAlpha2 -> Niger
                    Nigeria.codeAlpha2 -> Nigeria
                    Niue.codeAlpha2 -> Niue
                    NorfolkIsland.codeAlpha2 -> NorfolkIsland
                    NorthMacedonia.codeAlpha2 -> NorthMacedonia
                    NorthernMarianaIslands.codeAlpha2 -> NorthernMarianaIslands
                    Norway.codeAlpha2 -> Norway
                    Oman.codeAlpha2 -> Oman
                    Pakistan.codeAlpha2 -> Pakistan
                    Palau.codeAlpha2 -> Palau
                    Palestine.codeAlpha2 -> Palestine
                    Panama.codeAlpha2 -> Panama
                    PapuaNewGuinea.codeAlpha2 -> PapuaNewGuinea
                    Paraguay.codeAlpha2 -> Paraguay
                    Peru.codeAlpha2 -> Peru
                    Philippines.codeAlpha2 -> Philippines
                    Pitcairn.codeAlpha2 -> Pitcairn
                    Poland.codeAlpha2 -> Poland
                    Portugal.codeAlpha2 -> Portugal
                    PuertoRico.codeAlpha2 -> PuertoRico
                    Qatar.codeAlpha2 -> Qatar
                    Romania.codeAlpha2 -> Romania
                    RussianFederation.codeAlpha2 -> RussianFederation
                    Rwanda.codeAlpha2 -> Rwanda
                    Réunion.codeAlpha2 -> Réunion
                    SaintBarthélemy.codeAlpha2 -> SaintBarthélemy
                    SaintHelena.codeAlpha2 -> SaintHelena
                    SaintKittsAndNevis.codeAlpha2 -> SaintKittsAndNevis
                    SaintLucia.codeAlpha2 -> SaintLucia
                    SaintMartin.codeAlpha2 -> SaintMartin
                    SaintPierreAndMiquelon.codeAlpha2 -> SaintPierreAndMiquelon
                    SaintVincentAndTheGrenadines.codeAlpha2 -> SaintVincentAndTheGrenadines
                    Samoa.codeAlpha2 -> Samoa
                    SaoTomeAndPrincipe.codeAlpha2 -> SaoTomeAndPrincipe
                    SaudiArabia.codeAlpha2 -> SaudiArabia
                    Senegal.codeAlpha2 -> Senegal
                    Serbia.codeAlpha2 -> Serbia
                    Seychelles.codeAlpha2 -> Seychelles
                    SierraLeone.codeAlpha2 -> SierraLeone
                    Singapore.codeAlpha2 -> Singapore
                    SintMaarten.codeAlpha2 -> SintMaarten
                    Slovakia.codeAlpha2 -> Slovakia
                    Slovenia.codeAlpha2 -> Slovenia
                    SolomonIslands.codeAlpha2 -> SolomonIslands
                    SouthAfrica.codeAlpha2 -> SouthAfrica
                    SouthGeorgiaAndTheSouthSandwichIslands.codeAlpha2 -> SouthGeorgiaAndTheSouthSandwichIslands
                    SouthSudan.codeAlpha2 -> SouthSudan
                    Spain.codeAlpha2 -> Spain
                    SriLanka.codeAlpha2 -> SriLanka
                    Sudan.codeAlpha2 -> Sudan
                    Suriname.codeAlpha2 -> Suriname
                    SvalbardAndJanMayen.codeAlpha2 -> SvalbardAndJanMayen
                    Sweden.codeAlpha2 -> Sweden
                    Switzerland.codeAlpha2 -> Switzerland
                    Syrian.codeAlpha2 -> Syrian
                    Taiwan.codeAlpha2 -> Taiwan
                    Tajikistan.codeAlpha2 -> Tajikistan
                    Tanzania.codeAlpha2 -> Tanzania
                    Thailand.codeAlpha2 -> Thailand
                    TimorLeste.codeAlpha2 -> TimorLeste
                    Togo.codeAlpha2 -> Togo
                    Tokelau.codeAlpha2 -> Tokelau
                    Tonga.codeAlpha2 -> Tonga
                    TrinidadAndTobago.codeAlpha2 -> TrinidadAndTobago
                    Tunisia.codeAlpha2 -> Tunisia
                    Turkmenistan.codeAlpha2 -> Turkmenistan
                    TurksAndCaicosIsland.codeAlpha2 -> TurksAndCaicosIsland
                    Tuvalu.codeAlpha2 -> Tuvalu
                    Türkiye.codeAlpha2 -> Türkiye
                    Uganda.codeAlpha2 -> Uganda
                    Ukraine.codeAlpha2 -> Ukraine
                    UnitedArabEmirates.codeAlpha2 -> UnitedArabEmirates
                    UnitedKingdomOfGreatBritainAndNorthernIreland.codeAlpha2 -> UnitedKingdomOfGreatBritainAndNorthernIreland
                    UnitedStatesMinorOutlyingIslands.codeAlpha2 -> UnitedStatesMinorOutlyingIslands
                    UnitedStatesOfAmerica.codeAlpha2 -> UnitedStatesOfAmerica
                    Uruguay.codeAlpha2 -> Uruguay
                    Uzbekistan.codeAlpha2 -> Uzbekistan
                    Vanuatu.codeAlpha2 -> Vanuatu
                    Venezuela.codeAlpha2 -> Venezuela
                    VietNam.codeAlpha2 -> VietNam
                    VirginIslandsBritish.codeAlpha2 -> VirginIslandsBritish
                    VirginIslandsUS.codeAlpha2 -> VirginIslandsUS
                    WallisAndFutuna.codeAlpha2 -> WallisAndFutuna
                    WesternSahara.codeAlpha2 -> WesternSahara
                    Yemen.codeAlpha2 -> Yemen
                    Zambia.codeAlpha2 -> Zambia
                    Zimbabwe.codeAlpha2 -> Zimbabwe
                    else -> null

                }
            }

            fun parseAlpha3(): Country? {
                return when (trimmedCode.takeIf(Code.Alpha3::isValidFormat)) {
                    null -> null // make sure it doesn't check other types
                    Afghanistan.codeAlpha3 -> Afghanistan
                    Albania.codeAlpha3 -> Albania
                    Algeria.codeAlpha3 -> Algeria
                    AmericanSamoa.codeAlpha3 -> AmericanSamoa
                    Andorra.codeAlpha3 -> Andorra
                    Angola.codeAlpha3 -> Angola
                    Anguilla.codeAlpha3 -> Anguilla
                    Antarctica.codeAlpha3 -> Antarctica
                    AntiguaAndBarbuda.codeAlpha3 -> AntiguaAndBarbuda
                    Argentina.codeAlpha3 -> Argentina
                    Armenia.codeAlpha3 -> Armenia
                    Aruba.codeAlpha3 -> Aruba
                    Australia.codeAlpha3 -> Australia
                    Austria.codeAlpha3 -> Austria
                    Azerbaijan.codeAlpha3 -> Azerbaijan
                    Bahamas.codeAlpha3 -> Bahamas
                    Bahrain.codeAlpha3 -> Bahrain
                    Bangladesh.codeAlpha3 -> Bangladesh
                    Barbados.codeAlpha3 -> Barbados
                    Belarus.codeAlpha3 -> Belarus
                    Belgium.codeAlpha3 -> Belgium
                    Belize.codeAlpha3 -> Belize
                    Benin.codeAlpha3 -> Benin
                    Bermuda.codeAlpha3 -> Bermuda
                    ÅlandIslands.codeAlpha3 -> ÅlandIslands
                    Bhutan.codeAlpha3 -> Bhutan
                    Bolivia.codeAlpha3 -> Bolivia
                    Bonaire.codeAlpha3 -> Bonaire
                    BosniaAndHerzegovina.codeAlpha3 -> BosniaAndHerzegovina
                    Botswana.codeAlpha3 -> Botswana
                    BouvetIsland.codeAlpha3 -> BouvetIsland
                    Brazil.codeAlpha3 -> Brazil
                    BritishIndianOceanTerritory.codeAlpha3 -> BritishIndianOceanTerritory
                    BruneiDarussalam.codeAlpha3 -> BruneiDarussalam
                    Bulgaria.codeAlpha3 -> Bulgaria
                    BurkinaFaso.codeAlpha3 -> BurkinaFaso
                    Burundi.codeAlpha3 -> Burundi
                    CaboVerde.codeAlpha3 -> CaboVerde
                    Cambodia.codeAlpha3 -> Cambodia
                    Cameroon.codeAlpha3 -> Cameroon
                    Canada.codeAlpha3 -> Canada
                    CaymanIslands.codeAlpha3 -> CaymanIslands
                    CentralAfricanRepublic.codeAlpha3 -> CentralAfricanRepublic
                    Chad.codeAlpha3 -> Chad
                    Chile.codeAlpha3 -> Chile
                    China.codeAlpha3 -> China
                    ChristmasIsland.codeAlpha3 -> ChristmasIsland
                    CocosIslands.codeAlpha3 -> CocosIslands
                    Colombia.codeAlpha3 -> Colombia
                    Comoros.codeAlpha3 -> Comoros
                    DemocraticRepublicCongo.codeAlpha3 -> DemocraticRepublicCongo
                    Congo.codeAlpha3 -> Congo
                    CookIslands.codeAlpha3 -> CookIslands
                    CostaRica.codeAlpha3 -> CostaRica
                    Croatia.codeAlpha3 -> Croatia
                    Cuba.codeAlpha3 -> Cuba
                    Curaçao.codeAlpha3 -> Curaçao
                    Cyprus.codeAlpha3 -> Cyprus
                    Czechia.codeAlpha3 -> Czechia
                    IvoryCoast.codeAlpha3 -> IvoryCoast
                    Denmark.codeAlpha3 -> Denmark
                    Djibouti.codeAlpha3 -> Djibouti
                    Dominica.codeAlpha3 -> Dominica
                    DominicanRepublic.codeAlpha3 -> DominicanRepublic
                    Ecuador.codeAlpha3 -> Ecuador
                    Egypt.codeAlpha3 -> Egypt
                    ElSalvador.codeAlpha3 -> ElSalvador
                    EquatorialGuinea.codeAlpha3 -> EquatorialGuinea
                    Eritrea.codeAlpha3 -> Eritrea
                    Estonia.codeAlpha3 -> Estonia
                    Eswatini.codeAlpha3 -> Eswatini
                    Ethiopia.codeAlpha3 -> Ethiopia
                    FalklandIslands.codeAlpha3 -> FalklandIslands
                    FaroeIslands.codeAlpha3 -> FaroeIslands
                    Fiji.codeAlpha3 -> Fiji
                    Finland.codeAlpha3 -> Finland
                    France.codeAlpha3 -> France
                    FrenchGuiana.codeAlpha3 -> FrenchGuiana
                    FrenchPolynesia.codeAlpha3 -> FrenchPolynesia
                    FrenchSouthernTerritories.codeAlpha3 -> FrenchSouthernTerritories
                    Gabon.codeAlpha3 -> Gabon
                    Gambia.codeAlpha3 -> Gambia
                    Georgia.codeAlpha3 -> Georgia
                    Germany.codeAlpha3 -> Germany
                    Ghana.codeAlpha3 -> Ghana
                    Gibraltar.codeAlpha3 -> Gibraltar
                    Greece.codeAlpha3 -> Greece
                    Greenland.codeAlpha3 -> Greenland
                    Grenada.codeAlpha3 -> Grenada
                    Guadeloupe.codeAlpha3 -> Guadeloupe
                    Guam.codeAlpha3 -> Guam
                    Guatemala.codeAlpha3 -> Guatemala
                    Guernsey.codeAlpha3 -> Guernsey
                    Guinea.codeAlpha3 -> Guinea
                    GuineaBissau.codeAlpha3 -> GuineaBissau
                    Guyana.codeAlpha3 -> Guyana
                    Haiti.codeAlpha3 -> Haiti
                    HeardIslandAndMcDonaldIslands.codeAlpha3 -> HeardIslandAndMcDonaldIslands
                    HolySee.codeAlpha3 -> HolySee
                    Honduras.codeAlpha3 -> Honduras
                    HongKong.codeAlpha3 -> HongKong
                    Hungary.codeAlpha3 -> Hungary
                    Iceland.codeAlpha3 -> Iceland
                    India.codeAlpha3 -> India
                    Indonesia.codeAlpha3 -> Indonesia
                    Iran.codeAlpha3 -> Iran
                    Iraq.codeAlpha3 -> Iraq
                    Ireland.codeAlpha3 -> Ireland
                    IsleOfMan.codeAlpha3 -> IsleOfMan
                    Israel.codeAlpha3 -> Israel
                    Italy.codeAlpha3 -> Italy
                    Jamaica.codeAlpha3 -> Jamaica
                    Japan.codeAlpha3 -> Japan
                    Jersey.codeAlpha3 -> Jersey
                    Jordan.codeAlpha3 -> Jordan
                    Kazakhstan.codeAlpha3 -> Kazakhstan
                    Kenya.codeAlpha3 -> Kenya
                    Kiribati.codeAlpha3 -> Kiribati
                    NorthKorea.codeAlpha3 -> NorthKorea
                    SouthKorea.codeAlpha3 -> SouthKorea
                    Kuwait.codeAlpha3 -> Kuwait
                    Kyrgyzstan.codeAlpha3 -> Kyrgyzstan
                    Lao.codeAlpha3 -> Lao
                    Latvia.codeAlpha3 -> Latvia
                    Lebanon.codeAlpha3 -> Lebanon
                    Lesotho.codeAlpha3 -> Lesotho
                    Liberia.codeAlpha3 -> Liberia
                    Libya.codeAlpha3 -> Libya
                    Liechtenstein.codeAlpha3 -> Liechtenstein
                    Lithuania.codeAlpha3 -> Lithuania
                    Luxembourg.codeAlpha3 -> Luxembourg
                    Macao.codeAlpha3 -> Macao
                    Madagascar.codeAlpha3 -> Madagascar
                    Malawi.codeAlpha3 -> Malawi
                    Malaysia.codeAlpha3 -> Malaysia
                    Maldives.codeAlpha3 -> Maldives
                    Mali.codeAlpha3 -> Mali
                    Malta.codeAlpha3 -> Malta
                    MarshallIslands.codeAlpha3 -> MarshallIslands
                    Martinique.codeAlpha3 -> Martinique
                    Mauritania.codeAlpha3 -> Mauritania
                    Mauritius.codeAlpha3 -> Mauritius
                    Mayotte.codeAlpha3 -> Mayotte
                    Mexico.codeAlpha3 -> Mexico
                    Micronesia.codeAlpha3 -> Micronesia
                    Moldova.codeAlpha3 -> Moldova
                    Monaco.codeAlpha3 -> Monaco
                    Mongolia.codeAlpha3 -> Mongolia
                    Montenegro.codeAlpha3 -> Montenegro
                    Montserrat.codeAlpha3 -> Montserrat
                    Morocco.codeAlpha3 -> Morocco
                    Mozambique.codeAlpha3 -> Mozambique
                    Myanmar.codeAlpha3 -> Myanmar
                    Namibia.codeAlpha3 -> Namibia
                    Nauru.codeAlpha3 -> Nauru
                    Nepal.codeAlpha3 -> Nepal
                    Netherlands.codeAlpha3 -> Netherlands
                    NewCaledonia.codeAlpha3 -> NewCaledonia
                    NewZealand.codeAlpha3 -> NewZealand
                    Nicaragua.codeAlpha3 -> Nicaragua
                    Niger.codeAlpha3 -> Niger
                    Nigeria.codeAlpha3 -> Nigeria
                    Niue.codeAlpha3 -> Niue
                    NorfolkIsland.codeAlpha3 -> NorfolkIsland
                    NorthMacedonia.codeAlpha3 -> NorthMacedonia
                    NorthernMarianaIslands.codeAlpha3 -> NorthernMarianaIslands
                    Norway.codeAlpha3 -> Norway
                    Oman.codeAlpha3 -> Oman
                    Pakistan.codeAlpha3 -> Pakistan
                    Palau.codeAlpha3 -> Palau
                    Palestine.codeAlpha3 -> Palestine
                    Panama.codeAlpha3 -> Panama
                    PapuaNewGuinea.codeAlpha3 -> PapuaNewGuinea
                    Paraguay.codeAlpha3 -> Paraguay
                    Peru.codeAlpha3 -> Peru
                    Philippines.codeAlpha3 -> Philippines
                    Pitcairn.codeAlpha3 -> Pitcairn
                    Poland.codeAlpha3 -> Poland
                    Portugal.codeAlpha3 -> Portugal
                    PuertoRico.codeAlpha3 -> PuertoRico
                    Qatar.codeAlpha3 -> Qatar
                    Romania.codeAlpha3 -> Romania
                    RussianFederation.codeAlpha3 -> RussianFederation
                    Rwanda.codeAlpha3 -> Rwanda
                    Réunion.codeAlpha3 -> Réunion
                    SaintBarthélemy.codeAlpha3 -> SaintBarthélemy
                    SaintHelena.codeAlpha3 -> SaintHelena
                    SaintKittsAndNevis.codeAlpha3 -> SaintKittsAndNevis
                    SaintLucia.codeAlpha3 -> SaintLucia
                    SaintMartin.codeAlpha3 -> SaintMartin
                    SaintPierreAndMiquelon.codeAlpha3 -> SaintPierreAndMiquelon
                    SaintVincentAndTheGrenadines.codeAlpha3 -> SaintVincentAndTheGrenadines
                    Samoa.codeAlpha3 -> Samoa
                    SaoTomeAndPrincipe.codeAlpha3 -> SaoTomeAndPrincipe
                    SaudiArabia.codeAlpha3 -> SaudiArabia
                    Senegal.codeAlpha3 -> Senegal
                    Serbia.codeAlpha3 -> Serbia
                    Seychelles.codeAlpha3 -> Seychelles
                    SierraLeone.codeAlpha3 -> SierraLeone
                    Singapore.codeAlpha3 -> Singapore
                    SintMaarten.codeAlpha3 -> SintMaarten
                    Slovakia.codeAlpha3 -> Slovakia
                    Slovenia.codeAlpha3 -> Slovenia
                    SolomonIslands.codeAlpha3 -> SolomonIslands
                    SouthAfrica.codeAlpha3 -> SouthAfrica
                    SouthGeorgiaAndTheSouthSandwichIslands.codeAlpha3 -> SouthGeorgiaAndTheSouthSandwichIslands
                    SouthSudan.codeAlpha3 -> SouthSudan
                    Spain.codeAlpha3 -> Spain
                    SriLanka.codeAlpha3 -> SriLanka
                    Sudan.codeAlpha3 -> Sudan
                    Suriname.codeAlpha3 -> Suriname
                    SvalbardAndJanMayen.codeAlpha3 -> SvalbardAndJanMayen
                    Sweden.codeAlpha3 -> Sweden
                    Switzerland.codeAlpha3 -> Switzerland
                    Syrian.codeAlpha3 -> Syrian
                    Taiwan.codeAlpha3 -> Taiwan
                    Tajikistan.codeAlpha3 -> Tajikistan
                    Tanzania.codeAlpha3 -> Tanzania
                    Thailand.codeAlpha3 -> Thailand
                    TimorLeste.codeAlpha3 -> TimorLeste
                    Togo.codeAlpha3 -> Togo
                    Tokelau.codeAlpha3 -> Tokelau
                    Tonga.codeAlpha3 -> Tonga
                    TrinidadAndTobago.codeAlpha3 -> TrinidadAndTobago
                    Tunisia.codeAlpha3 -> Tunisia
                    Turkmenistan.codeAlpha3 -> Turkmenistan
                    TurksAndCaicosIsland.codeAlpha3 -> TurksAndCaicosIsland
                    Tuvalu.codeAlpha3 -> Tuvalu
                    Türkiye.codeAlpha3 -> Türkiye
                    Uganda.codeAlpha3 -> Uganda
                    Ukraine.codeAlpha3 -> Ukraine
                    UnitedArabEmirates.codeAlpha3 -> UnitedArabEmirates
                    UnitedKingdomOfGreatBritainAndNorthernIreland.codeAlpha3 -> UnitedKingdomOfGreatBritainAndNorthernIreland
                    UnitedStatesMinorOutlyingIslands.codeAlpha3 -> UnitedStatesMinorOutlyingIslands
                    UnitedStatesOfAmerica.codeAlpha3 -> UnitedStatesOfAmerica
                    Uruguay.codeAlpha3 -> Uruguay
                    Uzbekistan.codeAlpha3 -> Uzbekistan
                    Vanuatu.codeAlpha3 -> Vanuatu
                    Venezuela.codeAlpha3 -> Venezuela
                    VietNam.codeAlpha3 -> VietNam
                    VirginIslandsBritish.codeAlpha3 -> VirginIslandsBritish
                    VirginIslandsUS.codeAlpha3 -> VirginIslandsUS
                    WallisAndFutuna.codeAlpha3 -> WallisAndFutuna
                    WesternSahara.codeAlpha3 -> WesternSahara
                    Yemen.codeAlpha3 -> Yemen
                    Zambia.codeAlpha3 -> Zambia
                    Zimbabwe.codeAlpha3 -> Zimbabwe
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
