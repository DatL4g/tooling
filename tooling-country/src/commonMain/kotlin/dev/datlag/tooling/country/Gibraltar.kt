package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Gibraltar : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GI")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GIB")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(292)
}