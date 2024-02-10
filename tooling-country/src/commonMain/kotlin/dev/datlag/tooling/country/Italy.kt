package dev.datlag.tooling.country


data object Italy : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IT")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ITA")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(380)
}