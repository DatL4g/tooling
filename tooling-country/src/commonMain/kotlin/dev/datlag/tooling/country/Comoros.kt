package dev.datlag.tooling.country


data object Comoros : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("KM")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("COM")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(174)
}