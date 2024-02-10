package dev.datlag.tooling.country


data object Curaçao : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("CW")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("CUW")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(531)
}