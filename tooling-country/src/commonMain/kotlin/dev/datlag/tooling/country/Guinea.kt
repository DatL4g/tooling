package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Guinea : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GN")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GIN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(324)
}