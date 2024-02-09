package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Ecuador : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("EC")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ECU")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(218)
}