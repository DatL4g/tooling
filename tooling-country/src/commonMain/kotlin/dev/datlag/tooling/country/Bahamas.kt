package dev.datlag.tooling.country


data object Bahamas : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BS")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BHS")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(44)
}