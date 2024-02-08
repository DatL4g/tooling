package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Afghanistan : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AF")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("AFG")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(4)
}