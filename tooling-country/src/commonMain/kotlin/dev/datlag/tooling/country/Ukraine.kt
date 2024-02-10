package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable


data object Ukraine : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("UA")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("UKR")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(804)
}