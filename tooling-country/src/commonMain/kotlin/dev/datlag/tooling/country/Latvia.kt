package dev.datlag.tooling.country


data object Latvia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("LV")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("LVA")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(428)
}