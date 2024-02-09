package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object FalklandIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("FK")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("FLK")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(238)
}