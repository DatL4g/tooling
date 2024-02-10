package dev.datlag.tooling.country


data object India : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IN")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("IND")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(356)
}