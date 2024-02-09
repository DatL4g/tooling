package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Estonia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("EE")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("EST")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(233)
}