package dev.datlag.tooling.country


data object Libya : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("LY")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("LBY")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(434)
}