package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Grenada : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GD")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GRD")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(308)
}