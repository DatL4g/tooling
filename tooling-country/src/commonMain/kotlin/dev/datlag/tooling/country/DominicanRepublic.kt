package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object DominicanRepublic : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("DO")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("DOM")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(214)
}