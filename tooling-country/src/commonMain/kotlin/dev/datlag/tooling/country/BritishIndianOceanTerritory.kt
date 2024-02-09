package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object BritishIndianOceanTerritory : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("IO")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("IOT")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(86)
}