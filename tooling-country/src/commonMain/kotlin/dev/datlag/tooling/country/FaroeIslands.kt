package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object FaroeIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("FO")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("FRO")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(234)
}