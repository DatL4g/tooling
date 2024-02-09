package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Belarus : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BY")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BLR")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(112)
}