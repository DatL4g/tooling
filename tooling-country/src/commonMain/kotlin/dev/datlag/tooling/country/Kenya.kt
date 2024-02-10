package dev.datlag.tooling.country


data object Kenya : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("KE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("KEN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(404)
}