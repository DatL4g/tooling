package dev.datlag.tooling.country


data object Gambia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GM")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GMB")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(270)
}