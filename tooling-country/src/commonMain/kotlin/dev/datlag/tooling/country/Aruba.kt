package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Aruba : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AW")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ABW")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(533)
}