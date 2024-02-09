package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Fiji : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("FJ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("FJI")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(242)
}