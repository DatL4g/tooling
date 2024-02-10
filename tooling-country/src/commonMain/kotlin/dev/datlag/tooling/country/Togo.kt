package dev.datlag.tooling.country


data object Togo : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("TG")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("TGO")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(768)
}