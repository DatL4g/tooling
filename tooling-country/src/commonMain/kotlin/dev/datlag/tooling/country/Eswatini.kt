package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Eswatini : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("SZ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("SWZ")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(748)
}