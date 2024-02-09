package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Curaçao : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CW")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CUW")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(531)
}