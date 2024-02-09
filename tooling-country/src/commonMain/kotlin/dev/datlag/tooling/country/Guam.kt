package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Guam : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GU")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GUM")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(316)
}