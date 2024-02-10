package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable


data object Tanzania : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("TZ")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("TZA")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(834)
}