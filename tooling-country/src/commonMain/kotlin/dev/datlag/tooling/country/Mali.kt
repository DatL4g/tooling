package dev.datlag.tooling.country


data object Mali : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("ML")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("MLI")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(466)
}