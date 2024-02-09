package dev.datlag.tooling.country

import kotlinx.serialization.Serializable

@Serializable
data object Argentina : Country {
    override val alpha2: Country.Format.Alpha2 = Country.Format.Alpha2("AR")
    override val alpha3: Country.Format.Alpha3 = Country.Format.Alpha3("ARG")
    override val numeric: Country.Format.Numeric = Country.Format.Numeric(32)
}