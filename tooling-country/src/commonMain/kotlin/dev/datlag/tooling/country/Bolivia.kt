package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Bolivia : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BO")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BOL")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(68)
}