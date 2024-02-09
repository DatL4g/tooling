package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Finland : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("FI")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("FIN")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(246)
}