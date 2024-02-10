package dev.datlag.tooling.country


data object Ireland : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("IRL")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(372)
}