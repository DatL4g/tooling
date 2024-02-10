package dev.datlag.tooling.country


data object Bahrain : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BH")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BHR")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(48)
}