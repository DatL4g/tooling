package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object Slovakia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("SK")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("SVK")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(703)
}