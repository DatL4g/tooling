package dev.datlag.tooling.country


data object Tuvalu : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("TV")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("TUV")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(798)
}