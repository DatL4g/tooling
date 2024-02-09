package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Comoros : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("KM")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("COM")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(174)
}