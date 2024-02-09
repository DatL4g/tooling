package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object DemocraticRepublicCongo : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CD")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("COD")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(180)
}