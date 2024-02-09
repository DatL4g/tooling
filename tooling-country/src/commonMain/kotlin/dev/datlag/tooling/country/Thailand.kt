package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object Thailand : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("TH")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("THA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(764)
}