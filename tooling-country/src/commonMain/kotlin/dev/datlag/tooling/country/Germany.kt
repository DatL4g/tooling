package dev.datlag.tooling.country


data object Germany : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("DE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("DEU")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(276)
}