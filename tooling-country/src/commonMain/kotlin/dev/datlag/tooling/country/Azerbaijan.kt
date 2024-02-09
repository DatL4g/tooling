package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Azerbaijan : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AZ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("AZE")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(31)
}