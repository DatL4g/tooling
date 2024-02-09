package dev.datlag.tooling.country

import dev.datlag.tooling.country.serializer.CountryAsAlpha2StringSerializer
import kotlinx.serialization.Serializable

@Serializable(CountryAsAlpha2StringSerializer::class)
data object AntiguaAndBarbuda : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AG")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ATG")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(28)
}