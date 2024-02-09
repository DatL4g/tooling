package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Czechia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CZ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CZE")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(203)
}