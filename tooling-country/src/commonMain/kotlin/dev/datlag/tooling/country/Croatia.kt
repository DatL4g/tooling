package dev.datlag.tooling.country


data object Croatia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("HR")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("HRV")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(191)
}