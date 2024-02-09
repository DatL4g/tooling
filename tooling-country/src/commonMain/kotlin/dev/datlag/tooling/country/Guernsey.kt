package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Guernsey : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GG")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GGY")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(831)
}