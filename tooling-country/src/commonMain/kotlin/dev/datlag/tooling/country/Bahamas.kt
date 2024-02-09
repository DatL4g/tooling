package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Bahamas : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BS")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BHS")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(44)
}