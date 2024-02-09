package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object EquatorialGuinea : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GQ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GNQ")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(226)
}