package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Albania : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AL")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ALB")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(8)
}