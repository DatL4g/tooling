package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Bangladesh : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BD")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BGD")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(50)
}