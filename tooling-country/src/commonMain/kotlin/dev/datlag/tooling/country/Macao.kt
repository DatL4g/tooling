package dev.datlag.tooling.country


data object Macao : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("MO")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("MAC")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(446)
}