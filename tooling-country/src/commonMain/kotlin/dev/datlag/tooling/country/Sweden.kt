package dev.datlag.tooling.country


data object Sweden : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("SE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("SWE")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(752)
}