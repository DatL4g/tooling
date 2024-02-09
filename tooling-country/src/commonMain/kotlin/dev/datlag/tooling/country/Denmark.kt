package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Denmark : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("DK")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("DNK")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(208)
}