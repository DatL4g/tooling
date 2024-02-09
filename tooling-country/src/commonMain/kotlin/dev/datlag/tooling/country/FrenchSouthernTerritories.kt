package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object FrenchSouthernTerritories : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("TF")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ATF")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(260)
}