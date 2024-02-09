package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Canada : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CA")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CAN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(124)
}