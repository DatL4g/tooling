package dev.datlag.tooling.country


data object Georgia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GEO")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(268)
}