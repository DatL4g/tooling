package dev.datlag.tooling.country


data object Greece : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GR")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GRC")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(300)
}