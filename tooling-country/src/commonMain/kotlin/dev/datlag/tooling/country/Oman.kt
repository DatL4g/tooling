package dev.datlag.tooling.country


data object Oman : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("OM")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("OMN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(512)
}