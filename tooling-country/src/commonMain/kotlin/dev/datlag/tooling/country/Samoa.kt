package dev.datlag.tooling.country


data object Samoa : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("WS")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("WSM")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(882)
}