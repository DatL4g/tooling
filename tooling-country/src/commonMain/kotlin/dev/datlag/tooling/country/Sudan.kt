package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object Sudan : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("SD")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("SDN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(729)
}