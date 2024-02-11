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
                    Afghanistan.codeAlpha2.toString() -> Afghanistan
                    Albania.codeAlpha2.toString() -> Albania
                    Algeria.codeAlpha2.toString() -> Algeria
                    AmericanSamoa.codeAlpha2.toString() -> AmericanSamoa
                    Andorra.codeAlpha2.toString() -> Andorra
                    Angola.codeAlpha2.toString() -> Angola
                    Anguilla.codeAlpha2.toString() -> Anguilla
                    Antarctica.codeAlpha2.toString() -> Antarctica
                    AntiguaAndBarbuda.codeAlpha2.toString() -> AntiguaAndBarbuda
                    Argentina.codeAlpha2.toString() -> Argentina
                    Armenia.codeAlpha2.toString() -> Armenia
                    Aruba.codeAlpha2.toString() -> Aruba
                    Australia.codeAlpha2.toString() -> Australia
                    Austria.codeAlpha2.toString() -> Austria
                    Azerbaijan.codeAlpha2.toString() -> Azerbaijan
                    Bahamas.codeAlpha2.toString() -> Bahamas
                    Bahrain.codeAlpha2.toString() -> Bahrain
                    Bangladesh.codeAlpha2.toString() -> Bangladesh
                    Barbados.codeAlpha2.toString() -> Barbados
                    Belarus.codeAlpha2.toString() -> Belarus
                    Belgium.codeAlpha2.toString() -> Belgium
                    Belize.codeAlpha2.toString() -> Belize
                    Benin.codeAlpha2.toString() -> Benin
                    Bermuda.codeAlpha2.toString() -> Bermuda
                    ÅlandIslands.codeAlpha2.toString() -> ÅlandIslands
                    Bhutan.codeAlpha2.toString() -> Bhutan
                    Bolivia.codeAlpha2.toString() -> Bolivia
                    Bonaire.codeAlpha2.toString() -> Bonaire
                    BosniaAndHerzegovina.codeAlpha2.toString() -> BosniaAndHerzegovina
                    Botswana.codeAlpha2.toString() -> Botswana
                    BouvetIsland.codeAlpha2.toString() -> BouvetIsland
                    Brazil.codeAlpha2.toString() -> Brazil
                    BritishIndianOceanTerritory.codeAlpha2.toString() -> BritishIndianOceanTerritory
                    BruneiDarussalam.codeAlpha2.toString() -> BruneiDarussalam
                    Bulgaria.codeAlpha2.toString() -> Bulgaria
                    BurkinaFaso.codeAlpha2.toString() -> BurkinaFaso
                    Burundi.codeAlpha2.toString() -> Burundi
                    CaboVerde.codeAlpha2.toString() -> CaboVerde
                    Cambodia.codeAlpha2.toString() -> Cambodia
                    Cameroon.codeAlpha2.toString() -> Cameroon
                    Canada.codeAlpha2.toString() -> Canada
                    CaymanIslands.codeAlpha2.toString() -> CaymanIslands
                    CentralAfricanRepublic.codeAlpha2.toString() -> CentralAfricanRepublic
                    Chad.codeAlpha2.toString() -> Chad
                    Chile.codeAlpha2.toString() -> Chile
                    China.codeAlpha2.toString() -> China
                    ChristmasIsland.codeAlpha2.toString() -> ChristmasIsland
                    CocosIslands.codeAlpha2.toString() -> CocosIslands
                    Colombia.codeAlpha2.toString() -> Colombia
                    Comoros.codeAlpha2.toString() -> Comoros
                    DemocraticRepublicCongo.codeAlpha2.toString() -> DemocraticRepublicCongo
                    Congo.codeAlpha2.toString() -> Congo
                    CookIslands.codeAlpha2.toString() -> CookIslands
                    CostaRica.codeAlpha2.toString() -> CostaRica
                    Croatia.codeAlpha2.toString() -> Croatia
                    Cuba.codeAlpha2.toString() -> Cuba
                    Curaçao.codeAlpha2.toString() -> Curaçao
                    Cyprus.codeAlpha2.toString() -> Cyprus
                    Czechia.codeAlpha2.toString() -> Czechia
                    IvoryCoast.codeAlpha2.toString() -> IvoryCoast
                    Denmark.codeAlpha2.toString() -> Denmark
                    Djibouti.codeAlpha2.toString() -> Djibouti
                    Dominica.codeAlpha2.toString() -> Dominica
                    DominicanRepublic.codeAlpha2.toString() -> DominicanRepublic
                    Ecuador.codeAlpha2.toString() -> Ecuador
                    Egypt.codeAlpha2.toString() -> Egypt
                    ElSalvador.codeAlpha2.toString() -> ElSalvador
                    EquatorialGuinea.codeAlpha2.toString() -> EquatorialGuinea
                    Eritrea.codeAlpha2.toString() -> Eritrea
                    Estonia.codeAlpha2.toString() -> Estonia
                    Eswatini.codeAlpha2.toString() -> Eswatini
                    Ethiopia.codeAlpha2.toString() -> Ethiopia
                    FalklandIslands.codeAlpha2.toString() -> FalklandIslands
                    FaroeIslands.codeAlpha2.toString() -> FaroeIslands
                    Fiji.codeAlpha2.toString() -> Fiji
                    Finland.codeAlpha2.toString() -> Finland
                    France.codeAlpha2.toString() -> France
                    FrenchGuiana.codeAlpha2.toString() -> FrenchGuiana
                    FrenchPolynesia.codeAlpha2.toString() -> FrenchPolynesia
                    FrenchSouthernTerritories.codeAlpha2.toString() -> FrenchSouthernTerritories
                    Gabon.codeAlpha2.toString() -> Gabon
                    Gambia.codeAlpha2.toString() -> Gambia
                    Georgia.codeAlpha2.toString() -> Georgia
                    Germany.codeAlpha2.toString() -> Germany
                    Ghana.codeAlpha2.toString() -> Ghana
                    Gibraltar.codeAlpha2.toString() -> Gibraltar
                    Greece.codeAlpha2.toString() -> Greece
                    Greenland.codeAlpha2.toString() -> Greenland
                    Grenada.codeAlpha2.toString() -> Grenada
                    Guadeloupe.codeAlpha2.toString() -> Guadeloupe
                    Guam.codeAlpha2.toString() -> Guam
                    Guatemala.codeAlpha2.toString() -> Guatemala
                    Guernsey.codeAlpha2.toString() -> Guernsey
                    Guinea.codeAlpha2.toString() -> Guinea
                    GuineaBissau.codeAlpha2.toString() -> GuineaBissau
                    Guyana.codeAlpha2.toString() -> Guyana
                    Haiti.codeAlpha2.toString() -> Haiti
                    HeardIslandAndMcDonaldIslands.codeAlpha2.toString() -> HeardIslandAndMcDonaldIslands
                    HolySee.codeAlpha2.toString() -> HolySee
                    Honduras.codeAlpha2.toString() -> Honduras
                    HongKong.codeAlpha2.toString() -> HongKong
                    Hungary.codeAlpha2.toString() -> Hungary
                    Iceland.codeAlpha2.toString() -> Iceland
                    India.codeAlpha2.toString() -> India
                    Indonesia.codeAlpha2.toString() -> Indonesia
                    Iran.codeAlpha2.toString() -> Iran
                    Iraq.codeAlpha2.toString() -> Iraq
                    Ireland.codeAlpha2.toString() -> Ireland
                    IsleOfMan.codeAlpha2.toString() -> IsleOfMan
                    Israel.codeAlpha2.toString() -> Israel
                    Italy.codeAlpha2.toString() -> Italy
                    Jamaica.codeAlpha2.toString() -> Jamaica
                    Japan.codeAlpha2.toString() -> Japan
                    Jersey.codeAlpha2.toString() -> Jersey
                    Jordan.codeAlpha2.toString() -> Jordan
                    Kazakhstan.codeAlpha2.toString() -> Kazakhstan
                    Kenya.codeAlpha2.toString() -> Kenya
                    Kiribati.codeAlpha2.toString() -> Kiribati
                    NorthKorea.codeAlpha2.toString() -> NorthKorea
                    SouthKorea.codeAlpha2.toString() -> SouthKorea
                    Kuwait.codeAlpha2.toString() -> Kuwait
                    Kyrgyzstan.codeAlpha2.toString() -> Kyrgyzstan
                    Lao.codeAlpha2.toString() -> Lao
                    Latvia.codeAlpha2.toString() -> Latvia
                    Lebanon.codeAlpha2.toString() -> Lebanon
                    Lesotho.codeAlpha2.toString() -> Lesotho
                    Liberia.codeAlpha2.toString() -> Liberia
                    Libya.codeAlpha2.toString() -> Libya
                    Liechtenstein.codeAlpha2.toString() -> Liechtenstein
                    Lithuania.codeAlpha2.toString() -> Lithuania
                    Luxembourg.codeAlpha2.toString() -> Luxembourg
                    Macao.codeAlpha2.toString() -> Macao
                    Madagascar.codeAlpha2.toString() -> Madagascar
                    Malawi.codeAlpha2.toString() -> Malawi
                    Malaysia.codeAlpha2.toString() -> Malaysia
                    Maldives.codeAlpha2.toString() -> Maldives
                    Mali.codeAlpha2.toString() -> Mali
                    Malta.codeAlpha2.toString() -> Malta
                    MarshallIslands.codeAlpha2.toString() -> MarshallIslands
                    Martinique.codeAlpha2.toString() -> Martinique
                    Mauritania.codeAlpha2.toString() -> Mauritania
                    Mauritius.codeAlpha2.toString() -> Mauritius
                    Mayotte.codeAlpha2.toString() -> Mayotte
                    Mexico.codeAlpha2.toString() -> Mexico
                    Micronesia.codeAlpha2.toString() -> Micronesia
                    Moldova.codeAlpha2.toString() -> Moldova
                    Monaco.codeAlpha2.toString() -> Monaco
                    Mongolia.codeAlpha2.toString() -> Mongolia
                    Montenegro.codeAlpha2.toString() -> Montenegro
                    Montserrat.codeAlpha2.toString() -> Montserrat
                    Morocco.codeAlpha2.toString() -> Morocco
                    Mozambique.codeAlpha2.toString() -> Mozambique
                    Myanmar.codeAlpha2.toString() -> Myanmar
                    Namibia.codeAlpha2.toString() -> Namibia
                    Nauru.codeAlpha2.toString() -> Nauru
                    Nepal.codeAlpha2.toString() -> Nepal
                    Netherlands.codeAlpha2.toString() -> Netherlands
                    NewCaledonia.codeAlpha2.toString() -> NewCaledonia
                    NewZealand.codeAlpha2.toString() -> NewZealand
                    Nicaragua.codeAlpha2.toString() -> Nicaragua
                    Niger.codeAlpha2.toString() -> Niger
                    Nigeria.codeAlpha2.toString() -> Nigeria
                    Niue.codeAlpha2.toString() -> Niue
                    NorfolkIsland.codeAlpha2.toString() -> NorfolkIsland
                    NorthMacedonia.codeAlpha2.toString() -> NorthMacedonia
                    NorthernMarianaIslands.codeAlpha2.toString() -> NorthernMarianaIslands
                    Norway.codeAlpha2.toString() -> Norway
                    Oman.codeAlpha2.toString() -> Oman
                    Pakistan.codeAlpha2.toString() -> Pakistan
                    Palau.codeAlpha2.toString() -> Palau
                    Palestine.codeAlpha2.toString() -> Palestine
                    Panama.codeAlpha2.toString() -> Panama
                    PapuaNewGuinea.codeAlpha2.toString() -> PapuaNewGuinea
                    Paraguay.codeAlpha2.toString() -> Paraguay
                    Peru.codeAlpha2.toString() -> Peru
                    Philippines.codeAlpha2.toString() -> Philippines
                    Pitcairn.codeAlpha2.toString() -> Pitcairn
                    Poland.codeAlpha2.toString() -> Poland
                    Portugal.codeAlpha2.toString() -> Portugal
                    PuertoRico.codeAlpha2.toString() -> PuertoRico
                    Qatar.codeAlpha2.toString() -> Qatar
                    Romania.codeAlpha2.toString() -> Romania
                    RussianFederation.codeAlpha2.toString() -> RussianFederation
                    Rwanda.codeAlpha2.toString() -> Rwanda
                    Réunion.codeAlpha2.toString() -> Réunion
                    SaintBarthélemy.codeAlpha2.toString() -> SaintBarthélemy
                    SaintHelena.codeAlpha2.toString() -> SaintHelena
                    SaintKittsAndNevis.codeAlpha2.toString() -> SaintKittsAndNevis
                    SaintLucia.codeAlpha2.toString() -> SaintLucia
                    SaintMartin.codeAlpha2.toString() -> SaintMartin
                    SaintPierreAndMiquelon.codeAlpha2.toString() -> SaintPierreAndMiquelon
                    SaintVincentAndTheGrenadines.codeAlpha2.toString() -> SaintVincentAndTheGrenadines
                    Samoa.codeAlpha2.toString() -> Samoa
                    SaoTomeAndPrincipe.codeAlpha2.toString() -> SaoTomeAndPrincipe
                    SaudiArabia.codeAlpha2.toString() -> SaudiArabia
                    Senegal.codeAlpha2.toString() -> Senegal
                    Serbia.codeAlpha2.toString() -> Serbia
                    Seychelles.codeAlpha2.toString() -> Seychelles
                    SierraLeone.codeAlpha2.toString() -> SierraLeone
                    Singapore.codeAlpha2.toString() -> Singapore
                    SintMaarten.codeAlpha2.toString() -> SintMaarten
                    Slovakia.codeAlpha2.toString() -> Slovakia
                    Slovenia.codeAlpha2.toString() -> Slovenia
                    SolomonIslands.codeAlpha2.toString() -> SolomonIslands
                    SouthAfrica.codeAlpha2.toString() -> SouthAfrica
                    SouthGeorgiaAndTheSouthSandwichIslands.codeAlpha2.toString() -> SouthGeorgiaAndTheSouthSandwichIslands
                    SouthSudan.codeAlpha2.toString() -> SouthSudan
                    Spain.codeAlpha2.toString() -> Spain
                    SriLanka.codeAlpha2.toString() -> SriLanka
                    Sudan.codeAlpha2.toString() -> Sudan
                    Suriname.codeAlpha2.toString() -> Suriname
                    SvalbardAndJanMayen.codeAlpha2.toString() -> SvalbardAndJanMayen
                    Sweden.codeAlpha2.toString() -> Sweden
                    Switzerland.codeAlpha2.toString() -> Switzerland
                    Syrian.codeAlpha2.toString() -> Syrian
                    Taiwan.codeAlpha2.toString() -> Taiwan
                    Tajikistan.codeAlpha2.toString() -> Tajikistan
                    Tanzania.codeAlpha2.toString() -> Tanzania
                    Thailand.codeAlpha2.toString() -> Thailand
                    TimorLeste.codeAlpha2.toString() -> TimorLeste
                    Togo.codeAlpha2.toString() -> Togo
                    Tokelau.codeAlpha2.toString() -> Tokelau
                    Tonga.codeAlpha2.toString() -> Tonga
                    TrinidadAndTobago.codeAlpha2.toString() -> TrinidadAndTobago
                    Tunisia.codeAlpha2.toString() -> Tunisia
                    Turkmenistan.codeAlpha2.toString() -> Turkmenistan
                    TurksAndCaicosIsland.codeAlpha2.toString() -> TurksAndCaicosIsland
                    Tuvalu.codeAlpha2.toString() -> Tuvalu
                    Türkiye.codeAlpha2.toString() -> Türkiye
                    Uganda.codeAlpha2.toString() -> Uganda
                    Ukraine.codeAlpha2.toString() -> Ukraine
                    UnitedArabEmirates.codeAlpha2.toString() -> UnitedArabEmirates
                    UnitedKingdomOfGreatBritainAndNorthernIreland.codeAlpha2.toString() -> UnitedKingdomOfGreatBritainAndNorthernIreland
                    UnitedStatesMinorOutlyingIslands.codeAlpha2.toString() -> UnitedStatesMinorOutlyingIslands
                    UnitedStatesOfAmerica.codeAlpha2.toString() -> UnitedStatesOfAmerica
                    Uruguay.codeAlpha2.toString() -> Uruguay
                    Uzbekistan.codeAlpha2.toString() -> Uzbekistan
                    Vanuatu.codeAlpha2.toString() -> Vanuatu
                    Venezuela.codeAlpha2.toString() -> Venezuela
                    VietNam.codeAlpha2.toString() -> VietNam
                    VirginIslandsBritish.codeAlpha2.toString() -> VirginIslandsBritish
                    VirginIslandsUS.codeAlpha2.toString() -> VirginIslandsUS
                    WallisAndFutuna.codeAlpha2.toString() -> WallisAndFutuna
                    WesternSahara.codeAlpha2.toString() -> WesternSahara
                    Yemen.codeAlpha2.toString() -> Yemen
                    Zambia.codeAlpha2.toString() -> Zambia
                    Zimbabwe.codeAlpha2.toString() -> Zimbabwe
                    else -> null

                }
            }

            fun parseAlpha3(): Country? {
                return when (trimmedCode.takeIf(Code.Alpha3::isValidFormat)) {
                    null -> null // make sure it doesn't check other types
                    Afghanistan.codeAlpha3.toString() -> Afghanistan
                    Albania.codeAlpha3.toString() -> Albania
                    Algeria.codeAlpha3.toString() -> Algeria
                    AmericanSamoa.codeAlpha3.toString() -> AmericanSamoa
                    Andorra.codeAlpha3.toString() -> Andorra
                    Angola.codeAlpha3.toString() -> Angola
                    Anguilla.codeAlpha3.toString() -> Anguilla
                    Antarctica.codeAlpha3.toString() -> Antarctica
                    AntiguaAndBarbuda.codeAlpha3.toString() -> AntiguaAndBarbuda
                    Argentina.codeAlpha3.toString() -> Argentina
                    Armenia.codeAlpha3.toString() -> Armenia
                    Aruba.codeAlpha3.toString() -> Aruba
                    Australia.codeAlpha3.toString() -> Australia
                    Austria.codeAlpha3.toString() -> Austria
                    Azerbaijan.codeAlpha3.toString() -> Azerbaijan
                    Bahamas.codeAlpha3.toString() -> Bahamas
                    Bahrain.codeAlpha3.toString() -> Bahrain
                    Bangladesh.codeAlpha3.toString() -> Bangladesh
                    Barbados.codeAlpha3.toString() -> Barbados
                    Belarus.codeAlpha3.toString() -> Belarus
                    Belgium.codeAlpha3.toString() -> Belgium
                    Belize.codeAlpha3.toString() -> Belize
                    Benin.codeAlpha3.toString() -> Benin
                    Bermuda.codeAlpha3.toString() -> Bermuda
                    ÅlandIslands.codeAlpha3.toString() -> ÅlandIslands
                    Bhutan.codeAlpha3.toString() -> Bhutan
                    Bolivia.codeAlpha3.toString() -> Bolivia
                    Bonaire.codeAlpha3.toString() -> Bonaire
                    BosniaAndHerzegovina.codeAlpha3.toString() -> BosniaAndHerzegovina
                    Botswana.codeAlpha3.toString() -> Botswana
                    BouvetIsland.codeAlpha3.toString() -> BouvetIsland
                    Brazil.codeAlpha3.toString() -> Brazil
                    BritishIndianOceanTerritory.codeAlpha3.toString() -> BritishIndianOceanTerritory
                    BruneiDarussalam.codeAlpha3.toString() -> BruneiDarussalam
                    Bulgaria.codeAlpha3.toString() -> Bulgaria
                    BurkinaFaso.codeAlpha3.toString() -> BurkinaFaso
                    Burundi.codeAlpha3.toString() -> Burundi
                    CaboVerde.codeAlpha3.toString() -> CaboVerde
                    Cambodia.codeAlpha3.toString() -> Cambodia
                    Cameroon.codeAlpha3.toString() -> Cameroon
                    Canada.codeAlpha3.toString() -> Canada
                    CaymanIslands.codeAlpha3.toString() -> CaymanIslands
                    CentralAfricanRepublic.codeAlpha3.toString() -> CentralAfricanRepublic
                    Chad.codeAlpha3.toString() -> Chad
                    Chile.codeAlpha3.toString() -> Chile
                    China.codeAlpha3.toString() -> China
                    ChristmasIsland.codeAlpha3.toString() -> ChristmasIsland
                    CocosIslands.codeAlpha3.toString() -> CocosIslands
                    Colombia.codeAlpha3.toString() -> Colombia
                    Comoros.codeAlpha3.toString() -> Comoros
                    DemocraticRepublicCongo.codeAlpha3.toString() -> DemocraticRepublicCongo
                    Congo.codeAlpha3.toString() -> Congo
                    CookIslands.codeAlpha3.toString() -> CookIslands
                    CostaRica.codeAlpha3.toString() -> CostaRica
                    Croatia.codeAlpha3.toString() -> Croatia
                    Cuba.codeAlpha3.toString() -> Cuba
                    Curaçao.codeAlpha3.toString() -> Curaçao
                    Cyprus.codeAlpha3.toString() -> Cyprus
                    Czechia.codeAlpha3.toString() -> Czechia
                    IvoryCoast.codeAlpha3.toString() -> IvoryCoast
                    Denmark.codeAlpha3.toString() -> Denmark
                    Djibouti.codeAlpha3.toString() -> Djibouti
                    Dominica.codeAlpha3.toString() -> Dominica
                    DominicanRepublic.codeAlpha3.toString() -> DominicanRepublic
                    Ecuador.codeAlpha3.toString() -> Ecuador
                    Egypt.codeAlpha3.toString() -> Egypt
                    ElSalvador.codeAlpha3.toString() -> ElSalvador
                    EquatorialGuinea.codeAlpha3.toString() -> EquatorialGuinea
                    Eritrea.codeAlpha3.toString() -> Eritrea
                    Estonia.codeAlpha3.toString() -> Estonia
                    Eswatini.codeAlpha3.toString() -> Eswatini
                    Ethiopia.codeAlpha3.toString() -> Ethiopia
                    FalklandIslands.codeAlpha3.toString() -> FalklandIslands
                    FaroeIslands.codeAlpha3.toString() -> FaroeIslands
                    Fiji.codeAlpha3.toString() -> Fiji
                    Finland.codeAlpha3.toString() -> Finland
                    France.codeAlpha3.toString() -> France
                    FrenchGuiana.codeAlpha3.toString() -> FrenchGuiana
                    FrenchPolynesia.codeAlpha3.toString() -> FrenchPolynesia
                    FrenchSouthernTerritories.codeAlpha3.toString() -> FrenchSouthernTerritories
                    Gabon.codeAlpha3.toString() -> Gabon
                    Gambia.codeAlpha3.toString() -> Gambia
                    Georgia.codeAlpha3.toString() -> Georgia
                    Germany.codeAlpha3.toString() -> Germany
                    Ghana.codeAlpha3.toString() -> Ghana
                    Gibraltar.codeAlpha3.toString() -> Gibraltar
                    Greece.codeAlpha3.toString() -> Greece
                    Greenland.codeAlpha3.toString() -> Greenland
                    Grenada.codeAlpha3.toString() -> Grenada
                    Guadeloupe.codeAlpha3.toString() -> Guadeloupe
                    Guam.codeAlpha3.toString() -> Guam
                    Guatemala.codeAlpha3.toString() -> Guatemala
                    Guernsey.codeAlpha3.toString() -> Guernsey
                    Guinea.codeAlpha3.toString() -> Guinea
                    GuineaBissau.codeAlpha3.toString() -> GuineaBissau
                    Guyana.codeAlpha3.toString() -> Guyana
                    Haiti.codeAlpha3.toString() -> Haiti
                    HeardIslandAndMcDonaldIslands.codeAlpha3.toString() -> HeardIslandAndMcDonaldIslands
                    HolySee.codeAlpha3.toString() -> HolySee
                    Honduras.codeAlpha3.toString() -> Honduras
                    HongKong.codeAlpha3.toString() -> HongKong
                    Hungary.codeAlpha3.toString() -> Hungary
                    Iceland.codeAlpha3.toString() -> Iceland
                    India.codeAlpha3.toString() -> India
                    Indonesia.codeAlpha3.toString() -> Indonesia
                    Iran.codeAlpha3.toString() -> Iran
                    Iraq.codeAlpha3.toString() -> Iraq
                    Ireland.codeAlpha3.toString() -> Ireland
                    IsleOfMan.codeAlpha3.toString() -> IsleOfMan
                    Israel.codeAlpha3.toString() -> Israel
                    Italy.codeAlpha3.toString() -> Italy
                    Jamaica.codeAlpha3.toString() -> Jamaica
                    Japan.codeAlpha3.toString() -> Japan
                    Jersey.codeAlpha3.toString() -> Jersey
                    Jordan.codeAlpha3.toString() -> Jordan
                    Kazakhstan.codeAlpha3.toString() -> Kazakhstan
                    Kenya.codeAlpha3.toString() -> Kenya
                    Kiribati.codeAlpha3.toString() -> Kiribati
                    NorthKorea.codeAlpha3.toString() -> NorthKorea
                    SouthKorea.codeAlpha3.toString() -> SouthKorea
                    Kuwait.codeAlpha3.toString() -> Kuwait
                    Kyrgyzstan.codeAlpha3.toString() -> Kyrgyzstan
                    Lao.codeAlpha3.toString() -> Lao
                    Latvia.codeAlpha3.toString() -> Latvia
                    Lebanon.codeAlpha3.toString() -> Lebanon
                    Lesotho.codeAlpha3.toString() -> Lesotho
                    Liberia.codeAlpha3.toString() -> Liberia
                    Libya.codeAlpha3.toString() -> Libya
                    Liechtenstein.codeAlpha3.toString() -> Liechtenstein
                    Lithuania.codeAlpha3.toString() -> Lithuania
                    Luxembourg.codeAlpha3.toString() -> Luxembourg
                    Macao.codeAlpha3.toString() -> Macao
                    Madagascar.codeAlpha3.toString() -> Madagascar
                    Malawi.codeAlpha3.toString() -> Malawi
                    Malaysia.codeAlpha3.toString() -> Malaysia
                    Maldives.codeAlpha3.toString() -> Maldives
                    Mali.codeAlpha3.toString() -> Mali
                    Malta.codeAlpha3.toString() -> Malta
                    MarshallIslands.codeAlpha3.toString() -> MarshallIslands
                    Martinique.codeAlpha3.toString() -> Martinique
                    Mauritania.codeAlpha3.toString() -> Mauritania
                    Mauritius.codeAlpha3.toString() -> Mauritius
                    Mayotte.codeAlpha3.toString() -> Mayotte
                    Mexico.codeAlpha3.toString() -> Mexico
                    Micronesia.codeAlpha3.toString() -> Micronesia
                    Moldova.codeAlpha3.toString() -> Moldova
                    Monaco.codeAlpha3.toString() -> Monaco
                    Mongolia.codeAlpha3.toString() -> Mongolia
                    Montenegro.codeAlpha3.toString() -> Montenegro
                    Montserrat.codeAlpha3.toString() -> Montserrat
                    Morocco.codeAlpha3.toString() -> Morocco
                    Mozambique.codeAlpha3.toString() -> Mozambique
                    Myanmar.codeAlpha3.toString() -> Myanmar
                    Namibia.codeAlpha3.toString() -> Namibia
                    Nauru.codeAlpha3.toString() -> Nauru
                    Nepal.codeAlpha3.toString() -> Nepal
                    Netherlands.codeAlpha3.toString() -> Netherlands
                    NewCaledonia.codeAlpha3.toString() -> NewCaledonia
                    NewZealand.codeAlpha3.toString() -> NewZealand
                    Nicaragua.codeAlpha3.toString() -> Nicaragua
                    Niger.codeAlpha3.toString() -> Niger
                    Nigeria.codeAlpha3.toString() -> Nigeria
                    Niue.codeAlpha3.toString() -> Niue
                    NorfolkIsland.codeAlpha3.toString() -> NorfolkIsland
                    NorthMacedonia.codeAlpha3.toString() -> NorthMacedonia
                    NorthernMarianaIslands.codeAlpha3.toString() -> NorthernMarianaIslands
                    Norway.codeAlpha3.toString() -> Norway
                    Oman.codeAlpha3.toString() -> Oman
                    Pakistan.codeAlpha3.toString() -> Pakistan
                    Palau.codeAlpha3.toString() -> Palau
                    Palestine.codeAlpha3.toString() -> Palestine
                    Panama.codeAlpha3.toString() -> Panama
                    PapuaNewGuinea.codeAlpha3.toString() -> PapuaNewGuinea
                    Paraguay.codeAlpha3.toString() -> Paraguay
                    Peru.codeAlpha3.toString() -> Peru
                    Philippines.codeAlpha3.toString() -> Philippines
                    Pitcairn.codeAlpha3.toString() -> Pitcairn
                    Poland.codeAlpha3.toString() -> Poland
                    Portugal.codeAlpha3.toString() -> Portugal
                    PuertoRico.codeAlpha3.toString() -> PuertoRico
                    Qatar.codeAlpha3.toString() -> Qatar
                    Romania.codeAlpha3.toString() -> Romania
                    RussianFederation.codeAlpha3.toString() -> RussianFederation
                    Rwanda.codeAlpha3.toString() -> Rwanda
                    Réunion.codeAlpha3.toString() -> Réunion
                    SaintBarthélemy.codeAlpha3.toString() -> SaintBarthélemy
                    SaintHelena.codeAlpha3.toString() -> SaintHelena
                    SaintKittsAndNevis.codeAlpha3.toString() -> SaintKittsAndNevis
                    SaintLucia.codeAlpha3.toString() -> SaintLucia
                    SaintMartin.codeAlpha3.toString() -> SaintMartin
                    SaintPierreAndMiquelon.codeAlpha3.toString() -> SaintPierreAndMiquelon
                    SaintVincentAndTheGrenadines.codeAlpha3.toString() -> SaintVincentAndTheGrenadines
                    Samoa.codeAlpha3.toString() -> Samoa
                    SaoTomeAndPrincipe.codeAlpha3.toString() -> SaoTomeAndPrincipe
                    SaudiArabia.codeAlpha3.toString() -> SaudiArabia
                    Senegal.codeAlpha3.toString() -> Senegal
                    Serbia.codeAlpha3.toString() -> Serbia
                    Seychelles.codeAlpha3.toString() -> Seychelles
                    SierraLeone.codeAlpha3.toString() -> SierraLeone
                    Singapore.codeAlpha3.toString() -> Singapore
                    SintMaarten.codeAlpha3.toString() -> SintMaarten
                    Slovakia.codeAlpha3.toString() -> Slovakia
                    Slovenia.codeAlpha3.toString() -> Slovenia
                    SolomonIslands.codeAlpha3.toString() -> SolomonIslands
                    SouthAfrica.codeAlpha3.toString() -> SouthAfrica
                    SouthGeorgiaAndTheSouthSandwichIslands.codeAlpha3.toString() -> SouthGeorgiaAndTheSouthSandwichIslands
                    SouthSudan.codeAlpha3.toString() -> SouthSudan
                    Spain.codeAlpha3.toString() -> Spain
                    SriLanka.codeAlpha3.toString() -> SriLanka
                    Sudan.codeAlpha3.toString() -> Sudan
                    Suriname.codeAlpha3.toString() -> Suriname
                    SvalbardAndJanMayen.codeAlpha3.toString() -> SvalbardAndJanMayen
                    Sweden.codeAlpha3.toString() -> Sweden
                    Switzerland.codeAlpha3.toString() -> Switzerland
                    Syrian.codeAlpha3.toString() -> Syrian
                    Taiwan.codeAlpha3.toString() -> Taiwan
                    Tajikistan.codeAlpha3.toString() -> Tajikistan
                    Tanzania.codeAlpha3.toString() -> Tanzania
                    Thailand.codeAlpha3.toString() -> Thailand
                    TimorLeste.codeAlpha3.toString() -> TimorLeste
                    Togo.codeAlpha3.toString() -> Togo
                    Tokelau.codeAlpha3.toString() -> Tokelau
                    Tonga.codeAlpha3.toString() -> Tonga
                    TrinidadAndTobago.codeAlpha3.toString() -> TrinidadAndTobago
                    Tunisia.codeAlpha3.toString() -> Tunisia
                    Turkmenistan.codeAlpha3.toString() -> Turkmenistan
                    TurksAndCaicosIsland.codeAlpha3.toString() -> TurksAndCaicosIsland
                    Tuvalu.codeAlpha3.toString() -> Tuvalu
                    Türkiye.codeAlpha3.toString() -> Türkiye
                    Uganda.codeAlpha3.toString() -> Uganda
                    Ukraine.codeAlpha3.toString() -> Ukraine
                    UnitedArabEmirates.codeAlpha3.toString() -> UnitedArabEmirates
                    UnitedKingdomOfGreatBritainAndNorthernIreland.codeAlpha3.toString() -> UnitedKingdomOfGreatBritainAndNorthernIreland
                    UnitedStatesMinorOutlyingIslands.codeAlpha3.toString() -> UnitedStatesMinorOutlyingIslands
                    UnitedStatesOfAmerica.codeAlpha3.toString() -> UnitedStatesOfAmerica
                    Uruguay.codeAlpha3.toString() -> Uruguay
                    Uzbekistan.codeAlpha3.toString() -> Uzbekistan
                    Vanuatu.codeAlpha3.toString() -> Vanuatu
                    Venezuela.codeAlpha3.toString() -> Venezuela
                    VietNam.codeAlpha3.toString() -> VietNam
                    VirginIslandsBritish.codeAlpha3.toString() -> VirginIslandsBritish
                    VirginIslandsUS.codeAlpha3.toString() -> VirginIslandsUS
                    WallisAndFutuna.codeAlpha3.toString() -> WallisAndFutuna
                    WesternSahara.codeAlpha3.toString() -> WesternSahara
                    Yemen.codeAlpha3.toString() -> Yemen
                    Zambia.codeAlpha3.toString() -> Zambia
                    Zimbabwe.codeAlpha3.toString() -> Zimbabwe
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
