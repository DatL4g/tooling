package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable


data object Bangladesh : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BD")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BGD")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(50)
}