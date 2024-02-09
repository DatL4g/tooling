package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object IvoryCoast : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CI")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CIV")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(384)
}