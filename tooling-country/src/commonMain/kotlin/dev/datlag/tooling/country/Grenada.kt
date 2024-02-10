package dev.datlag.tooling.country


data object Grenada : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GD")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GRD")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(308)
}