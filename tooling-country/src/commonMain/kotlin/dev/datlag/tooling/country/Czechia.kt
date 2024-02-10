package dev.datlag.tooling.country


data object Czechia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("CZ")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("CZE")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(203)
}