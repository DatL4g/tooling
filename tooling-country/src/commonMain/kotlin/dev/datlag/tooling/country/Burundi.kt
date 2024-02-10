package dev.datlag.tooling.country


data object Burundi : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BI")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BDI")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(108)
}