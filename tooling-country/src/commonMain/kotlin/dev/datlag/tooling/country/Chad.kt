package dev.datlag.tooling.country


data object Chad : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("TD")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("TCD")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(148)
}