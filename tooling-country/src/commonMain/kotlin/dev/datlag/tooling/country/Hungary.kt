package dev.datlag.tooling.country


data object Hungary : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("HU")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("HUN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(348)
}