package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Djibouti : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("DJ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("DJI")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(262)
}