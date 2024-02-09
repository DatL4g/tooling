package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Belgium : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BE")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BEL")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(56)
}