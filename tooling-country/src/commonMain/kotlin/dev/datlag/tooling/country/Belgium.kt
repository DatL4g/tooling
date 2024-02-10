package dev.datlag.tooling.country


data object Belgium : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BEL")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(56)
}