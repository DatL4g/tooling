package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Angola : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AO")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("AGO")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(24)
}