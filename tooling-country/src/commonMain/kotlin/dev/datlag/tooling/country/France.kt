package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object France : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("FR")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("FRA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(250)
}