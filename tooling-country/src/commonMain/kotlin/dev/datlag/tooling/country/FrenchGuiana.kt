package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object FrenchGuiana : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GF")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GUF")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(254)
}