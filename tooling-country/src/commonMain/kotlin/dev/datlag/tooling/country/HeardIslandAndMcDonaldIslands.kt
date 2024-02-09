package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object HeardIslandAndMcDonaldIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("HM")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("HMD")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(334)
}