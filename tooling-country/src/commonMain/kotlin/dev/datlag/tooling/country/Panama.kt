package dev.datlag.tooling.country


data object Panama : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("PA")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("PAN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(591)
}