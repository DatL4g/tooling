package dev.datlag.tooling.country


data object Guam : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GU")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GUM")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(316)
}