package dev.datlag.tooling.country


data object Uganda : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("UG")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("UGA")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(800)
}