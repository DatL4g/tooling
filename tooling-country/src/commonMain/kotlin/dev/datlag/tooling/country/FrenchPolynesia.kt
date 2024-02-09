package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object FrenchPolynesia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("PF")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("PYF")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(258)
}