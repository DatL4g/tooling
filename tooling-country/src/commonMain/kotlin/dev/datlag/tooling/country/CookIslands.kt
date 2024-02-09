package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object CookIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CK")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("COK")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(184)
}