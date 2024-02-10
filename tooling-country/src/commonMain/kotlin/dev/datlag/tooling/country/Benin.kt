package dev.datlag.tooling.country


data object Benin : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BJ")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BEN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(204)
}