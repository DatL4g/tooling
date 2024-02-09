package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object MarshallIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("MH")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("MHL")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(584)
}