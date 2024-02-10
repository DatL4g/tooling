package dev.datlag.tooling.country


data object Andorra : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("AD")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("AND")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(20)
}