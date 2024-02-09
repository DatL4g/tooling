package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Anguilla : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AI")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("AIA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(660)
}