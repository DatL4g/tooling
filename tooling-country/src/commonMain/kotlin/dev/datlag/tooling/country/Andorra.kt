package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Andorra : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AD")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("AND")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(20)
}