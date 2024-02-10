package dev.datlag.tooling.country


data object Guyana : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GY")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GUY")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(328)
}