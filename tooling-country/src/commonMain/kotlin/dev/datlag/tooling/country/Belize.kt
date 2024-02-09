package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Belize : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BZ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BLZ")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(84)
}