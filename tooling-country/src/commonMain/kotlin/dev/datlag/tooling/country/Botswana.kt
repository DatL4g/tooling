package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Botswana : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BW")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BWA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(72)
}