package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object BurkinaFaso : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BF")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BFA")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(854)
}