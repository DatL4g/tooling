package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object ÅlandIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AX")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ALA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(248)
}