package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object Iran : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("IR")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("IRN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(364)
}