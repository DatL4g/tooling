package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Colombia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CO")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("COL")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(170)
}