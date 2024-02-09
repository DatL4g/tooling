package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Cambodia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("KH")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("KHM")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(116)
}