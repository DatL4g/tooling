package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object CocosIslands : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("CC")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("CCK")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(166)
}