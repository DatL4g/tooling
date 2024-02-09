package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Bonaire : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BQ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BES")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(535)
}