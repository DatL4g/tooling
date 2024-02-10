package dev.datlag.tooling.country


data object Angola : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("AO")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("AGO")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(24)
}