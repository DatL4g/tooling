package dev.datlag.tooling.country


data object Poland : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("PL")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("POL")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(616)
}