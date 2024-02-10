package dev.datlag.tooling.country


data object Eritrea : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("ER")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ERI")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(232)
}