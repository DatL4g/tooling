package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable


data object Netherlands : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("NL")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("NLD")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(528)
}