package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Chile : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CL")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CHL")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(152)
}