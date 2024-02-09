package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Bulgaria : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BG")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BGR")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(100)
}