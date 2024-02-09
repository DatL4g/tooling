package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Greece : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GR")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GRC")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(300)
}