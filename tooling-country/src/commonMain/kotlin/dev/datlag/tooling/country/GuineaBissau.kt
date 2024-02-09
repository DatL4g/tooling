package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object GuineaBissau : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("GW")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("GNB")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(624)
}