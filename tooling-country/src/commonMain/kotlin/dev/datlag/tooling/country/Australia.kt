package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Australia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AU")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("AUS")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(36)
}