package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Benin : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BJ")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BEN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(204)
}