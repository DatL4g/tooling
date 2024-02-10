package dev.datlag.tooling.country


data object Spain : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("ES")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ESP")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(724)
}