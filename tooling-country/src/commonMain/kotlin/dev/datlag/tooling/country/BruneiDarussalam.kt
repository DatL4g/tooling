package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object BruneiDarussalam : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("BN")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("BRN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(96)
}