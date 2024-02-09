package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Ghana : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GH")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GHA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(288)
}