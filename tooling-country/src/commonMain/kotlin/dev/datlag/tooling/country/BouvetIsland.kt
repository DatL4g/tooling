package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object BouvetIsland : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BV")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BVT")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(74)
}