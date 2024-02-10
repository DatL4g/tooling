package dev.datlag.tooling.country


data object Lesotho : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("LS")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("LSO")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(426)
}