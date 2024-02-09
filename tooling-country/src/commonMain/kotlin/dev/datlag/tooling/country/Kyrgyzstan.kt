package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object Kyrgyzstan : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("KG")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("KGZ")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(417)
}