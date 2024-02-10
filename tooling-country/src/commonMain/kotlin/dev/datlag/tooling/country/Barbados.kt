package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable


data object Barbados : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BB")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BRB")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(52)
}