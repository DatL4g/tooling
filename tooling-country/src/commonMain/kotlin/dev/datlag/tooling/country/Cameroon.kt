package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Cameroon : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CM")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CMR")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(120)
}