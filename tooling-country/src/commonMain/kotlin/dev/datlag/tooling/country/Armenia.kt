package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Armenia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AM")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ARM")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(51)
}