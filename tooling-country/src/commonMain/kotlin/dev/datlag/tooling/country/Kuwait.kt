package dev.datlag.tooling.country


data object Kuwait : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("KW")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("KWT")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(414)
}