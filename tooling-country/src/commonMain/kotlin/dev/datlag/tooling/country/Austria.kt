package dev.datlag.tooling.country


data object Austria : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("AT")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("AUT")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(40)
}