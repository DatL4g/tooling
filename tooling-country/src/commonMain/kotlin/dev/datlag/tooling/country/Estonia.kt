package dev.datlag.tooling.country


data object Estonia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("EE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("EST")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(233)
}