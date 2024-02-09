package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object CaboVerde : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CV")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CPV")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(132)
}