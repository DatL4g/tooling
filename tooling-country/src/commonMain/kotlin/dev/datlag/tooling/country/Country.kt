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
        fun forCodeOrNull(code: CharSequence): Country? {
            val trimmedCode = code.trim()

            fun parseAlpha2(): Country? {
                return trimmedCode.takeIf(Format.Alpha2::isValidFormat)?.let { validCode ->
                    when (validCode) {
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
                        Philippines.alpha2 -> Philippines
                        Pitcairn.alpha2 -> Pitcairn
                        Poland.alpha2 -> Poland
                        Portugal.alpha2 -> Portugal
                        PuertoRico.alpha2 -> PuertoRico
                        Qatar.alpha2 -> Qatar
                        Romania.alpha2 -> Romania
                        RussianFederation.alpha2 -> RussianFederation
                        Rwanda.alpha2 -> Rwanda
                        Réunion.alpha2 -> Réunion
                        SaintBarthélemy.alpha2 -> SaintBarthélemy
                        SaintHelena.alpha2 -> SaintHelena
                        SaintKittsAndNevis.alpha2 -> SaintKittsAndNevis
                        SaintLucia.alpha2 -> SaintLucia
                        SaintMartin.alpha2 -> SaintMartin
                        SaintPierreAndMiquelon.alpha2 -> SaintPierreAndMiquelon
                        SaintVincentAndTheGrenadines.alpha2 -> SaintVincentAndTheGrenadines
                        Samoa.alpha2 -> Samoa
                        SaoTomeAndPrincipe.alpha2 -> SaoTomeAndPrincipe
                        SaudiArabia.alpha2 -> SaudiArabia
                        Senegal.alpha2 -> Senegal
                        Serbia.alpha2 -> Serbia
                        Seychelles.alpha2 -> Seychelles
                        SierraLeone.alpha2 -> SierraLeone
                        Singapore.alpha2 -> Singapore
                        SintMaarten.alpha2 -> SintMaarten
                        Slovakia.alpha2 -> Slovakia
                        Slovenia.alpha2 -> Slovenia
                        SolomonIslands.alpha2 -> SolomonIslands
                        SouthAfrica.alpha2 -> SouthAfrica
                        SouthGeorgiaAndTheSouthSandwichIslands.alpha2 -> SouthGeorgiaAndTheSouthSandwichIslands
                        SouthSudan.alpha2 -> SouthSudan
                        Spain.alpha2 -> Spain
                        SriLanka.alpha2 -> SriLanka
                        Sudan.alpha2 -> Sudan
                        Suriname.alpha2 -> Suriname
                        SvalbardAndJanMayen.alpha2 -> SvalbardAndJanMayen
                        Sweden.alpha2 -> Sweden
                        Switzerland.alpha2 -> Switzerland
                        Syrian.alpha2 -> Syrian
                        Taiwan.alpha2 -> Taiwan
                        Tajikistan.alpha2 -> Tajikistan
                        Tanzania.alpha2 -> Tanzania
                        Thailand.alpha2 -> Thailand
                        TimorLeste.alpha2 -> TimorLeste
                        Togo.alpha2 -> Togo
                        Tokelau.alpha2 -> Tokelau
                        Tonga.alpha2 -> Tonga
                        TrinidadAndTobago.alpha2 -> TrinidadAndTobago
                        Tunisia.alpha2 -> Tunisia
                        Turkmenistan.alpha2 -> Turkmenistan
                        TurksAndCaicosIsland.alpha2 -> TurksAndCaicosIsland
                        Tuvalu.alpha2 -> Tuvalu
                        Türkiye.alpha2 -> Türkiye
                        Uganda.alpha2 -> Uganda
                        Ukraine.alpha2 -> Ukraine
                        UnitedArabEmirates.alpha2 -> UnitedArabEmirates
                        UnitedKingdomOfGreatBritainAndNorthernIreland.alpha2 -> UnitedKingdomOfGreatBritainAndNorthernIreland
                        UnitedStatesMinorOutlyingIslands.alpha2 -> UnitedStatesMinorOutlyingIslands
                        UnitedStatesOfAmerica.alpha2 -> UnitedStatesOfAmerica
                        Uruguay.alpha2 -> Uruguay
                        Uzbekistan.alpha2 -> Uzbekistan
                        Vanuatu.alpha2 -> Vanuatu
                        Venezuela.alpha2 -> Venezuela
                        VietNam.alpha2 -> VietNam
                        VirginIslandsBritish.alpha2 -> VirginIslandsBritish
                        VirginIslandsUS.alpha2 -> VirginIslandsUS
                        WallisAndFutuna.alpha2 -> WallisAndFutuna
                        WesternSahara.alpha2 -> WesternSahara
                        Yemen.alpha2 -> Yemen
                        Zambia.alpha2 -> Zambia
                        Zimbabwe.alpha2 -> Zimbabwe
                        else -> null
                    }
                }
            }

            fun parseAlpha3(): Country? {
                return trimmedCode.takeIf(Format.Alpha3::isValidFormat)?.let { validCode ->
                    when (validCode) {
                        Afghanistan.alpha3 -> Afghanistan
                        Albania.alpha3 -> Albania
                        Algeria.alpha3 -> Algeria
                        AmericanSamoa.alpha3 -> AmericanSamoa
                        Andorra.alpha3 -> Andorra
                        Angola.alpha3 -> Angola
                        Anguilla.alpha3 -> Anguilla
                        Antarctica.alpha3 -> Antarctica
                        AntiguaAndBarbuda.alpha3 -> AntiguaAndBarbuda
                        Argentina.alpha3 -> Argentina
                        Armenia.alpha3 -> Armenia
                        Aruba.alpha3 -> Aruba
                        Australia.alpha3 -> Australia
                        Austria.alpha3 -> Austria
                        Azerbaijan.alpha3 -> Azerbaijan
                        Bahamas.alpha3 -> Bahamas
                        Bahrain.alpha3 -> Bahrain
                        Bangladesh.alpha3 -> Bangladesh
                        Barbados.alpha3 -> Barbados
                        Belarus.alpha3 -> Belarus
                        Belgium.alpha3 -> Belgium
                        Belize.alpha3 -> Belize
                        Benin.alpha3 -> Benin
                        Bermuda.alpha3 -> Bermuda
                        ÅlandIslands.alpha3 -> ÅlandIslands
                        Bhutan.alpha3 -> Bhutan
                        Bolivia.alpha3 -> Bolivia
                        Bonaire.alpha3 -> Bonaire
                        BosniaAndHerzegovina.alpha3 -> BosniaAndHerzegovina
                        Botswana.alpha3 -> Botswana
                        BouvetIsland.alpha3 -> BouvetIsland
                        Brazil.alpha3 -> Brazil
                        BritishIndianOceanTerritory.alpha3 -> BritishIndianOceanTerritory
                        BruneiDarussalam.alpha3 -> BruneiDarussalam
                        Bulgaria.alpha3 -> Bulgaria
                        BurkinaFaso.alpha3 -> BurkinaFaso
                        Burundi.alpha3 -> Burundi
                        CaboVerde.alpha3 -> CaboVerde
                        Cambodia.alpha3 -> Cambodia
                        Cameroon.alpha3 -> Cameroon
                        Canada.alpha3 -> Canada
                        CaymanIslands.alpha3 -> CaymanIslands
                        CentralAfricanRepublic.alpha3 -> CentralAfricanRepublic
                        Chad.alpha3 -> Chad
                        Chile.alpha3 -> Chile
                        China.alpha3 -> China
                        ChristmasIsland.alpha3 -> ChristmasIsland
                        CocosIslands.alpha3 -> CocosIslands
                        Colombia.alpha3 -> Colombia
                        Comoros.alpha3 -> Comoros
                        DemocraticRepublicCongo.alpha3 -> DemocraticRepublicCongo
                        Congo.alpha3 -> Congo
                        CookIslands.alpha3 -> CookIslands
                        CostaRica.alpha3 -> CostaRica
                        Croatia.alpha3 -> Croatia
                        Cuba.alpha3 -> Cuba
                        Curaçao.alpha3 -> Curaçao
                        Cyprus.alpha3 -> Cyprus
                        Czechia.alpha3 -> Czechia
                        IvoryCoast.alpha3 -> IvoryCoast
                        Denmark.alpha3 -> Denmark
                        Djibouti.alpha3 -> Djibouti
                        Dominica.alpha3 -> Dominica
                        DominicanRepublic.alpha3 -> DominicanRepublic
                        Ecuador.alpha3 -> Ecuador
                        Egypt.alpha3 -> Egypt
                        ElSalvador.alpha3 -> ElSalvador
                        EquatorialGuinea.alpha3 -> EquatorialGuinea
                        Eritrea.alpha3 -> Eritrea
                        Estonia.alpha3 -> Estonia
                        Eswatini.alpha3 -> Eswatini
                        Ethiopia.alpha3 -> Ethiopia
                        FalklandIslands.alpha3 -> FalklandIslands
                        FaroeIslands.alpha3 -> FaroeIslands
                        Fiji.alpha3 -> Fiji
                        Finland.alpha3 -> Finland
                        France.alpha3 -> France
                        FrenchGuiana.alpha3 -> FrenchGuiana
                        FrenchPolynesia.alpha3 -> FrenchPolynesia
                        FrenchSouthernTerritories.alpha3 -> FrenchSouthernTerritories
                        Gabon.alpha3 -> Gabon
                        Gambia.alpha3 -> Gambia
                        Georgia.alpha3 -> Georgia
                        Germany.alpha3 -> Germany
                        Ghana.alpha3 -> Ghana
                        Gibraltar.alpha3 -> Gibraltar
                        Greece.alpha3 -> Greece
                        Greenland.alpha3 -> Greenland
                        Grenada.alpha3 -> Grenada
                        Guadeloupe.alpha3 -> Guadeloupe
                        Guam.alpha3 -> Guam
                        Guatemala.alpha3 -> Guatemala
                        Guernsey.alpha3 -> Guernsey
                        Guinea.alpha3 -> Guinea
                        GuineaBissau.alpha3 -> GuineaBissau
                        Guyana.alpha3 -> Guyana
                        Haiti.alpha3 -> Haiti
                        HeardIslandAndMcDonaldIslands.alpha3 -> HeardIslandAndMcDonaldIslands
                        HolySee.alpha3 -> HolySee
                        Honduras.alpha3 -> Honduras
                        HongKong.alpha3 -> HongKong
                        Hungary.alpha3 -> Hungary
                        Iceland.alpha3 -> Iceland
                        India.alpha3 -> India
                        Indonesia.alpha3 -> Indonesia
                        Iran.alpha3 -> Iran
                        Iraq.alpha3 -> Iraq
                        Ireland.alpha3 -> Ireland
                        IsleOfMan.alpha3 -> IsleOfMan
                        Israel.alpha3 -> Israel
                        Italy.alpha3 -> Italy
                        Jamaica.alpha3 -> Jamaica
                        Japan.alpha3 -> Japan
                        Jersey.alpha3 -> Jersey
                        Jordan.alpha3 -> Jordan
                        Kazakhstan.alpha3 -> Kazakhstan
                        Kenya.alpha3 -> Kenya
                        Kiribati.alpha3 -> Kiribati
                        NorthKorea.alpha3 -> NorthKorea
                        SouthKorea.alpha3 -> SouthKorea
                        Kuwait.alpha3 -> Kuwait
                        Kyrgyzstan.alpha3 -> Kyrgyzstan
                        Lao.alpha3 -> Lao
                        Latvia.alpha3 -> Latvia
                        Lebanon.alpha3 -> Lebanon
                        Lesotho.alpha3 -> Lesotho
                        Liberia.alpha3 -> Liberia
                        Libya.alpha3 -> Libya
                        Liechtenstein.alpha3 -> Liechtenstein
                        Lithuania.alpha3 -> Lithuania
                        Luxembourg.alpha3 -> Luxembourg
                        Macao.alpha3 -> Macao
                        Madagascar.alpha3 -> Madagascar
                        Malawi.alpha3 -> Malawi
                        Malaysia.alpha3 -> Malaysia
                        Maldives.alpha3 -> Maldives
                        Mali.alpha3 -> Mali
                        Malta.alpha3 -> Malta
                        MarshallIslands.alpha3 -> MarshallIslands
                        Martinique.alpha3 -> Martinique
                        Mauritania.alpha3 -> Mauritania
                        Mauritius.alpha3 -> Mauritius
                        Mayotte.alpha3 -> Mayotte
                        Mexico.alpha3 -> Mexico
                        Micronesia.alpha3 -> Micronesia
                        Moldova.alpha3 -> Moldova
                        Monaco.alpha3 -> Monaco
                        Mongolia.alpha3 -> Mongolia
                        Montenegro.alpha3 -> Montenegro
                        Montserrat.alpha3 -> Montserrat
                        Morocco.alpha3 -> Morocco
                        Mozambique.alpha3 -> Mozambique
                        Myanmar.alpha3 -> Myanmar
                        Namibia.alpha3 -> Namibia
                        Nauru.alpha3 -> Nauru
                        Nepal.alpha3 -> Nepal
                        Netherlands.alpha3 -> Netherlands
                        NewCaledonia.alpha3 -> NewCaledonia
                        NewZealand.alpha3 -> NewZealand
                        Nicaragua.alpha3 -> Nicaragua
                        Niger.alpha3 -> Niger
                        Nigeria.alpha3 -> Nigeria
                        Niue.alpha3 -> Niue
                        NorfolkIsland.alpha3 -> NorfolkIsland
                        NorthMacedonia.alpha3 -> NorthMacedonia
                        NorthernMarianaIslands.alpha3 -> NorthernMarianaIslands
                        Norway.alpha3 -> Norway
                        Oman.alpha3 -> Oman
                        Pakistan.alpha3 -> Pakistan
                        Palau.alpha3 -> Palau
                        Palestine.alpha3 -> Palestine
                        Panama.alpha3 -> Panama
                        PapuaNewGuinea.alpha3 -> PapuaNewGuinea
                        Paraguay.alpha3 -> Paraguay
                        Peru.alpha3 -> Peru
                        Philippines.alpha3 -> Philippines
                        Pitcairn.alpha3 -> Pitcairn
                        Poland.alpha3 -> Poland
                        Portugal.alpha3 -> Portugal
                        PuertoRico.alpha3 -> PuertoRico
                        Qatar.alpha3 -> Qatar
                        Romania.alpha3 -> Romania
                        RussianFederation.alpha3 -> RussianFederation
                        Rwanda.alpha3 -> Rwanda
                        Réunion.alpha3 -> Réunion
                        SaintBarthélemy.alpha3 -> SaintBarthélemy
                        SaintHelena.alpha3 -> SaintHelena
                        SaintKittsAndNevis.alpha3 -> SaintKittsAndNevis
                        SaintLucia.alpha3 -> SaintLucia
                        SaintMartin.alpha3 -> SaintMartin
                        SaintPierreAndMiquelon.alpha3 -> SaintPierreAndMiquelon
                        SaintVincentAndTheGrenadines.alpha3 -> SaintVincentAndTheGrenadines
                        Samoa.alpha3 -> Samoa
                        SaoTomeAndPrincipe.alpha3 -> SaoTomeAndPrincipe
                        SaudiArabia.alpha3 -> SaudiArabia
                        Senegal.alpha3 -> Senegal
                        Serbia.alpha3 -> Serbia
                        Seychelles.alpha3 -> Seychelles
                        SierraLeone.alpha3 -> SierraLeone
                        Singapore.alpha3 -> Singapore
                        SintMaarten.alpha3 -> SintMaarten
                        Slovakia.alpha3 -> Slovakia
                        Slovenia.alpha3 -> Slovenia
                        SolomonIslands.alpha3 -> SolomonIslands
                        SouthAfrica.alpha3 -> SouthAfrica
                        SouthGeorgiaAndTheSouthSandwichIslands.alpha3 -> SouthGeorgiaAndTheSouthSandwichIslands
                        SouthSudan.alpha3 -> SouthSudan
                        Spain.alpha3 -> Spain
                        SriLanka.alpha3 -> SriLanka
                        Sudan.alpha3 -> Sudan
                        Suriname.alpha3 -> Suriname
                        SvalbardAndJanMayen.alpha3 -> SvalbardAndJanMayen
                        Sweden.alpha3 -> Sweden
                        Switzerland.alpha3 -> Switzerland
                        Syrian.alpha3 -> Syrian
                        Taiwan.alpha3 -> Taiwan
                        Tajikistan.alpha3 -> Tajikistan
                        Tanzania.alpha3 -> Tanzania
                        Thailand.alpha3 -> Thailand
                        TimorLeste.alpha3 -> TimorLeste
                        Togo.alpha3 -> Togo
                        Tokelau.alpha3 -> Tokelau
                        Tonga.alpha3 -> Tonga
                        TrinidadAndTobago.alpha3 -> TrinidadAndTobago
                        Tunisia.alpha3 -> Tunisia
                        Turkmenistan.alpha3 -> Turkmenistan
                        TurksAndCaicosIsland.alpha3 -> TurksAndCaicosIsland
                        Tuvalu.alpha3 -> Tuvalu
                        Türkiye.alpha3 -> Türkiye
                        Uganda.alpha3 -> Uganda
                        Ukraine.alpha3 -> Ukraine
                        UnitedArabEmirates.alpha3 -> UnitedArabEmirates
                        UnitedKingdomOfGreatBritainAndNorthernIreland.alpha3 -> UnitedKingdomOfGreatBritainAndNorthernIreland
                        UnitedStatesMinorOutlyingIslands.alpha3 -> UnitedStatesMinorOutlyingIslands
                        UnitedStatesOfAmerica.alpha3 -> UnitedStatesOfAmerica
                        Uruguay.alpha3 -> Uruguay
                        Uzbekistan.alpha3 -> Uzbekistan
                        Vanuatu.alpha3 -> Vanuatu
                        Venezuela.alpha3 -> Venezuela
                        VietNam.alpha3 -> VietNam
                        VirginIslandsBritish.alpha3 -> VirginIslandsBritish
                        VirginIslandsUS.alpha3 -> VirginIslandsUS
                        WallisAndFutuna.alpha3 -> WallisAndFutuna
                        WesternSahara.alpha3 -> WesternSahara
                        Yemen.alpha3 -> Yemen
                        Zambia.alpha3 -> Zambia
                        Zimbabwe.alpha3 -> Zimbabwe
                        else -> null
                    }
                }
            }

            return trimmedCode.toString().toIntOrNull()?.let(::forCodeOrNull) ?: parseAlpha3() ?: parseAlpha2()
        }

        /**
         * Parse numeric country code to a country object.
         *
         * @param code country code following numeric [ISO 3166 Format][Format.Numeric]
         * @return [Country] object or null
         */
        @JvmStatic
        fun forCodeOrNull(code: Int): Country? {
            return when (code) {
                Afghanistan.numeric.toInt() -> Afghanistan
                Albania.numeric.toInt() -> Albania
                Algeria.numeric.toInt() -> Algeria
                AmericanSamoa.numeric.toInt() -> AmericanSamoa
                Andorra.numeric.toInt() -> Andorra
                Angola.numeric.toInt() -> Angola
                Anguilla.numeric.toInt() -> Anguilla
                Antarctica.numeric.toInt() -> Antarctica
                AntiguaAndBarbuda.numeric.toInt() -> AntiguaAndBarbuda
                Argentina.numeric.toInt() -> Argentina
                Armenia.numeric.toInt() -> Armenia
                Aruba.numeric.toInt() -> Aruba
                Australia.numeric.toInt() -> Australia
                Austria.numeric.toInt() -> Austria
                Azerbaijan.numeric.toInt() -> Azerbaijan
                Bahamas.numeric.toInt() -> Bahamas
                Bahrain.numeric.toInt() -> Bahrain
                Bangladesh.numeric.toInt() -> Bangladesh
                Barbados.numeric.toInt() -> Barbados
                Belarus.numeric.toInt() -> Belarus
                Belgium.numeric.toInt() -> Belgium
                Belize.numeric.toInt() -> Belize
                Benin.numeric.toInt() -> Benin
                Bermuda.numeric.toInt() -> Bermuda
                ÅlandIslands.numeric.toInt() -> ÅlandIslands
                Bhutan.numeric.toInt() -> Bhutan
                Bolivia.numeric.toInt() -> Bolivia
                Bonaire.numeric.toInt() -> Bonaire
                BosniaAndHerzegovina.numeric.toInt() -> BosniaAndHerzegovina
                Botswana.numeric.toInt() -> Botswana
                BouvetIsland.numeric.toInt() -> BouvetIsland
                Brazil.numeric.toInt() -> Brazil
                BritishIndianOceanTerritory.numeric.toInt() -> BritishIndianOceanTerritory
                BruneiDarussalam.numeric.toInt() -> BruneiDarussalam
                Bulgaria.numeric.toInt() -> Bulgaria
                BurkinaFaso.numeric.toInt() -> BurkinaFaso
                Burundi.numeric.toInt() -> Burundi
                CaboVerde.numeric.toInt() -> CaboVerde
                Cambodia.numeric.toInt() -> Cambodia
                Cameroon.numeric.toInt() -> Cameroon
                Canada.numeric.toInt() -> Canada
                CaymanIslands.numeric.toInt() -> CaymanIslands
                CentralAfricanRepublic.numeric.toInt() -> CentralAfricanRepublic
                Chad.numeric.toInt() -> Chad
                Chile.numeric.toInt() -> Chile
                China.numeric.toInt() -> China
                ChristmasIsland.numeric.toInt() -> ChristmasIsland
                CocosIslands.numeric.toInt() -> CocosIslands
                Colombia.numeric.toInt() -> Colombia
                Comoros.numeric.toInt() -> Comoros
                DemocraticRepublicCongo.numeric.toInt() -> DemocraticRepublicCongo
                Congo.numeric.toInt() -> Congo
                CookIslands.numeric.toInt() -> CookIslands
                CostaRica.numeric.toInt() -> CostaRica
                Croatia.numeric.toInt() -> Croatia
                Cuba.numeric.toInt() -> Cuba
                Curaçao.numeric.toInt() -> Curaçao
                Cyprus.numeric.toInt() -> Cyprus
                Czechia.numeric.toInt() -> Czechia
                IvoryCoast.numeric.toInt() -> IvoryCoast
                Denmark.numeric.toInt() -> Denmark
                Djibouti.numeric.toInt() -> Djibouti
                Dominica.numeric.toInt() -> Dominica
                DominicanRepublic.numeric.toInt() -> DominicanRepublic
                Ecuador.numeric.toInt() -> Ecuador
                Egypt.numeric.toInt() -> Egypt
                ElSalvador.numeric.toInt() -> ElSalvador
                EquatorialGuinea.numeric.toInt() -> EquatorialGuinea
                Eritrea.numeric.toInt() -> Eritrea
                Estonia.numeric.toInt() -> Estonia
                Eswatini.numeric.toInt() -> Eswatini
                Ethiopia.numeric.toInt() -> Ethiopia
                FalklandIslands.numeric.toInt() -> FalklandIslands
                FaroeIslands.numeric.toInt() -> FaroeIslands
                Fiji.numeric.toInt() -> Fiji
                Finland.numeric.toInt() -> Finland
                France.numeric.toInt() -> France
                FrenchGuiana.numeric.toInt() -> FrenchGuiana
                FrenchPolynesia.numeric.toInt() -> FrenchPolynesia
                FrenchSouthernTerritories.numeric.toInt() -> FrenchSouthernTerritories
                Gabon.numeric.toInt() -> Gabon
                Gambia.numeric.toInt() -> Gambia
                Georgia.numeric.toInt() -> Georgia
                Germany.numeric.toInt() -> Germany
                Ghana.numeric.toInt() -> Ghana
                Gibraltar.numeric.toInt() -> Gibraltar
                Greece.numeric.toInt() -> Greece
                Greenland.numeric.toInt() -> Greenland
                Grenada.numeric.toInt() -> Grenada
                Guadeloupe.numeric.toInt() -> Guadeloupe
                Guam.numeric.toInt() -> Guam
                Guatemala.numeric.toInt() -> Guatemala
                Guernsey.numeric.toInt() -> Guernsey
                Guinea.numeric.toInt() -> Guinea
                GuineaBissau.numeric.toInt() -> GuineaBissau
                Guyana.numeric.toInt() -> Guyana
                Haiti.numeric.toInt() -> Haiti
                HeardIslandAndMcDonaldIslands.numeric.toInt() -> HeardIslandAndMcDonaldIslands
                HolySee.numeric.toInt() -> HolySee
                Honduras.numeric.toInt() -> Honduras
                HongKong.numeric.toInt() -> HongKong
                Hungary.numeric.toInt() -> Hungary
                Iceland.numeric.toInt() -> Iceland
                India.numeric.toInt() -> India
                Indonesia.numeric.toInt() -> Indonesia
                Iran.numeric.toInt() -> Iran
                Iraq.numeric.toInt() -> Iraq
                Ireland.numeric.toInt() -> Ireland
                IsleOfMan.numeric.toInt() -> IsleOfMan
                Israel.numeric.toInt() -> Israel
                Italy.numeric.toInt() -> Italy
                Jamaica.numeric.toInt() -> Jamaica
                Japan.numeric.toInt() -> Japan
                Jersey.numeric.toInt() -> Jersey
                Jordan.numeric.toInt() -> Jordan
                Kazakhstan.numeric.toInt() -> Kazakhstan
                Kenya.numeric.toInt() -> Kenya
                Kiribati.numeric.toInt() -> Kiribati
                NorthKorea.numeric.toInt() -> NorthKorea
                SouthKorea.numeric.toInt() -> SouthKorea
                Kuwait.numeric.toInt() -> Kuwait
                Kyrgyzstan.numeric.toInt() -> Kyrgyzstan
                Lao.numeric.toInt() -> Lao
                Latvia.numeric.toInt() -> Latvia
                Lebanon.numeric.toInt() -> Lebanon
                Lesotho.numeric.toInt() -> Lesotho
                Liberia.numeric.toInt() -> Liberia
                Libya.numeric.toInt() -> Libya
                Liechtenstein.numeric.toInt() -> Liechtenstein
                Lithuania.numeric.toInt() -> Lithuania
                Luxembourg.numeric.toInt() -> Luxembourg
                Macao.numeric.toInt() -> Macao
                Madagascar.numeric.toInt() -> Madagascar
                Malawi.numeric.toInt() -> Malawi
                Malaysia.numeric.toInt() -> Malaysia
                Maldives.numeric.toInt() -> Maldives
                Mali.numeric.toInt() -> Mali
                Malta.numeric.toInt() -> Malta
                MarshallIslands.numeric.toInt() -> MarshallIslands
                Martinique.numeric.toInt() -> Martinique
                Mauritania.numeric.toInt() -> Mauritania
                Mauritius.numeric.toInt() -> Mauritius
                Mayotte.numeric.toInt() -> Mayotte
                Mexico.numeric.toInt() -> Mexico
                Micronesia.numeric.toInt() -> Micronesia
                Moldova.numeric.toInt() -> Moldova
                Monaco.numeric.toInt() -> Monaco
                Mongolia.numeric.toInt() -> Mongolia
                Montenegro.numeric.toInt() -> Montenegro
                Montserrat.numeric.toInt() -> Montserrat
                Morocco.numeric.toInt() -> Morocco
                Mozambique.numeric.toInt() -> Mozambique
                Myanmar.numeric.toInt() -> Myanmar
                Namibia.numeric.toInt() -> Namibia
                Nauru.numeric.toInt() -> Nauru
                Nepal.numeric.toInt() -> Nepal
                Netherlands.numeric.toInt() -> Netherlands
                NewCaledonia.numeric.toInt() -> NewCaledonia
                NewZealand.numeric.toInt() -> NewZealand
                Nicaragua.numeric.toInt() -> Nicaragua
                Niger.numeric.toInt() -> Niger
                Nigeria.numeric.toInt() -> Nigeria
                Niue.numeric.toInt() -> Niue
                NorfolkIsland.numeric.toInt() -> NorfolkIsland
                NorthMacedonia.numeric.toInt() -> NorthMacedonia
                NorthernMarianaIslands.numeric.toInt() -> NorthernMarianaIslands
                Norway.numeric.toInt() -> Norway
                Oman.numeric.toInt() -> Oman
                Pakistan.numeric.toInt() -> Pakistan
                Palau.numeric.toInt() -> Palau
                Palestine.numeric.toInt() -> Palestine
                Panama.numeric.toInt() -> Panama
                PapuaNewGuinea.numeric.toInt() -> PapuaNewGuinea
                Paraguay.numeric.toInt() -> Paraguay
                Peru.numeric.toInt() -> Peru
                Philippines.numeric.toInt() -> Philippines
                Pitcairn.numeric.toInt() -> Pitcairn
                Poland.numeric.toInt() -> Poland
                Portugal.numeric.toInt() -> Portugal
                PuertoRico.numeric.toInt() -> PuertoRico
                Qatar.numeric.toInt() -> Qatar
                Romania.numeric.toInt() -> Romania
                RussianFederation.numeric.toInt() -> RussianFederation
                Rwanda.numeric.toInt() -> Rwanda
                Réunion.numeric.toInt() -> Réunion
                SaintBarthélemy.numeric.toInt() -> SaintBarthélemy
                SaintHelena.numeric.toInt() -> SaintHelena
                SaintKittsAndNevis.numeric.toInt() -> SaintKittsAndNevis
                SaintLucia.numeric.toInt() -> SaintLucia
                SaintMartin.numeric.toInt() -> SaintMartin
                SaintPierreAndMiquelon.numeric.toInt() -> SaintPierreAndMiquelon
                SaintVincentAndTheGrenadines.numeric.toInt() -> SaintVincentAndTheGrenadines
                Samoa.numeric.toInt() -> Samoa
                SaoTomeAndPrincipe.numeric.toInt() -> SaoTomeAndPrincipe
                SaudiArabia.numeric.toInt() -> SaudiArabia
                Senegal.numeric.toInt() -> Senegal
                Serbia.numeric.toInt() -> Serbia
                Seychelles.numeric.toInt() -> Seychelles
                SierraLeone.numeric.toInt() -> SierraLeone
                Singapore.numeric.toInt() -> Singapore
                SintMaarten.numeric.toInt() -> SintMaarten
                Slovakia.numeric.toInt() -> Slovakia
                Slovenia.numeric.toInt() -> Slovenia
                SolomonIslands.numeric.toInt() -> SolomonIslands
                SouthAfrica.numeric.toInt() -> SouthAfrica
                SouthGeorgiaAndTheSouthSandwichIslands.numeric.toInt() -> SouthGeorgiaAndTheSouthSandwichIslands
                SouthSudan.numeric.toInt() -> SouthSudan
                Spain.numeric.toInt() -> Spain
                SriLanka.numeric.toInt() -> SriLanka
                Sudan.numeric.toInt() -> Sudan
                Suriname.numeric.toInt() -> Suriname
                SvalbardAndJanMayen.numeric.toInt() -> SvalbardAndJanMayen
                Sweden.numeric.toInt() -> Sweden
                Switzerland.numeric.toInt() -> Switzerland
                Syrian.numeric.toInt() -> Syrian
                Taiwan.numeric.toInt() -> Taiwan
                Tajikistan.numeric.toInt() -> Tajikistan
                Tanzania.numeric.toInt() -> Tanzania
                Thailand.numeric.toInt() -> Thailand
                TimorLeste.numeric.toInt() -> TimorLeste
                Togo.numeric.toInt() -> Togo
                Tokelau.numeric.toInt() -> Tokelau
                Tonga.numeric.toInt() -> Tonga
                TrinidadAndTobago.numeric.toInt() -> TrinidadAndTobago
                Tunisia.numeric.toInt() -> Tunisia
                Turkmenistan.numeric.toInt() -> Turkmenistan
                TurksAndCaicosIsland.numeric.toInt() -> TurksAndCaicosIsland
                Tuvalu.numeric.toInt() -> Tuvalu
                Türkiye.numeric.toInt() -> Türkiye
                Uganda.numeric.toInt() -> Uganda
                Ukraine.numeric.toInt() -> Ukraine
                UnitedArabEmirates.numeric.toInt() -> UnitedArabEmirates
                UnitedKingdomOfGreatBritainAndNorthernIreland.numeric.toInt() -> UnitedKingdomOfGreatBritainAndNorthernIreland
                UnitedStatesMinorOutlyingIslands.numeric.toInt() -> UnitedStatesMinorOutlyingIslands
                UnitedStatesOfAmerica.numeric.toInt() -> UnitedStatesOfAmerica
                Uruguay.numeric.toInt() -> Uruguay
                Uzbekistan.numeric.toInt() -> Uzbekistan
                Vanuatu.numeric.toInt() -> Vanuatu
                Venezuela.numeric.toInt() -> Venezuela
                VietNam.numeric.toInt() -> VietNam
                VirginIslandsBritish.numeric.toInt() -> VirginIslandsBritish
                VirginIslandsUS.numeric.toInt() -> VirginIslandsUS
                WallisAndFutuna.numeric.toInt() -> WallisAndFutuna
                WesternSahara.numeric.toInt() -> WesternSahara
                Yemen.numeric.toInt() -> Yemen
                Zambia.numeric.toInt() -> Zambia
                Zimbabwe.numeric.toInt() -> Zimbabwe
                else -> null
            }
        }
    }
}
