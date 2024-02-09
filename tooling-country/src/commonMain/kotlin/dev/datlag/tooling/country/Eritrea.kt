package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Eritrea : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("ER")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ERI")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(232)
}