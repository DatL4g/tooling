package dev.datlag.tooling.country


data object Egypt : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("EG")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("EGY")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(818)
}