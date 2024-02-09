package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Greenland : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GL")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GRL")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(304)
}