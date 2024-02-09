package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Georgia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GE")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GEO")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(268)
}