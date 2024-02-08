package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Algeria : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("DZ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("DZA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(12)
}