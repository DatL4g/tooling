package dev.datlag.tooling.country


data object Bhutan : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BT")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BTN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(64)
}