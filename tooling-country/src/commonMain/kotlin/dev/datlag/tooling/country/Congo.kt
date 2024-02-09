package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Congo : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CG")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("COG")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(178)
}