package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Gambia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GM")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GMB")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(270)
}