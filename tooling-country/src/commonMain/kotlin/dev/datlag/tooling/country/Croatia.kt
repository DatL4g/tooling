package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Croatia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("HR")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("HRV")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(191)
}